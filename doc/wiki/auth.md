## 用户管理和登录认证

目前密码认证比较简单，只要账号存在，如果没有设置初始密码，那么任意密码可登陆，如果设置了初始密码，则所有
账号都用初始密码登陆。初始密码在frostmourne-monitor里配置

```
initial.password=${initial_password:#{null}}
```

默认只有admin账号可用。frostmourne-monitor自带的账号信息管理功能模块


一般情况下默认实现就能满足使用需求。如果需要对接自己内部系统，可以自己把账号信息同步到frostmourne库里；