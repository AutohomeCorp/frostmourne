## `Telnet`监控指南

用于测试端口连通性

### 数据配置

数据选择telnet

其中查询语句填入需要检测的`ip`地址或者域名和端口，用英文冒号连接，多个地址用英文逗号分割，如:

```
127.0.0.1:8080, 192.168.1.1:80
```

### telnet规则配置

规则直接就是`telnet`就行

### 预览数据

点击预览，返回数据格式字段说明如下：

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
服务器：${FAIL_SERVERS} 测试Telnet失败，请关注
```
