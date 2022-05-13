<div align="center"><a href="https://github.com/AutohomeCorp/frostmourne"><img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/logo/frostmourne_logo.jpg"></a></div>

<p align="center">
<a href="https://github.com/AutohomeCorp/frostmourne/releases"><img src="https://img.shields.io/github/release/AutohomeCorp/frostmourne" alt="GitHub release"></a>
<a href="https://github.com/AutohomeCorp/frostmourne/stargazers"><img src="https://img.shields.io/github/stars/AutohomeCorp/frostmourne" alt="GitHub Stars"></a>
<a href="https://github.com/AutohomeCorp/frostmourne/fork"><img src="https://img.shields.io/github/forks/AutohomeCorp/frostmourne" alt="GitHub Forks"></a>
<a href="https://github.com/AutohomeCorp/frostmourne/graphs/contributors"><img src="https://img.shields.io/github/contributors/AutohomeCorp/frostmourne" alt="GitHub Contributors"></a>
<a href="https://github.com/AutohomeCorp/frostmourne/issues"><img src="https://img.shields.io/github/issues/AutohomeCorp/frostmourne" alt="GitHub issues"></a>
<a href="https://openjdk.java.net/"><img src="https://img.shields.io/badge/Java-8-blue?logo=java&logoColor=white" alt="JDK support"></a>
<a href="https://github.com/AutohomeCorp/frostmourne/blob/master/LICENSE"><img src="https://img.shields.io/npm/l/svelte.svg" alt="LICENSE"></a>
<a href="https://github.com/AutohomeCorp/frostmourne"><img src="https://img.shields.io/github/downloads/AutohomeCorp/frostmourne/total.svg" alt="Downloads"></a>
</p>

