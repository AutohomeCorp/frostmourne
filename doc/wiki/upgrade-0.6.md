## 升级到0.6说明

0.6版本引入了破坏性的修改。

* (1) 将spi移除，相关功能转移至monitor，原因是随着monitor功能完善，spi显得很鸡肋了，移除spi可以降低部署难度。

功能没有变化。全部修改内容只需要将原来配置在spi中的配置移动monitor即可。

### 变更内容：

将如下配置从spi移至monitor

```
your.email.smtp.host=
your.email.smtp.port=
your.email.smtp.auth=
your.email.sender=
your.email.sender.password=

your.wechat.corpid=
your.wechat.agentid=
your.wechat.secret=
```

移除spi相关配置：

```
frostmourne.spi.service-addr=
```

下线spi服务

### docker-compose配置修改内容

增加如下部分

```
version: '3.6'
services:

  frostmourne-monitor:
    environment:
      email.smtp.host: smtp.qq.com
      email.smtp.port: 25
      email.smtp.auth: 'true'
      email.sender: xxx@qq.com
      email.sender.password: xxx
      wechat.corpid:
      wechat.agentid:
      wechat.secret:
```

删除spi服务所有配置，并移除frostmourne-monitor对frostmourne-spi的depends_on配置。


