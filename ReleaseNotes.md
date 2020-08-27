# 0.3-SNAPSHOT(开发中未发布)

### Feature

* 监控列表增加按团队查询，默认只显示自己团队的监控；监控按部门隔离 [2020-07-22]
* Elasticsearch监控数值实现同比监控 [2020-07-24]
* Elasticsearch数据源更新免重启加载 [2020-07-25]
* 集成LDAP登录认证 [2020-07-25]
* 数据名保存表单数据提交增加前端验证 [2020-07-22]
* 菜单增加权限控制，部分页面(如：数据源配置)只对管理员开放 [2020-07-27]
* Elasticsearch查询增加历史语句自动提示 [2020-07-27]
* Elasticsearch查询数据柱状图可点击并自动变更时间范围 [2020-07-28]
* 另存时，监控名称增加(copy)字样标识，名字和原监控区分开 [2020-08-01]
* 报警消息模板管理功能 [2020-08-10]
* 账号增加角色(管理员，普通用户)设置功能 [issue#18](https://github.com/AutohomeCorp/frostmourne/issues/18) [2020-08-18]
* 聚合类型(unique_count, percentiles, standard deviation)数值监控

### Bugfix

* bugfix: 解决列表分页问题
* 解决Elasticsearch数据嵌套时，数据值为undefine的问题 [issue#11](https://github.com/AutohomeCorp/frostmourne/issues/11) [2020-08-01]

### Mysql

* mysql: alarm表增加风险等级字段risk_level - [SQL](./doc/mysql-schema/2020-07-24/change.sql)
* mysql: 增加消息模板表alert_template - [SQL](./doc/mysql-schema/2020-07-31/alert_template.sql)
* mysql: 增加用户角色表user_role - [SQL](./doc/mysql-schema/2020-08-18/user_role.sql)

### Document

* 增加Elasticsearch数据监控使用指南 [2020-08-27]

### Other

* mybatis-generator-maven-plugin依赖的mysql-connector升级为8.0.20
* 默认镜像服务改用阿里云
* 数据库访问层全部换成[mybatis-dynamic-sql](https://github.com/mybatis/mybatis-dynamic-sql) [2020-07-30]

# 0.2-RELEASE

### Feature

* 用户，团队，部门增加应用外配置文件的维护方式
* 增加数据导出CSV功能
* 增加Elasticsearch查询分享功能
* 增加HTTP报警方式
* 增加企业微信报警方式
* elasticsearch监控增加avg,min,max,sum数值metric类型
* 缺少短链接或短链接失败的情况下，使用原链接
* 数据查询页面增加创建监控跳转按钮
* 增加dockerfile和docker-compose
* HTTP监控增加头设置
* 报警接收人设置时给出提示
* 增加企业微信机器人消息发送方式 [2020-07-05]
* 用户信息，团队信息，部门信息外部文件增加定期重新加载 [2020-07-05]
* 账号管理功能模块 [2020-07-11]

### Bugfix

* bugfix: 解决dataname还在使用中，仍然可以删除的问题
* bugfix: 解决elasticsearch 7+版本，数据数量为0的问题
* bugfix: 解决邮箱需要认证的情况下，邮件发送失败的问题
* bugfix: 解决消息过长无法保存的问题

### Mysql

* mysql: alert表增加字段: http_post_url - [SQL](./doc/mysql-schema/2020-06-01/change.sql)
* mysql: data_name表增加data_name字段唯一索引 - [SQL](./doc/mysql-schema/2020-06-13/change.sql)
* mysql: alert表增加字段: wechat_robot_hook - [SQL](./doc/mysql-schema/2020-07-04/change.sql)
* mysql: 增加user_info, team_info, department_info表 - [SQL](./doc/mysql-schema/2020-07-11/change.sql)
* mysql: alarm_log表字段message类型改为text；alert_log字段content类型改为text - [SQL](./doc/mysql-schema/2020-07-16/change.sql)

### Document

* 增加query string简易教程
* 增加docker启动说明文档

### Others

* Elasticsearch-Rest-Client升级至6.6.2
* 升级guava至28.2-jre
* 引入[mybatis-dynamic-sql](https://github.com/mybatis/mybatis-dynamic-sql)
* springboot升级至2.3.1-RELEASE
* 引入[maven-ci-friendly](https://maven.apache.org/maven-ci-friendly.html)实践 [2020-07-18]
* 使用[autolog4j](https://github.com/AutohomeCorp/autolog4j)程序日志格式 [2020-07-19]
* 文本日志按天滚动 [2020-07-19]

# 0.1-RELEASE

* Elasticsearch数据监控, 你只需要写一条查询就可以轻松搞定监控
* HTTP数据监控
* UI功能，简单易用
* 监控管理
* 灵活的报警消息模板定制，支持变量
* 多种消息发送方式(email,短信,钉钉(机器人))
* 多数据源管理
* Elasticsearch数据查询页面
* 报警消息附带日志查询短链接，直达报警原因
* 报警消息抑制功能，防止消息轰炸