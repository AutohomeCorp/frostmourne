## HTTP监控使用说明

HTTP最大的特点就是无限的灵活，主要体现在三个方面。

* HTTP接口由于是自己定制的，监控内容非常灵活

* 判断使用js表达式，监控判断非常灵活

* 报警消息可完全自己定制

### 以用HTTP来监控elasticsearch的监控状态为例

#### url输入
HTTP Url： http://localhost:9200/_cluster/health?pretty ;常规返回结果：

```json
{
    "number_of_pending_tasks": 0,
    "cluster_name": "elk",
    "active_shards": 15050,
    "active_primary_shards": 7526,
    "unassigned_shards": 0,
    "delayed_unassigned_shards": 0,
    "HTTP_STATUS": 200,
    "timed_out": false,
    "HTTP_COST": 181,
    "relocating_shards": 0,
    "initializing_shards": 0,
    "task_max_waiting_in_queue_millis": 0,
    "number_of_data_nodes": 26,
    "number_of_in_flight_fetch": 0,
    "active_shards_percent_as_number": 100,
    "status": "green",
    "number_of_nodes": 5
}
```

#### 报警判断Javascript表达式

javascript表达式引擎使用<a href="https://www.runoob.com/java/java8-nashorn-javascript.html" target="_blank">nashorn</a>

```
status == "yellow" || status == "red" || number_of_nodes != 5
```
意思是集群状态为yellow或者状态为red或者节点数量不等于5的时候报警

#### 报警消息模板

```
日志elasticsearch集群状态异常。status: ${status}， number_of_nodes: ${number_of_nodes}
```

消息举例:

```
日志elasticsearch集群状态异常。status: red， number_of_nodes: 2
```

