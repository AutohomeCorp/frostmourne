## HTTP监控使用说明

HTTP最大的特点就是无限的灵活，主要体现在三个方面。

* HTTP接口由于是自己定制的，监控内容非常灵活

* 判断使用js表达式(nashorn实现)，监控判断非常灵活

* 报警消息可完全自己定制

### 以用HTTP来监控elasticsearch的监控状态为例

#### url输入
HTTP Url： http://localhost:9200/_cluster/health?pretty ;常规返回结果：

```json
{
    "number_of_pending_tasks": 0,
    "cluster_name": "elk",
    "active_shards": 15050,
    "active_primary_shards": 7526,
    "unassigned_shards": 0,
    "delayed_unassigned_shards": 0,
    "HTTP_STATUS": 200,
    "timed_out": false,
    "HTTP_COST": 181,
    "relocating_shards": 0,
    "initializing_shards": 0,
    "task_max_waiting_in_queue_millis": 0,
    "number_of_data_nodes": 26,
    "number_of_in_flight_fetch": 0,
    "active_shards_percent_as_number": 100,
    "status": "green",
    "number_of_nodes": 5
}
```

#### 报警判断Javascript表达式

javascript表达式引擎使用<a href="https://www.runoob.com/java/java8-nashorn-javascript.html" target="_blank">nashorn</a>

```
status == "yellow" || status == "red" || number_of_nodes != 5
```
意思是集群状态为yellow或者状态为red或者节点数量不等于5的时候报警

#### 报警消息模板

```
日志elasticsearch集群状态异常。status: ${status}， number_of_nodes: ${number_of_nodes}
```

消息举例:

```
日志elasticsearch集群状态异常。status: red， number_of_nodes: 2
```

### 利用HTTP监控prometheus数据举例

prometheus自带HTTP API，可以非常方便得使用HTTP监控里面得数据。下面举例说明。

假设local.prometheus.com是prometheus使用的域名。那么查询语句可以这么写
```
http://local.prometheus.com/api/v1/query?query=sum by(cluster_name,cluster_env,pod) (kube_pod_info{pod_ip="127.0.0.1"})
```

点击预览数据可以看到返回的数据。

```
{
    "data": {
        "resultType": "vector",
        "result": [
            {
                "metric": {
                    "cluster_env": "prod",
                    "cluster_name": "cluster-1",
                    "pod": "my-api-fsfsf-fbbp"
                },
                "value": [
                    1621938928.222,
                    "1"
                ]
            }
        ]
    },
    "HTTP_STATUS": 200,
    "HTTP_COST": 65,
    "status": "success"
}
```

这样返回的json数据就可以用表达式来判断是否报警了

```
data.result[0].value[1] > 0
```

报警模板也可以根据返回的json定制消息。例如：

```
存在不合法的pod。
环境: ${data.result[0].metric.cluster_env}
集群名称: ${data.result[0].metric.cluster_name}
pod: ${data.result[0].metric.pod}
```

这样一个prometheus数据监控就完成了，非常简便而且强大，快点试试。

### 用HTTP监控复杂Elasticsearch Aggregation数据

HTTP可以有更为复杂的使用方法，比如：elasticsearch 有丰富的 rest api，都是可以用HTTP方式
来获取到结果然后监控报警的。例如：

```
POST http://localhost:9200/applog-*/_search

{
	"size": 1,
	"query": {
		"bool": {
			"must": [
				{
					"query_string": {
						"query": "Level: ERROR",
						"analyze_wildcard": true
					}
				},
				{
					"range": {
						"LogAt": {
							"gte": "now-1h",
							"format": "epoch_millis"
						}
					}
				}
			]
		}
	},
	"aggs": {
		"check": {
			"cardinality": {
				"field": "IP.keyword"
			}
		}
	}
}
```

返回值

```json
{
	"_shards": {
		"total": 25,
		"failed": 0,
		"successful": 25,
		"skipped": 0
	},
	"hits": {
		"hits": [
			{
				"_index": "applog-2020.06",
				"_type": "applog",
				"_source": {
					"Level": "ERROR",
					"CustomMessage": "访问接口报错",
					"ExceptionType": "java.lang.NullPointerException",
					"LogAt": "2020-06-11T10:35:08.547+0800"
				},
				"_id": "juA7oXIBULp1bxFiRzYT",
				"_score": 2.0
			}
		],
		"total": 14,
		"max_score": 2.0
	},
	"took": 56,
	"QTime": 61,
	"HttpStatus": 200,
	"timed_out": false,
	"aggregations": {
		"check": {
			"value": 11
		}
	}
}
```

看到这么丰富的返回数据，是不是有了很多想法。你还可以结合kibana的分享功能，将kibana的短链接，放到你
的报警消息里，收到报警的人直接点击kibana短链接就可以查看数据或者你定制的图表。你的判断可以这么写：

```
hits.total > 1
```

InfluxDb也有自带HTTP服务，同样可以使用HTTP来实现InfluxDB数据监控，非常方便，自己试试吧。

### 自定义HTTP监控

除了利用现有的中间件提供的REST API之外，你还可以自己定义HTTP接口，接口里只需要写监控逻辑，接口返回的JSON数据，
可以用于消息模板的变量，一切均可定制，你可以为所欲为。

