## 用户管理和登录认证

目前密码认证比较简单，只要账号存在，如果没有设置初始密码，那么任意密码可登陆，如果设置了初始密码，则所有
账号都用初始密码登陆。初始密码在frostmourne-monitor里配置

```
initial.password=${initial_password:#{null}}
```

默认只有admin账号可用。目前用户管理有两个实现，

* frostmourne-monitor自带的账号信息管理功能模块(默认选项)
* 另一个是用frostmourne-spi接口实现的。

使用如下配置项目来进行设置：

```
### default: apply frostmourne's default account module; spi: apply frostmourne-spi account service
frostmourne.account.type=${frostmourne_account_type:default}
```

配置值说明：

* default: 默认实现，使用frostmourne-monitor自带的账号信息管理
* spi: 使用spi接口实现

一般情况下默认实现就能满足使用需求。如果需要对接自己内部系统，可以自己把账号信息同步到frostmourne库里；或者选择对接
frostrmourne-spi, 然后将frostmourne.account.type配置为spi即可。frostmourne-spi里的账号信息默认使用json文件实现，比较简陋，不建议使用。

* [frostmourne-spi/src/main/resources/auth/user.json](https://github.com/AutohomeCorp/frostmourne/tree/master/frostmourne-spi/src/main/resources/auth/user.json) 用户信息配置文件
* [frostmourne-spi/src/main/resources/auth/department.json](https://github.com/AutohomeCorp/frostmourne/tree/master/frostmourne-spi/src/main/resources/auth/department.json) 部门信息配置文件
* [frostmourne-spi/src/main/resources/auth/team.json](https://github.com/AutohomeCorp/frostmourne/tree/master/frostmourne-spi/src/main/resources/auth/team.json) 团队信息配置文件

spi的账号管理接口只有选择自己对接内部信息管理时其中一种实现选择，基本上用不到。