## 升级到0.6.1说明

0.6.1版本引入了重大feature，移除xxl-job内置实现分布式调度。

* 移除xxl-job依赖

内置实现了分布式调度(抄的xxl-job)，功能没有变化，只是去掉xxl-job依赖。

### 变更内容

删除monitor中xxl-job如下相关配置：

```
xxl.job.mock=false
xxl.job.admin.addresses=${xxljob_admin_addresses}
### xxl-job executor address
xxl.job.executor.id=${xxljob_executor_id:2}
xxl.job.executor.appname=frostmourne
xxl.job.executor.ip=
xxl.job.executor.port=-1
### xxl-job, access token
xxl.job.accessToken=
### xxl-job log path
xxl.job.executor.logpath=${xxljob_executor_logpath}
### xxl-job log retention days
xxl.job.executor.logretentiondays=${xxljob_executor_logretentiondays:7}
### xxl-job alarm email
xxl.job.alarm.email=${xxljob_alarm_email}
```

如果xxl-job服务只是用于本项目，那么这个xxl-job可以下掉。

### docker-compose配置修改内容

删除xxl-job服务，去掉frostmourne-monitor中xxl-job相关配置，和depends_on。镜像tag改为0.6.1

### mysql表结构变更

表结构变更语句请看：[mysql-change](../mysql-schema/2022-04-17/change.sql)