[Github地址](https://github.com/AutohomeCorp/frostmourne) | [Gitee地址](https://gitee.com/tim_guai/frostmourne)


# 📖 介绍

`Frostmourne`(霜之哀伤)是汽车之家经销商技术部监控系统的开源版本，用于帮助开发监控应用日志，现主要用于监控`Elasticsearch`数据。如果你现在使用`Elastic stack(ELK)`建立起了日志系统，
却苦恼于没有一个配套日志监控系统，也许它能帮到你。

### 项目初衷

在用`ELK`建立起日志系统之后，我们发现应用日志监控这块除了`ElastAlert`之外，没有其他方案。我们初期使用`ElastAlert`来解决日志监控的问题，
但是随着配置的增加，不仅管理成本和使用成本较高，稳定性方面也不能让我们满意，所以为了更好的易用性，稳定性，我们决定自己做一套简单的监控系统，
来解决日志监控的问题。如果你面临和我们同样的问题，不妨一试。

但是项目并不仅限于`Elasticsearch`数据，还支持`HTTP`, `PING`数据监控，`Prometheus`, `SkyWalking`, `InfluxDB`，`MySQL`, `ClickHouse`数据监控，后面还会加入更多的常用数据源
(如：`IoTDB`, `Loki`, `MongoDB` `Redis`, `Oracle`, `SqlServer`等)纳入监控范畴，需要做的东西还有很多，需要更多相关开发加入进来，欢迎联系我们，一起做大做强。

# ✨ 主要功能

* 只需要写一条数据查询就可以轻松搞定监控
* 多种数据源支持：`Elasticsearch, HTTP, SkyWalking, Prometheus, InfluxDB, MySQL/TiDb, ClickHouse, PING`
* 数值计算类型监控：`count, min, max, avg, sum, unique count, percentiles, standard deviation`; `Elasticsearch`数据支持分桶
* 报警消息发送方式：钉钉(机器人)、企业微信(机器人)、飞书机器人、Email、短信、HTTP
* 支持消息格式：`text, markdown`
* 灵活的报警消息`Freemarker`模板定制，支持变量占位符；消息模板管理
* 分布式调度实现，每个监控都是独立调度，互不影响
* 报警消息附带日志查询短链接，直达报警原因
* 数值同比监控
* `HTTP`数据监控, `Javascript`表达式判断是否报警; `PING`连通监控
* 前端简单易用：监控管理、测试、另存、执行日志和历史消息
* `Elasticsearch`数据查询、分享和下载
* 报警消息抑制功能，防止消息轰炸
* 自带账号，团队，部门信息管理模块，也可自己实现内部对接
* 集成`LDAP`登录认证
* 权限控制，数据隔离，各团队互不影响

    #### 企业微信机器人报警截图展示： （<a href="./doc/wiki/feature_image.md" target="_blank">查看完整功能截图展示</a>）

    <img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/wiki/img/markdown_wechat_robot.png" />


# 📚 功能使用指南

* <a href="./doc/wiki/es.md" target="_blank">Elasticsearch数据监控指南</a>
* <a href="./doc/wiki/http-alarm.md" target="_blank">HTTP监控使用说明</a>
* <a href="./doc/wiki/prometheus.md" target="_blank">Prometheus数据监控指南</a>
* <a href="./doc/wiki/skywalking.md" target="_blank">SkyWalking数据监控指南</a>
* <a href="./doc/wiki/jdbc-mysql.md" target="_blank">MySQL数据监控指南</a>
* <a href="./doc/wiki/jdbc-clickhouse.md" target="_blank">ClickHouse数据监控指南</a>
* <a href="./doc/wiki/influxdb.md" target="_blank">InfluxDB数据监控指南</a>
* <a href="./doc/wiki/ping.md" target="_blank">PING监控指南</a>
* <a href="./doc/wiki/same-time-compare.md" target="_blank">数值同比监控使用指南</a>
* <a href="./doc/wiki/template.md" target="_blank">消息模板配置</a>
* <a href="./doc/wiki/ways.md" target="_blank">报警发送</a>
* <a href="./doc/wiki/supress.md" target="_blank">报警抑制</a>
* <a href="./doc/wiki/auth.md" target="_blank">用户管理和登录认证</a>
* <a href="./doc/wiki/note.md" target="_blank">注意事项</a>
* <a href="./doc/wiki/other.md" target="_blank">其他</a>


# 💻 在线demo

为了更快的理解本项目的作用，提供了一个接口全`mock`的静态站点供大家预览功能: <a href="https://frostmourne-demo.github.io/">在线demo</a>
在线`demo`更新不及时，请以项目实际运行效果为准，`demo`只是用于快速浏览


# 📦 快速启动

提供`docker-compose`方式，让你更快运行起来便于更好理解项目作用。详细请看文档：<a href="./doc/wiki/quick-start.md" target="_blank">Quick-Start</a>


# 🧰 部署

#### 预备环境准备：`MySQL`数据库表创建
> Frostmourne所有表的创建语句都在 [frostmourne.sql](./doc/mysql-schema/frostmourne.sql) 文件中。

数据库密码默认使用明文，没有加密策略。如果你需要对密码进行加密，请参考druid官方文档：[druid数据库密码加密](https://github.com/alibaba/druid/wiki/%E4%BD%BF%E7%94%A8ConfigFilter)


#### 一、`k8s`部署方式
`k8s`部署参考以下三个配置文件

* [frostmourne-monitor-namespace.yaml](./doc/docker/k8s/frostmourne-monitor-namespace.yaml)
* [frostmourne-monitor-deployment.yaml](./doc/docker/k8s/frostmourne-monitor-deployment.yaml)
* [frostmourne-monitor-service.yaml](./doc/docker/k8s/frostmourne-monitor-service.yaml)

相关参数在 `frostmourne-monitor-deployment.yaml` 文件里配置。需要注意的是在`frostmourne-monitor-service.yaml`里指定对外映射端口，默认`nodePort=30054`

```bash
kubectl applt -f frostmourne-monitor-namespace.yaml
kubectl applt -f frostmourne-monitor-deployment.yaml
kubectl apply -f frostmourne-monitor-service.yaml
```

#### 二、`zip`包部署方式

依赖环境
* `JDK 1.8`
* `MySQL 5.7.8+`

下载链接：<a href="https://github.com/AutohomeCorp/frostmourne/raw/master/doc/wiki/zip/frostmourne-monitor-0.7-SNAPSHOT.zip" download>frostmourne-monitor-0.7-SNAPSHOT.zip</a> ，解压后然后根据自己的环境修改应用配置文件`application.properties`文件和环境变量配置文件`env`，然后执行如下命令启动：

```bash
./scripts/startup.sh
```

执行如下命令停止应用：

```bash
./scripts/shutdown.sh
```

#### 三、自构建部署方式

依赖环境
* `JDK 1.8`
* `Maven 3.2.x+`
* `MySQL 5.7.8+`

在项目`frostmourne`主目录下执`maven`构建命令：
```bash
mvn -U clean package -DskipTests=true
```

前端项目`frostmourne-vue`会自动把资源构建到`frostmourne-monitor`的`resources/dist`下，所以你只需要部署`frostmourne-monitor`即可。

`frostmourne-monitor`已经配置了`assembly`打包，`target`目录下会生成`zip`包，你只需要将`zip`包解压，然后根据自己的
环境修改应用配置文件`application.properties`文件和环境变量配置文件env，然后执行如下命令启动：

```bash
./scripts/startup.sh
```

执行如下命令停止应用：

```bash
./scripts/shutdown.sh
```

# 🛠 开发调试

调试环境要求

* `JDK 1.8`
* `Node 16.14.2 (推荐)`
* `Yarn 1.22.10 (推荐) 或 Npm 8.7.0`
* `MySQL 5.7.8+`
* `Elasticsearch 6.3.2+`

启动`frostmourne-monitor`项目, 启动参数增加：

```
-Dmysql.host=localhost -Dmysql.user=root -Dmysql.password=example -Dlog.console.level=INFO
```

`MySQL`相关参数修改为自己环境的，`active profile`设置为`local`, 测试地址: http://localhost:10054
使用`VS Code`打开`frostmourne-vue`目录，进行前端调试。执行如下命令:

```bash
# install dependency
yarn install

# 建议不要直接使用 cnpm 安装以来，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
yarn install --registry=https://registry.npm.taobao.org

# develop
yarn dev
```

会自动打开： http://localhost:9528

搭建本地开发调试环境或者需要做二次开发遇到什么困难的都可以加群沟通，欢迎各路英雄多多PR

技术说明项目基于`Java8`实现，详细请看：[技术说明](./doc/wiki/technical.md)



# ⚙️ 后续规划

* ~~发布0.6.2-RELEASE~~ [2022-05-05]
* ~~【0.7】改进消息静默功能：添加静默判断表达式，对报警事件数据和静默时间内的事件数据指定字段对比。这样可以避免漏报同时防止报警消息过多。~~  [2022-05-05]
* ~~【0.7】MySQL, clickhouse监控增加表达式监控规则~~ [2022-05-06]
* ~~【0.7】增加ping监控报警~~ [2022-05-07]
* ~~【0.7】增加[skywalking](https://github.com/apache/skywalking)日志数据监控~~ [2022-05-09]
* ~~【0.7】增加[skywalking](https://github.com/apache/skywalking)报警数据监控~~ [2022-05-10]
* ~~【0.7】增加SkyWalking数据监控使用指南~~ [skywalking.md](./doc/wiki/skywalking.md) [2022-05-10]
* ~~【0.7】增加[prometheus](https://github.com/prometheus/prometheus)数据监控报警支持~~ [2022-05-12]
* ~~【0.7】bugfix: 解决http消息模板不加载的问题~~ [2022-05-12]
* ~~【0.7】Document: 增加Prometheus数据监控使用指南~~ [prometheus.md](./doc/wiki/prometheus.md) [2022-05-12]
* 发布0.7-RELEASE, 进入0.8-SNAPSHOT开发版
* Elasticsearch监控数值实现环比监控
* 增加本项目内程序日志采集至MySQL并提供查询页面，方便排查问题和监控
* 解决邮箱报警不支持ssl的问题
* Elasticsearch数据名增加kibana链接配置，在数据查询页面增加kibana地址跳转链接，方便将数据查询切换至kibana
* 短信报警方式实现，默认用阿里云短信实现
* 增加[loki](https://github.com/grafana/loki)数据监控报警
* 增加[iotdb](https://github.com/apache/iotdb)数据监控报警
* 增加[redis](https://github.com/redis/redis)数据监控报警
* 增加邮箱在线配置页面功能
* 增加企业微信在线配置页面功能
* 将短链接id以16进制格式展示，解决id数字很大的时候较长的问题
* 增加邮箱在线配置页面功能
* pom优化
* 增加消息内容长度配置，超过长度配置部分将被截掉
* 员工换组增加是否迁移监控至新组的选项，如果勾选将该员工创建的监控也转移至新组
* 增加报警组支持
* 增加监控转组功能
* Elasticsearch数据名增加traceid字段配置，可以配置跳转链接。例如: 配置skywalking的链接将跳转到skywalking对应的调用链
* InfluxDB数据查询除了返回数值，另外返回最新一个point详细数据用于报警消息模板
* 增加InfluxDB数据查询页面
* InfluxDB数据监控增加短链接，跳转到InfluxDB数据查询页面
* 监控列表增加"执行日志"操作按钮，点击跳转到对应监控执行日志列表页
* 增加时序数据历史数据比较规则
* 监控增加报警消息允许发送时间段设置，非允许发送时间段内消息将只记录不发送，发送状态为FORBID
* 增加企业钉钉发消息默认实现(本地没有环境，需要帮助，欢迎PR，或者提供示例代码，先行谢过)
* 更多报警方式补充（欢迎PR）
* 后端接口增加数据校验并返回合适的提示信息
* 监控列表增加一个开关选项，只显示我的监控
* 监控调度配置后显示预计调度时间
* Elasticsearch数据名配置时自动提示索引名称
* 引入对象迭代器，迭代器代表从数据源中得到一个List数据列表，或者自定义输入；用于动态生成监控查询语句，例如: Level: ERROR AND Project: ${ITEM_VALUE}；达到数据遍历监控的效果；
* 国际化
* 发布1.0-RELEASE
* 增加frostmourne程序日志格式采集方案
* 增加frostmourne程序日志查询和分析功能
* 3-sigma离群点检测报警规则
* 加入时序数据异常检测算法规则(需要实验可行性 [基于时间序列的异常检测](https://blog.rexking6.top/2018/11/05/%E5%9F%BA%E4%BA%8E%E6%97%B6%E9%97%B4%E5%BA%8F%E5%88%97%E7%9A%84%E5%BC%82%E5%B8%B8%E6%A3%80%E6%B5%8B/))
* 总结项目用到的知识点

### 1.0-RELEASE核心Feature后续计划

* ~~elasticsearch主流版本6,7,8支持~~
* ~~静默功能优化~~
* ~~msyql, Clickhouse监控增加表达式监控规则~~
* ~~ping命令监控~~
* ~~elasticearch数据配置支持数据分桶，分桶类型支持两种：1. 按字段值分组，相当于ES里的Terms Aggregation; 2. 按时间分组,相当于ES里的DateHistogramAggregation~~
* ~~增加[skywalking](https://github.com/apache/skywalking)Log数据监控报警支持~~
* ~~增加[skywalking](https://github.com/apache/skywalking)Alarm数据监控报警支持~~
* ~~增加[prometheus](https://github.com/prometheus/prometheus)数据监控报警支持~~
* 增加 [skywalking](https://github.com/apache/skywalking) Database Layer 数据监控报警支持~~
* 增加[loki](https://github.com/grafana/loki)数据监控报警
* 报警升级功能
* 增加本项目内程序日志采集至MySQL并提供查询页面，方便排查问题和监控
* Elasticsearch监控数值实现环比监控


# 🗓 [发版历史](./ReleaseNotes.md)

# 👍 [致谢](./doc/wiki/thank.md)


# 👷 Contributors

[@menong-chen](https://github.com/menong-chen) [@fox2zz](https://github.com/fox2zz) [@xyzj91](https://github.com/xyzj91)
[@wxmclub](https://github.com/wxmclub) [@Aping](https://github.com/wuaping)

[![GitHub Contributors](https://contrib.rocks/image?repo=AutohomeCorp/frostmourne)](https://github.com/AutohomeCorp/frostmourne/graphs/contributors)


# 💡️ 如何参与贡献

如果你觉得这个项目对你有所帮助想有所回馈，非常欢迎参与贡献。可以通过如下方式：

* 从后续规划里选择合适的任务提交`PR`
* 对文档进行必要补充
* 部署本项目使用起来并通过 [`github`](https://github.com/AutohomeCorp/frostmourne/issues/17) 或 [`gitee`](https://gitee.com/tim_guai/frostmourne/issues/I560YJ) 告知
* 帮忙扩散推广
* 在 [`issue`](https://github.com/AutohomeCorp/frostmourne/issues) 提出你的宝贵建议
* 加入交流群，解答交流问题。群内会不定时发布项目更新说明
* 开源不易，需要鼓励
* [代码规范说明](./doc/wiki/code_format.md)

# 💬 联系我们

有问题需要帮助或者交流可以添加下边的微信群或QQ群，请优先选择提 [issue](https://github.com/AutohomeCorp/frostmourne/issues) ，便于问题的讨论和记录追踪，也方便有类似问题的伙伴搜索解决。也欢迎对项目感兴趣的同僚加群交流。
特别提一下：关于文档觉得哪里写的不通畅，不好理解，或者有哪方面缺失，都欢迎提 [issue](https://github.com/AutohomeCorp/frostmourne/issues) 。

<div align="center"><img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/frostmourne-contact.jpg" /></div>

# 📌 项目事记

* 2019-12-16: 发布`github`
* 2020-06-14: 发布`gitee`
* 2020-07-02: 合并第一个`PR`
* 2020-07-04: 被`elastic`中文社区收录 [Elastic日报988期](https://elasticsearch.cn/article/14018)
* 2020-07-13: github项目设置为私有，丢失82个`star`，29个`fork`
* 2020-07-15: 重新公开`github`
* 2020-08-23: 上`gitee`推荐
* 2020-08-27: `gitee star`破百
* 2020-10-12: `github star`破百

# 👥 已注册使用者

通过 [`github`](https://github.com/AutohomeCorp/frostmourne/issues/17) 或 [`gitee`](https://gitee.com/tim_guai/frostmourne/issues/I560YJ) 登记的使用者

<table>
<tr>
<td><img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/user/autohome.jpg" alt="汽车之家"></td>
<td><img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/user/zuiyou.jpg" alt="最右APP"></td>
<td><img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/user/jiajiayue.jpg" alt="家家悦"></td>
<td><img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/user/sungrow.jpg" alt="阳光电源"></td>
<td><img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/user/etcp.jpg" alt="ETCP"></td>
</tr>
</table>




# ⭐️ Stargazers over time

[![Stargazers over time](https://starchart.cc/AutohomeCorp/frostmourne.svg)](https://starchart.cc/AutohomeCorp/frostmourne)
