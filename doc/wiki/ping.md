## Ping监控指南

用于测试域名或者服务器的连通性

### 数据配置

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/wiki/img/ping-metric.png" />

其中查询语句填入需要检测的ip地址或者域名，多个用英文逗号分割，如:

```
127.0.0.1, 192.168.1.1
```

### ping规则配置

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/wiki/img/ping-rule.png" />

规则直接就是ping就行

### 预览数据

点击预览，数据如下：

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/wiki/img/ping-data.png" />

可用变量说明如下：

 字段名  | 类型     | 说明  | 适用的数据源
-------- |----------| ------- | -----
TOTAL | int | 检测总数 | PING
SERVER_LIST | List<String> | 检测服务列表 | PING
SERVERS | String | 检测服务列表字符串，英文逗号分割 | PING
FAIL_COUNT | int | 检测失败的服务个数 | PING
FAIL_SERVER_LIST | List<String> | 检测失败的服务列表 | PING
FAIL_SERVERS | String | 检测失败的服务列表串，英文逗号分割 | PING

### 消息模板

可用上边变量配置消息模板，例如：

```
服务器：${FAIL_SERVERS} 测试Ping失败，请关注
```

报警消息如下：

```
[2022-05-07 20:14:31] [问题] [通知]
服务器：192.168.1.1 测试Ping失败，请关注
```
