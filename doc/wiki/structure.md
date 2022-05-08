## 主要项目结构

* `frostmourne-vue`

前端项目，使用`vue-element-template`实现，打包时会打到`frostmourne-monitor`下

* `frostmourne-monitor`

```
// 告警邮件配置
email.smtp.host=${your.email.smtp.host}
email.smtp.port=${your.email.smtp.port}
email.smtp.auth=${your.email.smtp.auth}
email.sender=${your.email.sender}
email.sender.password=${your.email.sender.password}

// 微信配置
wechat.corpid=${your.wechat.corpid}
wechat.agentid=${your.wechat.agentid}
wechat.secret={your.wechat.secret}
```
