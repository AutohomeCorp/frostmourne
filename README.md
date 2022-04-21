## [Github地址](https://github.com/AutohomeCorp/frostmourne) | [Gitee地址](https://gitee.com/tim_guai/frostmourne)

## 介绍

frostmourne(霜之哀伤)是汽车之家经销商技术部监控系统的开源版本，用于帮助开发监控应用日志，现主要用于监控Elasticsearch数据。
关于内部日志系统的设计实现感兴趣的话，请移步文章: <a href="./doc/wiki/design.md" target="_blank">之家经销商技术部基于Elasticsearch的日志系统设计与实现</a> 可以认为frostmoure是监控部分的实现。
如果你现在使用Elastic stack(ELK)建立起了日志系统，却苦恼于没有一个配套日志监控系统，也许它能帮到你。

## 主要功能

* 只需要写一条数据查询就可以轻松搞定监控
* 多数据源(Elasticsearch, InfluxDB, Mysql, ClickHouse)支持
* 多种数值计算类型监控(count,min,max,avg,sum,unique count,percentiles,standard deviation)
* 数值同比监控
* HTTP数据监控, js表达式判断是否报警
* UI功能，简单易用
* 监控管理，测试，另存。执行日志，历史消息。
* 灵活的报警消息freemarker模板定制，支持变量；消息模板管理
* 多种报警消息发送方式(email,短信,钉钉(机器人),企业微信(机器人), WebHook, 飞书机器人)
* 消息支持多种格式(text, markdown)
* Elasticsearch数据查询,分享,下载
* 报警消息附带日志查询短链接，直达报警原因
* 报警消息抑制功能，防止消息轰炸
* 分布式调度实现，每个监控都是独立调度，互不影响
* 自带账号,团队,部门信息管理模块，也可自己实现内部对接
* 集成LDAP登录认证
* 权限控制，数据隔离，各团队互不影响

## 功能截图展示

* 报警效果

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/message.png" />

* <a href="./doc/wiki/feature_image.md" target="_blank">完整功能截图展示</a>

## 在线demo

为了更快的理解本项目的作用，提供了一个接口全mock的静态站点供大家预览功能: <a href="https://frostmourne-demo.github.io/">在线demo</a>
在线demo更新不及时，请以项目实际运行效果为准，demo只是用于快速浏览

## 项目初衷

在用ELK建立起日志系统之后，我们发现应用日志监控这块除了ElastAlert之外，没有其他方案。我们初期使用ElastAlert来解决日志监控的问题，
但是随着配置的增加，管理成本，使用成本较高和，配置文件多了之后，稳定性方面也不能让我们满意，所以为了更好的易用性，稳定性，我们决定自己做一套简单的监控系统，
来解决日志监控的问题。如果你面临和我们同样的问题，不妨一试。

但是项目并不仅限于elasticsearch数据，还有HTTP数据监控，InfluxDB数据监控，Mysql数据监控, ClickHouse数据监控，后面还会加入更多的常用数据源(如：prometheus, skywalking,
iotdb, loki等)纳入监控范畴，需要做的东西还有很多，需要更多相关开发加入进来，欢迎联系我们。

## 快速启动

提供docker-compose方式，让你更快运行起来便于更好理解项目作用。
详细请看文档：<a href="./doc/wiki/quick-start.md" target="_blank">Quick-Start</a>

## 实现简易示意图

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/alarm_design.png" />

## mysql数据库表创建

* frostmourne库

frostmourne所有表的创建语句在[doc/mysql-schema/frostmourne.sql](./doc/mysql-schema/frostmourne.sql)文件中。

## 项目结构

* frostmourne-vue

UI项目，使用vue-element-template实现，打包时会把生成的资源文件打到frostmourne-monitor里

* frostmourne-monitor

监控运行主体项目。

## 部署

UI项目frostmourne-vue会自动把资源打到frostmourne-monitor的resources/dist下，所以你只需要部署frostmourne-monitor。

```
frostmourne.monitor.address=http://${frostmourne-monitor-address}
```
frostmourne.monitor.address配置用于生成日志查询地址和调度触发监控改运行。

### zip包部署

