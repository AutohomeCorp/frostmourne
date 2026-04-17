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

[Github地址](https://github.com/AutohomeCorp/frostmourne) | [Gitee地址](https://gitee.com/tim_guai/frostmourne) | [中文](./README.md) | [English](./README_en.md)


# 📖 Introduction

`Frostmourne` is an open source version of the Autohome Dealer Tech monitoring system to help monitor almost all database data (including `Elasticsearch`, `Prometheus`, `SkyWalking`, `MySql`, etc.) . If you have established a logging system,
metric system, but worry about not having a supporting monitoring system, maybe it can help you.

### Purpose

After building a logging system with `ELK`, we found that there is no other solution for application log monitoring except `ElastAlert`. We initially used `ElastAlert` to solve the problem of log monitoring,
However, with the increase of configuration, not only the management cost and usage cost are high, but also the stability cannot satisfy us. Therefore, for better ease of use and stability, we decided to build a simple monitoring system by ourselves.
to solve the problem of log monitoring.

The project is not limited to `Elasticsearch` data, but also supports `HTTP`, `PING`, `Telnet` monitoring, `Prometheus`, `SkyWalking`, `InfluxDB`, `MySQL`,
`ClickHouse`, `IoTDB`, ` SqlServer` data monitoring, Later, more common data sources (such as `Loki`, `MongoDB` `Redis`, `Oracle`, etc.) will be added to the monitoring category.
There are still many things that need to be done, and more related development needs to be added. Welcome to join us to make the project more powerful.

# ✨ Features

* You only need to write a data query to easily get monitoring
* Support multiple data source support：`Elasticsearch, HTTP, SkyWalking, Prometheus, InfluxDB, MySQL/TiDb, ClickHouse, SqlServer, PING, IotDB, Telnet`
* Support number aggregation：`count, min, max, avg, sum, unique count, percentiles, standard deviation`; and `Elasticsearch` data bucket.
* Support multiple message way: dingtalk(robot)、wechat(robot)、feishu robot、Email、sms、HTTP
* Support multiple message type: `text, markdown`
* Flexible alarm message `Freemarker` template customization, support for variable placeholders; message template management
* Distributed scheduling implementation, each monitoring is independently scheduled without affecting each other
* The alarm message comes with a short link for log query, directly to the cause of the alarm
* Value year-on-year, month-on-month monitoring
* `HTTP` data monitor, judge with `Javascript` expression; support `PING` monitor, `Telnet` monitor
* The front end is simple and easy to use: monitoring management, testing, saving, execution logs and historical messages
* `Elasticsearch` data query、share and download
* Alarm message suppression function to prevent message bombardment; there is also an alarm upgrade function to prevent fault-related parties from not being notified for a long time.
* Account, team, department information management module, you can also realize internal docking by yourself
* Integrated `LDAP` login authentication
* Permission control, data isolation, each team does not affect each other

    #### wechat robot message example： （<a href="./doc/wiki/feature_image.md" target="_blank">View full function screenshots</a>）

    <img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/wiki/img/markdown_wechat_robot.png" />

<!-- TOC -->

## Table of contents

- [Security Risk Notice](#️-security-risk-notice)
- [Introduction](#-Introduction)
- [Features](#-features)
- [Online demo](#-online-demo)
- [Quick start](#-quick-start)
- [Deployment](#-deployment)
  - [Prepare mysql](#preparemysql-scripts)
  - [k8s deployment](#1k8s-deployment)
  - [zip package deployment](#2zip-package-deployment)
  - [Self-build deployment](#3self-build-deployment)
- [Usage guide](#-usage-guide)
- [Develop](#-develop)
- [Follow-up planning](#follow-up-planning)
- [ReleaseNotes](./ReleaseNotes.md)
- [Contributors](#-contributors)

<!-- /TOC -->

# ⚠️ Security Risk Notice

The HTTP data source monitoring feature allows users to configure custom HTTP request URLs. The system will actively initiate HTTP requests to those URLs on the server side, retrieve responses, and evaluate custom expressions to determine whether an alert should be triggered. This feature carries an inherent risk of SSRF (Server-Side Request Forgery) by design.

> This system is designed as an internal ops tool. It is strongly recommended to deploy it only within your corporate intranet and never expose it to the public internet — this is the most fundamental and effective security baseline.

**Notes and Recommendations**

1. Intranet deployment is the baseline, but not an absolute guarantee: Be aware that even when deployed within an intranet, this feature could still be exploited by malicious users to access or probe other internal services. When deployed on cloud servers, pay special attention to configuration to prevent access to cloud platform metadata services.
1. Follow the principle of least privilege: When configuring monitored URLs, be as precise and fixed as possible. Avoid using dynamic URLs that can be freely constructed by users.
1. Recommended mitigation approaches: You can further improve security by configuring network firewall rules, using a proxy gateway, or applying network-level access controls on the host to restrict outbound request scope from this service.

**Final reminder**: Security is a continuous process, not a one-time task. We will also provide further security hardening in future releases. Please assess the risks according to your security requirements and implement appropriate security controls.

# 💻 Online demo

In order to understand the role of this project more quickly，we provide a static web with mock data for preview: <a href="https://frostmourne-demo.github.io/">online demo</a>
The online `demo` is not updated in time, please refer to the actual running effect of the project. The `demo` is only for quick browsing.


# 📦 Quick start

We provides the `docker-compose` method to make it run faster and better understand the role of the project。
Please see the documentation for details：<a href="./doc/wiki/quick-start.md" target="_blank">Quick-Start</a>


# 🧰 Deployment

#### Prepare：`MySQL` scripts
> Frostmourne's all sql scripts in file: [frostmourne.sql](./doc/mysql-schema/frostmourne.sql) 。


#### 1、`k8s` deployment
`k8s` way deployment refers to the following three configuration files:

* [frostmourne-monitor-namespace.yaml](./doc/docker/k8s/frostmourne-monitor-namespace.yaml)
* [frostmourne-monitor-deployment.yaml](./doc/docker/k8s/frostmourne-monitor-deployment.yaml)
* [frostmourne-monitor-service.yaml](./doc/docker/k8s/frostmourne-monitor-service.yaml)

Related parameters are configured in the `frostmourne-monitor-deployment.yaml` file.
It should be noted that the external mapping port is specified in `frostmourne-monitor-service.yaml`，default `nodePort=30054`

```bash
kubectl applt -f frostmourne-monitor-namespace.yaml
kubectl applt -f frostmourne-monitor-deployment.yaml
kubectl apply -f frostmourne-monitor-service.yaml
```

#### 2、`zip` package deployment

requirements
* `JDK 1.8`
* `MySQL 5.7.8+`

The latest Release zip package，please go to [releases](https://github.com/AutohomeCorp/frostmourne/releases) for download.
After decompression, modify the application configuration file `application.properties`
and environment configuration file `env` according to your own environment，then execute the following command to start：

```bash
./scripts/startup.sh
```

execute the following command to stop:

```bash
./scripts/shutdown.sh
```

#### 3、Self-build deployment

requirements
* `JDK 1.8`
* `Maven 3.2.x+`
* `MySQL 5.7.8+`

In `frostmourne` main folder, execute `maven` build command：

```bash
mvn -U clean package -DskipTests=true
```

The frontend module `frostmourne-vue` will copy static resource to `frostmourne-monitor` module's `resources/dist` folder，so you only need deploy `frostmourne-monitor`.

`frostmourne-monitor` config `assembly` package，`target` folder has `zip` package，then you can refer `zip` package deployment.

# 📚 Usage guide

<table>
<tr>
<td><a href="./doc/wiki/es.md" target="_blank">Elasticsearch data monitor guide</a></td>
<td><a href="./doc/wiki/http-alarm.md" target="_blank">HTTP monitor guide</a></td>
<td><a href="./doc/wiki/prometheus.md" target="_blank">Prometheus data monitor guide</a></td>
</tr>
<tr>
<td><a href="./doc/wiki/skywalking.md" target="_blank">SkyWalking data monitor guide</a></td>
<td><a href="./doc/wiki/jdbc-mysql.md" target="_blank">MySQL data monitor guide</a></td>
<td><a href="./doc/wiki/jdbc-clickhouse.md" target="_blank">ClickHouse data monitor guide</a></td>
</tr>
<tr>
<td><a href="./doc/wiki/influxdb.md" target="_blank">InfluxDB data monitor guide</a></td>
<td><a href="./doc/wiki/ping.md" target="_blank">PING guide</a></td>
<td><a href="./doc/wiki/same-time-compare.md" target="_blank">Same time data compare guide</a></td>
</tr>
<tr>
<td><a href="./doc/wiki/template.md" target="_blank">Message template</a></td>
<td><a href="./doc/wiki/ways.md" target="_blank">Send message</a></td>
<td><a href="./doc/wiki/supress.md" target="_blank">Alarm suppression</a></td>
</tr>
<tr>
<td><a href="./doc/wiki/auth.md" target="_blank">User management and login authentication</a></td>
<td><a href="./doc/wiki/note.md" target="_blank">Precautions</a></td>
<td><a href="./doc/wiki/other.md" target="_blank">Other</a></td>
</tr>
<tr>
<td><a href="./doc/wiki/ring-compare.md" target="_blank">Ring time data compare guide</a></td>
<td><a href="./doc/wiki/iotdb.md" target="_blank">Iotdb data monitor guide</a></td>
<td><a href="./doc/wiki/telnet.md" target="_blank">Telnet guide</a></td>
</tr>
</table>

# 🛠 Develop

Requirements

* `JDK 8 ~ 14`
* `Node 16.14.2 (recommand)`
* `Yarn 1.22.10 (recommand) or Npm 8.7.0`
* `MySQL 5.7.8+`
* `Elasticsearch 6.3.2+`

start up `frostmourne-monitor`, add arguments:

```
-Dmysql.host=localhost -Dmysql.user=root -Dmysql.password=example -Dlog.console.level=INFO
```

Modify `MySQL` related parameters to those of your own environment，`active profile` set `local`, test url: http://localhost:10054 ,
Open `frostmourne-vue` with `VS Code`，execute the following command:

```bash
# install dependency
yarn install

# develop
yarn dev
```

will automatically open：http://localhost:9528

Contact us if you got problems.

# ⚙Follow-up planning

* ~~【0.9】发布0.8-RELEASE, 进入0.9开发~~ [2022-06-09]
* ~~【0.9】增加telnet端口连通监控~~ [2022-06-09]
* ~~【0.9】修复飞书消息发送两条的问题~~ [2022-06-17]
* ~~【0.9】增加SqlServer数据监控报警~~ [2022-06-30]
* ~~【0.9】监控列表增加监控调度时间查看~~ [2022-08-04]
* ~~【0.9】优化启动脚本，支持java11, java13~~ [2022-08-04]
* ~~【0.9】告警列表组件新增日志查询~~ [2022-08-13]
* ~~【0.9】数据源及数据名页面数据类型新增icon~~ [2022-08-13]
* prometheus增加/api/v1/query_range查询支持
* 增加本项目内程序日志采集至MySQL并提供查询页面，方便排查问题和监控
* Elasticsearch数据名增加kibana链接配置，在数据查询页面增加kibana地址跳转链接，方便将数据查询切换至kibana
* 短信报警方式实现，默认用阿里云短信实现
* 完成英语国际化
* 增加 [loki](https://github.com/grafana/loki) 数据监控报警
* 数据源列表页面增加数据源图标列，方便区分
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
* 监控列表增加"执行日志"操作按钮，点击跳转到对应监控执行日志列表页
* 增加时序数据历史数据比较规则
* 监控增加报警消息允许发送时间段设置，非允许发送时间段内消息将只记录不发送，发送状态为FORBID
* 增加企业钉钉发消息默认实现(本地没有环境，需要帮助，欢迎PR，或者提供示例代码，先行谢过)
* 更多报警方式补充（欢迎PR）
* 后端接口增加数据校验并返回合适的提示信息
* 增加 [skywalking](https://github.com/apache/skywalking) `Database Layer` 数据监控报警支持
* 监控列表增加一个开关选项，只显示我的监控
* 监控调度配置后显示预计调度时间
* Elasticsearch数据名配置时自动提示索引名称
* 引入对象迭代器，迭代器代表从数据源中得到一个List数据列表，或者自定义输入；用于动态生成监控查询语句，例如: Level: ERROR AND Project: ${ITEM_VALUE}；达到数据遍历监控的效果；
* 国际化
* 发布1.0-RELEASE
* 3-sigma离群点检测报警规则
* 加入时序数据异常检测算法规则(需要实验可行性 [基于时间序列的异常检测](https://blog.rexking6.top/2018/11/05/%E5%9F%BA%E4%BA%8E%E6%97%B6%E9%97%B4%E5%BA%8F%E5%88%97%E7%9A%84%E5%BC%82%E5%B8%B8%E6%A3%80%E6%B5%8B/))
* 总结项目用到的知识点

# 🗓 [ReleaseNotes](./ReleaseNotes.md)

# 👍 [Thanks to](./doc/wiki/thank.md)


# 👷 Contributors

[@menong-chen](https://github.com/menong-chen) [@fox2zz](https://github.com/fox2zz) [@xyzj91](https://github.com/xyzj91)
[@wxmclub](https://github.com/wxmclub) [@Aping](https://github.com/wuaping)

[![GitHub Contributors](https://contrib.rocks/image?repo=AutohomeCorp/frostmourne)](https://github.com/AutohomeCorp/frostmourne/graphs/contributors)


# 💡️ How to contribute

If you think this project is helpful to you and want to give something back, you are very welcome to contribute. can be done as follows：

* Select the appropriate task from the follow-up plan to submit `PR`
* Make necessary additions to the documentation
* Deploy this project and use it, inform us via [`github`](https://github.com/AutohomeCorp/frostmourne/issues/17) or [`gitee`](https://gitee.com/tim_guai/frostmourne/issues/I560YJ)
* Help spread the word
* Submit your valuable suggestions in [`issue`](https://github.com/AutohomeCorp/frostmourne/issues)
* Join the exchange group and answer exchange questions. The group will publish project updates from time to time
* Open source is not easy, thank you for your one-click three-link encouragement
* [code conduct](./doc/wiki/code_format.md)

# 💬 Contact us

If you have any questions and need help or communication, you can add the WeChat group or QQ group below.
Please give priority to [issue](https://github.com/AutohomeCorp/frostmourne/issues), which is convenient for the discussion and record tracking of the problem,
 and it is also convenient to have A buddy search solution for similar problems. Colleagues who are interested in the project are also welcome to join the group to communicate.
Special mention: If you feel that the documentation is not smooth, it is difficult to understand, or there is something missing,
you are welcome to raise [issue](https://github.com/AutohomeCorp/frostmourne/issues)。

<div align="center"><img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/frostmourne-contact.jpg" /></div>

# 👥 Registered user

via [`github`](https://github.com/AutohomeCorp/frostmourne/issues/17) or [`gitee`](https://gitee.com/tim_guai/frostmourne/issues/I560YJ) registered user

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
