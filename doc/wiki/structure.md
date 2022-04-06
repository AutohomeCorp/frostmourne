## 主要项目结构

* frostmourne-vue

UI项目，使用vue-element-template实现，打包时会打到frostmourne-monitor下

* frostmourne-monitor

监控运行主体项目, 依赖xxl-job。监控调度模块依赖xxl-job[https://github.com/xuxueli/xxl-job] 实现。

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