frostmourne-monitor已经配置了assembly打包，target目录下会生成zip包，你只需要将zip包解压，然后根据自己的
环境修改应用配置文件application.properties文件和环境变量配置文件env，然后执行如下命令启动：

```bash
./scripts/startup.sh
```

执行如下命令停止应用：

```bash
./scripts/shutdown.sh
```

### k8s部署

努力ing

## 功能使用指南

* <a href="./doc/wiki/es.md" target="_blank">Elasticsearch数据监控指南</a>
* <a href="./doc/wiki/http-alarm.md" target="_blank">HTTP监控使用说明</a>
* <a href="./doc/wiki/influxdb.md" target="_blank">InfluxDB数据监控指南</a>
* <a href="./doc/wiki/jdbc-mysql.md" target="_blank">Mysql数据监控指南</a>
* <a href="./doc/wiki/jdbc-clickhouse.md" target="_blank">Clickhouse数据监控指南</a>
* <a href="./doc/wiki/same-time-compare.md" target="_blank">数值同比监控使用指南</a>
* <a href="./doc/wiki/template.md" target="_blank">消息模板配置</a>
* <a href="./doc/wiki/ways.md" target="_blank">报警发送</a>
* <a href="./doc/wiki/supress.md" target="_blank">报警抑制</a>
* <a href="./doc/wiki/auth.md" target="_blank">用户管理和登录认证</a>

## 调度配置说明

调度配置有一个需要特别注意的地方，就是调度间隔和你的数据查询窗口有关系。一般日志系统采集日志多少都会有延迟，少的话几秒，多的话几分钟都
是可以预见的，所以尽量保证两次调度之间查询的日志数据有一定的重叠是很明智的做法，切忌出现数据真空(两次调度之间有数据未被查询窗口覆盖)。
举例：一般的程序错误日志监控配置调度间隔为每2分钟调度一次，查询数据窗口可以配置为3分钟。这样虽然因为1分钟数据重叠可能导致多报(事实上因为报警抑制
的原因，你并不会受到多条报警消息的骚扰)，但是基本可以保证不会漏报。这里只是举一个常见的例子，具体如何配置，你需要根据自己的实际情况。

## 监控测试

一般在创建监控或者刚创建完监控的时候，你会想测试一下监控的执行。在监控保存页面有测试功能，你可以尝试不同的查询
语句来验证你的想法。

另外在监控列表页面，你可以点击运行按钮让监控立即运行而不必等待调度来验证你的想法。

## 监控另存

在创建了很多监控之后，你会发现同一类型的大部分监控是非常相似的，这时候你就会想要监控另存功能。你可以在监控列表的已有监控
中找一个和你想要创建的监控相似的监控，点编辑进入监控编辑页面后，直接另存，就会生成一个一模一样的新监控，然后你就可以安全的
修改这个新监控了。之所以建议直接另存是因为你会非常容易忘记你是想另存一个监控，而去点了保存按钮。就会把现有监控覆盖掉。

## 调试环境要求

* JDK 1.8
* node
* mysql
* elasticsearch 6.3.2+

