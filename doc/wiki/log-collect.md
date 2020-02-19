# 日志采集落地方案

## 问题背景

团队日志经过规范化后，接下来就需要一个采集方案。本文主要是分享我们团队的方案，供有相同需求的同行参考一下，希望对部分人有所帮助或者启发。本文是接着上一篇讲的，需要了解日志规范化相关内容的，请移步上一篇文章。<a href="https://github.com/AutohomeCorp/frostmourne/blob/master/doc/wiki/log.md" target="_blank">日志规范化落地方案</a>

## 采集端-nxlog

虽然现在已经进入容器化时代，filebeat, fluentd在私有云环境应用比较多，但是我们当时还没有容器化，所以我们选择了nxlog，目前实践下来，nxlog出色的完成了这个任务。使用下来，总结nxlog优秀的特性如下：

* 高性能，低消耗，稳定
* 多平台支持（因为我们是C#，java混合环境）
* 强大灵活的脚本语言

缺陷（社区版）：

* 单条日志大小有限制
* 无法统计关键指标

总体来说够用。下面以我们自己的日志格式为例，给出对应的nxlog配置主体部分。

```
<Extension syslog>
    Module xm_syslog
</Extension>
<Extension json>
    Module xm_json
</Extension>
#========================================================================
#applog
##======================================================================
<Extension applog_layout>
    Module xm_csv
    Fields $LogAt, $TraceId, $Department, $Team, $Project, $Host, $ServerIP, $ContextPath, $UriStem, $QueryString, $FormString, $UserAgent, $Level, $Class, $Method, $MethodParams, $Line, $Logger, $IOType, $ExceptionType, $ExceptionMessage, $CustomMessage, $StackTrace, $HawkKey
    FieldTypes string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string
    Delimiter \t
    QuoteChar '"'
    EscapeControl TRUE
    QuoteMethod All
    UndefValue ''
</Extension>
<Extension applog-multi>
    Module xm_multiline
    HeaderLine /^"\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}.+/
</Extension>
<Input applog_in>
    Module im_file
    File '/[your_log_dir_1]/project_*.log'
    SavePos TRUE
    InputType applog-multi
    Exec applog_layout->parse_csv();
    Exec to_json();
</Input>
 
<Input auth_applog_in>
    Module im_file
    File '/[your_log_dir_2]/project_*.log'
    SavePos TRUE
    InputType applog-multi
    Exec applog_layout->parse_csv();
    Exec to_json();
</Input>
<Output applog_out>
    Module om_tcp
    Host [your_host]
    Port [your_port]
</Output>
<Route applog>
    Path applog_in,auth_applog_in => applog_out
</Route>
```

我们日志格式是tab符分割的csv格式，这个配置主要做了如下几件事。

* 多行文本合并

```
<Extension applog-multi>
    Module xm_multiline
    HeaderLine /^"\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}.+/
</Extension>
```

* csv格式解析

```
<Extension applog_layout>
    Module xm_csv
    Fields $LogAt, $TraceId, $Department, $Team, $Project, $Host, $ServerIP, $ContextPath, $UriStem, $QueryString, $FormString, $UserAgent, $Level, $Class, $Method, $MethodParams, $Line, $Logger, $IOType, $ExceptionType, $ExceptionMessage, $CustomMessage, $StackTrace, $HawkKey
    FieldTypes string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string, string
    Delimiter \t
    QuoteChar '"'
    EscapeControl TRUE
    QuoteMethod All
    UndefValue ''
</Extension>
```

* 转化为json

```
Exec applog_layout->parse_csv();
Exec to_json();
```

* 通过tcp发送出去

```
<Output applog_out>
    Module om_tcp
    Host [your_host]
    Port [your_port]
</Output>
```

## 接收端-logstash

日志接收端，我们使用的是logstash，logstash的优点是配置灵活强大，性能也不差，但是资源吃的比较厉害。但是因为我们还是比较富的，这点资源还不是事儿。下面给出logstash日志接收端的配置。我们使用的logstash版本为2.4.1，其他版本需要自己测试并修改适配。

```
input {
  tcp {
    type => "applog"
    codec=> json
    port => [your_port]
  }
}

filter {
        mutate {
                convert => {
                        "Line" => "integer"
                }
                uppercase => [ "Level" ]
                lowercase => [ "Department", "Team", "Project", "Host", "ContextPath", "UriStem" ]
        }
}


output {
        elasticsearch {
                hosts => ["127.0.0.1:9200"]
                index => "applog-%{+YYYY.MM.dd}"
        }
}
```

上面的配置直接把日志写入es，在初期一般没啥问题，但是日志越来越多，日志系统越来越重要的时候，先写入kafka会是一个非常明智的做法，这样修改一下output部分的配置即可。

```
output {
        if [Level] == "WARN" or [Level] == "ERROR" or [Level] == "FATAL" or [Level] == "WARNING" or [Level] == "EROR" {
                kafka {
                        codec => json
                        topic_id => "project_error_log"
                        client_id => "client_id"
                        bootstrap_servers => "127.0.0.1:9092,127.0.0.2:9092"
                        retries => 2
                }
        } else {
                kafka {
                        codec => json
                        topic_id => "project_info_log"
                        client_id => "client_id"
                        bootstrap_servers => "127.0.0.1:9092,127.0.0.2:9092"
                        retries => 2
                }
        }
}
```

这里更进一步将错误日志和info日志分开采集到不同的队列，主要是考虑错误日志比较重要，实时性要求高于一般info日志，同时错误日志数量比一般info日志少很多，分开消费可以保证error日志能及时的采集并消费到es，对于错误监控和问题排查有重大的意义。当然，只有日志到达一定量的时候，你才需要考虑这么做，初期都写到一个队列就可以了。

然后，简单的做法是直接写java消费kafka的程序批量写入es。如果条件允许，最好可以用spark，storm，flink任何一个实现这部分。注意一定要批量写入es，不然写入速度会很慢。

这样程序日志规范化和采集已经完成了，日志已经可以用kibana直接查看了。 程序日志采集已经完成，后面会继续介绍我们如何实现日志查询分析和监控。  

其中我们的日志监控系统已经开源，有需要的话，欢迎使用：<a href="https://github.com/AutohomeCorp/frostmourne" target="_blank">frostmourne(霜之哀伤日志监控系统)</a>  

关于内部日志系统的设计实现感兴趣的话，请移步文章: <a href="./doc/wiki/design.md" target="_blank">之家经销商技术部基于Elasticsearch的日志系统设计与实现</a>
