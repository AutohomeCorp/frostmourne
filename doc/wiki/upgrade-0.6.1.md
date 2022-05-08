## 升级到0.6.1说明

`0.6.1`版本引入了重大`feature`，移除`xxl-job`内置实现分布式调度。

* 移除xxl-job依赖

内置实现了分布式调度(借鉴`xxl-job`实现)，功能没有变化，只是去掉`xxl-job`依赖。

### 变更内容

删除`monitor`中`xxl-job`如下相关配置：

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

如果`xxl-job`服务只是用于本项目，那么这个`xxl-job`可以下掉。

### `docker-compose`配置修改内容

删除`xxl-job`服务，去掉`frostmourne-monitor`中`xxl-job`相关配置，和`depends_on`。镜像tag改为`0.6.1`

### mysql表结构变更

表结构变更语句请看：[mysql-change](../mysql-schema/2022-04-17/change.sql)


