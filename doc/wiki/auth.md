## 登录认证和账号管理

### 登录认证
目前密码认证有两种

* 方式1： 默认配置文件认证

只要账号存在，如果没有设置初始密码，那么任意密码可登陆，如果设置了初始密码，则所有
账号都用初始密码登陆。初始密码在frostmourne-monitor里配置

```
initial.password=${initial_password:#{null}}
```

* 方式2：LDAP认证

```
spring.ldap.urls=${spring_ldap_urls:#{null}}
spring.ldap.username=${spring_ldap_username:#{null}}
spring.ldap.password=${spring_ldap_password:#{null}}
spring.ldap.base=${spring_ldap_base:#{null}}
spring.ldap.domainName=${spring_ldap_domainName:#{null}}
```

如果在frostmourne-monitor中关于ldap的配置有值，则优先使用ldap认证方式。

> 注意： 开启了LDAP认证，admin账号仍然可以用方式1来登录

### 账号信息

默认只有admin账号可用。frostmourne-monitor自带的账号信息管理功能模块一般情况下默认实现就能满足使用需求。如果需要对接自己内部系统，可以自己把账号信息同步到frostmourne库里；