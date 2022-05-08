## 登录认证和账号管理

### 登录认证

目前密码认证有两种

* 方式1： 默认系统自带密码认证

这是默认的方式，每个账号都可以设置密码，如果设置了密码就用密码认证； 如果账号没有设置密码，系统设置了统一的初始密码，用初始密码登陆，如果系统没有设置初始密码，那么任意密码可登陆。
初始密码在`frostmourne-monitor`里配置

```
initial.password=${initial_password:#{null}}
// admin账号默认的账号密码是123456
```


* 方式2：`LDAP`认证

如果在`frostmourne-monitor`中关于`ldap`的配置有值，则优先使用`ldap`认证方式。

```
ldap.enabled=${ldap_enabled:false}
spring.ldap.urls=${spring_ldap_urls:}
spring.ldap.username=${spring_ldap_username:}
spring.ldap.password=${spring_ldap_password:}
spring.ldap.base=${spring_ldap_base:}
spring.lap.auth.searchFilter=${spring_ldap_auth_searchFilter:sAMAccountName={0}}
```

其中`spring.lap.auth.searchFilter`是认证时查找账号的条件，注意根据自己的情况修改查询字段。一般是如下两种情况：

```
spring_ldap_auth_searchFilter=sAMAccountName={0}
```

```
spring_ldap_auth_searchFilter=uid={0}
```

例外情况自己修改


> 注意： 1. 开启了LDAP认证，admin账号仍然可以用初始密码登录。
> 2. 即使接入ldap登录，也需要在系统有对应的用户才能登录。

### 账号信息

默认只有`admin`账号可用。`frostmourne-monitor`自带的账号信息管理功能模块一般情况下默认实现就能满足使用需求。如果需要对接自己内部系统，可以自己把账号信息同步到`frostmourne`库里；
