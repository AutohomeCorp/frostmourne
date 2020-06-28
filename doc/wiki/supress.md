## 报警抑制

监控配置的报警发送配置里有个静默时间配置，是用来实现报警抑制的。主要是用来抑制持续报警发送消息的情况。具有如下特点: 

* 如果这个监控上一次运行触发了报警，这次监控运行没有触发报警，一定发送一条恢复通知。举例: 

```
[霜之哀伤监控系统][id:18]dealer.arch.project.error
@柯长青 
消息类型: [恢复] 请自己检查问题是否解决,上次报警内容如下
[2019-12-09 09:55:35]
frostmourne最近5分钟内有异常日志1条。最近一条异常信息:
服务器IP: 1.1.1.1
异常类型: redis.clients.jedis.exceptions.JedisConnectionException
自定义信息: HashCache error 
异常信息: save(key:xxxx, seconds:xxxx)

详细请看: http://iii94.cn/KYmvPI
```

* 如果这个监控上一次没有触发报警，这次触发了报警，一定发送一条报警通知

```
[霜之哀伤监控系统][id:18]dealer.arch.project.error
@柯长青 
消息类型: [问题] 60分钟内持续报警将不重复发送
[2019-12-09 09:55:35]
frostmourne最近5分钟内有异常日志1条。最近一条异常信息:
服务器IP: 1.1.1.1
异常类型: redis.clients.jedis.exceptions.JedisConnectionException
自定义信息: HashCache error 
异常信息: save(key:xxxx, seconds:xxxx)

详细请看: http://iii94.cn/KYmvPI
```

* 如果这次监控触发报警，并且前面一直在连续触发报警，并且当前报警和连续报警最早那次报警的时间距离小于静默时间，则这次报警被静默处理。
* 如果这次监控触发报警，并且前面一直在连续触发报警，并且当前报警和连续报警最早那次报警的时间距离大于静默时间，则这次报警正常发送。