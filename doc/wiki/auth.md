## 用户管理和登录认证

目前没有做任何密码认证，只要用户名是存在的，任意密码均可以登录, 默认只有admin账号可用。用户信息管理在frostmourne-spi中实现，默认实现方式是一个json配置文件，
需要添加用户的时候，修改这个json文件即可。同样团队管理，部门管理默认也是json配置。  

* frostmourne-spi/src/main/resources/auth/user.json 用户信息配置文件
* frostmourne-spi/src/main/resources/auth/department.json 部门信息配置文件
* frostmourne-spi/src/main/resources/auth/team.json 团队信息配置文件

应用内的文件有个问题就是修改起来非常不方便，每次修改都要重新打包部署，为了更方便一些，提供了外部配置文件路径设置的配置，你可以维护
应用外的配置文件，修改完重启frostmourne-spi就可以了，不需要重新打包，配置了外部文件的将优先使用外部文件。对应的application.properties文件的配置如下：

```
your.auth.user.jsonfile=
your.auth.team.jsonfile=
your.auth.department.jsonfile=
```

文件内容格式举例：

user.json

```json
[
  {
    "account": "admin",
    "fullName": "管理员",
    "teamName":"dealer.arch",
    "mobile": "150****501",
    "email": "xxxx@qq.com",
    "wxid": "000001",
    "roles": ["admin"]
  }
]
```

在设置报警接收人的时候，接收人信息填写这里的account字段值即可，发送报警的时候，会根据account获取到user信息，然后
取到号码，邮箱，企业微信id等接收对象。

team.json

```json
[
  {
    "name": "dealer.arch",
    "fullName": "架构组",
    "department": "dealer"
  }
]
```

department.json

```json
[
  {
    "name": "dealer",
    "fullName": "经销商技术部"
  }
]
```

当然，更好的选择是选择不使用默认的方式，自己实现frostmourne-spi里的相关插件来适配自己内部系统的用户管理。