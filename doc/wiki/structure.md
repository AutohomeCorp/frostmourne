## 主要项目结构

* frostmourne-vue

UI项目，使用vue-element-template实现，打包时会打到frostmourne-monitor下

* frostmourne-monitor

监控运行主体项目, 依赖frostmourne-spi和xxl-job。监控调度模块依赖xxl-job[https://github.com/xuxueli/xxl-job] 实现。

* frostmourne-spi

需要根据各自情况适配实现的模块，包括用户相关接口，短链接生成接口, 消息发送(短信发送和钉钉消息发送)接口, 需要自己实现，邮件发送,
钉钉机器人消息发送，企业微信消息发送和HTTP请求消息发送已经实现好了，其中邮箱配置和企业微信需要修改为自己的

```
email.smtp.host=${your.email.smtp.host}
email.smtp.port=${your.email.smtp.port}
email.smtp.auth=${your.email.smtp.auth}
email.sender=${your.email.sender}
email.sender.password=${your.email.sender.password}

wechat.corpid=${your.wechat.corpid}
wechat.agentid=${your.wechat.agentid}
wechat.secret={your.wechat.secret}
```

com.autohome.frostmourne.spi.plugin包下的接口，需要你根据自己情况实现。

* frostmourne-spi-starter

为了方便frostmourne-monitor使用frostmourne-spi，增加了frostmourne-spi-starter, 里面主要是接口定义和feign接口的自动注入。