数据库密码默认使用明文，没有加密策略，如果你需要对密码进行加密，请参考druid官方文档：[druid数据库密码加密](https://github.com/alibaba/druid/wiki/%E4%BD%BF%E7%94%A8ConfigFilter)

## 开发调试

启动frostmourne-monitor项目, 启动参数增加：

```
-Dmysql.host=localhost -Dmysql.user=root -Dmysql.password=example -Dlog.console.level=INFO
```

mysql相关参数修改为自己环境的，active profile设置为local, 测试地址: http://localhost:10054
使用VS Code打开frostmourne-vue目录，进行UI调试。执行如下命令:

```bash
# install dependency
npm install

# 建议不要直接使用 cnpm 安装以来，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org

# develop
npm run dev
```

会自动打开： http://localhost:9528

搭建本地开发调试环境或者需要做二次开发遇到什么困难的都可以加群沟通，欢迎各路英雄多多PR

## ORM选型说明

项目的数据库ORM使用的是mybatis，一直以来mybatis的xml配置sql深受国内开发欢迎，因为
它非常的灵活，能比较好的应付快速多变的的迭代需求。但是缺点也比较明显。

* 对于一些简单的查询来说，xml定义过于繁琐
* 太灵活了，稍不注意sql就会写得很复杂，后面维护艰难

mybatis最新推出了新的模块[mybatis-dynamic-sql](https://github.com/mybatis/mybatis-dynamic-sql)，代码即sql
的查询，大部分基于生成的代码可以直接写代码完成，不需要写任何sql。

为了兼顾方便和灵活，我同时在项目里引入了mybatis-dynamic-sql和xml-sql两种方式，让他们互补配合一起完成数据访问。
大部分(90%以上)查询直接用mybatis-dynamic-sql，对于一些很少的需要灵活的稍复杂sql使用xml-sql来完成。既提高了
编码效率，又保留了原来的灵活强大的xml-sql。我们只需要按需选择使用。

## 后续规划

目前已知的规划有:

* ~~发布0.5-RELEASE~~ [2022-04-04]
* ~~将发送消息功能从spi移到monitor~~ [2022-04-05]
* ~~移除spi模块，随着monitor功能完善，spi的存在已经成为鸡肋，移除掉可以降低调试和部署难度~~[2022-04-05]
* ~~增加0.5升级0.6的说明文档~~ [upgrade-0.6.md](./doc/wiki/upgrade-0.6.md) [2022-04-07]
* ~~rule表增加消息模板内容类型字段：alert_template_type~~ - [SQL](./doc/mysql-schema/2022-04-14/change.sql) [2022-04-15]
* ~~报警消息格式增加类型: text, markdown选项~~ [2022-04-15]
* ~~mysql: user_info表增加密码字段password~~ - [SQL](./doc/mysql-schema/2022-04-10/change.sql) [2022-04-10]
* ~~增加用户密码设置功能，方便没有部署ldap的团队使用。配置了ldap的将优先使用ldap认证。~~ [2022-04-10]
* ~~mysql: 增加数据库分布式锁表job_lock，alarm表增加两个字段：trigger_last_time, trigger_next_time~~ - [SQL](./doc/mysql-schema/2022-04-17/change.sql) [2022-04-18]
* ~~移除xxl-job依赖，内置实现分布式调度，减小部署难度~~ [2022-04-18]
* ~~增加0.6升级0.6.1的说明文档~~ [upgrade-0.6.1.md](./doc/wiki/upgrade-0.6.1.md) [2022-04-19]
* ~~msyql, influxdb, clickhouse监控增加表达式监控规则~~ [2022-04-20]
* 数据配置支持数据分桶，分桶类型支持两种：1. 按字段值分组，相当于ES里的Terms Aggregation; 2. 按时间分组,相当于ES里的DateHistogramAggregation
* Elasticsearch监控数值实现环比监控
* 优化dockerfile，增加k8s环境部署说明文档
* 增加ping监控报警,一个监控最多监控10个ping。
* 增加邮箱在线配置页面功能
* 增加企业微信在线配置页面功能
* 将短链接id以16进制格式展示，解决id数字很大的时候较长的问题
* 增加邮箱在线配置页面功能
* 增加消息内容长度配置，超过长度配置部分将被截掉
* 解决邮箱报警不支持ssl的问题
* 增加本项目内程序日志采集至mysql并提供查询页面，方便排查问题和监控
* 员工换组增加是否迁移监控至新组的选项，如果勾选将该员工创建的监控也转移至新组
* 解决firefox浏览器时间显示有问题的bug
* 增加报警组支持
* Elasticsearch数据名增加traceid字段配置，可以配置跳转链接。例如: 配置skywalking的链接将跳转到skywalking对应的调用链
* 增加[prometheus](https://github.com/prometheus/prometheus)数据监控报警支持
* 增加[skywalking](https://github.com/apache/skywalking)数据监控报警支持
* 增加[iotdb](https://github.com/apache/iotdb)数据监控报警
* 增加[loki](https://github.com/grafana/loki)数据监控报警
* 改进消息静默功能：对报警事件数据和静默时间内的事件数据对比，如果相似度很高就静默，如果和静默时间内事件相似度不高仍然报警。这样可以避免漏报同时防止报警消息过多。
* influxDB数据查询除了返回数值，另外返回最新一个point详细数据用于报警消息模板
* 增加influxDB数据查询页面
* influxdb数据监控增加短链接，跳转到influxdb数据查询页面
* 监控列表增加"执行日志"操作按钮，点击跳转到对应监控执行日志列表页
* 增加时序数据历史数据比较规则
* 监控增加报警消息允许发送时间段设置，非允许发送时间段内消息将只记录不发送，发送状态为FORBID
* 增加企业钉钉发消息默认实现(本地没有环境，需要帮助，欢迎PR，或者提供示例代码，先行谢过)
* 更多报警方式补充（欢迎PR）
* 后端接口增加数据校验并返回合适的提示信息
* 监控列表增加一个开关选项，只显示我的监控
* 监控调度配置后显示预计调度时间
* Elasticsearch数据名配置时自动提示索引名称
* 数据源增加连接测试功能
* 增加监控模板功能：可以创建多个变量，变量名用于填写监控模板，保存时将变量名替换为变量值，
基于监控模板创建监控只需要填写变量值即可，基于模板一次可以创建多个监控。
* 国际化
* 发布1.0-RELEASE
* 增加frostmourne程序日志格式采集方案
* 增加frostmourne程序日志查询和分析功能
* 3-sigma离群点检测报警规则
* 加入时序数据异常检测算法规则(需要实验可行性，欢迎有相关经验的同僚联系)
* 总结项目用到的知识点

## 发版历史

[ReleaseNotes](./ReleaseNotes.md)

## 主要技术栈

* springboot 2.x
* element ui
* vue-admin-template
* xxl-job
* mybatis
* freemarker
* elasticsearch
* InfluxDB
* jjwt
* nashorn

## Contributors

[@menong-chen](https://github.com/menong-chen) [@fox2zz](https://github.com/fox2zz) [@xyzj91](https://github.com/xyzj91)
[@wxmclub](https://github.com/wxmclub) [@wuaping](https://github.com/wuaping)

## 致谢
- [springboot](https://github.com/spring-projects/spring-boot)
- [vue](https://cn.vuejs.org/index.html)
- [vue-admin-template](https://github.com/PanJiaChen/vue-admin-template)
- [xxl-job](https://github.com/xuxueli/xxl-job)
- [element ui](https://element.eleme.cn/#/zh-CN)
- [45短网址](https://45dwz.cn/)
- [jjwt](https://github.com/jwtk/jjwt)
- [mybatis-dynamic-sql](https://github.com/mybatis/mybatis-dynamic-sql)
- [JetBrains](https://www.jetbrains.com/)

## License

The project is licensed under the [MIT](LICENSE).

## 如何参与贡献

如果你觉得这个项目对你有所帮助想有所回馈，非常欢迎参与贡献。可以通过如下方式：

* 部署本项目使用起来并通过[issue#17](https://github.com/AutohomeCorp/frostmourne/issues/17)告知
* 帮忙扩散推广
* 在issue提出你的宝贵建议
* 对文档进行必要补充
* 加入交流群，解答交流问题。群内会不定时发布项目更新说明。
* 从后续规划里选择合适的任务提交PR
* 开源不易，需要鼓励。

## 联系我们

有问题或需要帮助请提issue或者加入QQ群: 1082617505，请优先选择提issue，便于问题的讨论和记录追踪，也方便有类似问题的伙伴搜索解决。 也欢迎对项目感兴趣的同僚加群沟通。
特别提一下：关于文档觉得哪里写的不通畅，不好理解，或者有哪方面缺失，都欢迎提issue。

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/frostmourne-qq.png" />

## 项目事记

* 2019-12-16: 发布github
* 2020-06-14: 发布gitee
* 2020-07-02: 合并第一个PR
* 2020-07-04: 被elastic中文社区收录 [Elastic日报988期](https://elasticsearch.cn/article/14018)
* 2020-07-13: github项目设置为私有，丢失82个star，29个fork
* 2020-07-15: 重新公开github
* 2020-08-23: 上gitee推荐
* 2020-08-27: gitee star破百
* 2020-10-12: github star破百
