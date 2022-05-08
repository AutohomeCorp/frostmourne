## Skywalking Logging数据监控

使用elasticsearch是skywalking比较普遍的存储实现。可以通过监控skywalking采集到elasticsearch里log索引数据来
实现skywalking的logging。下面举例说明：

### skywalking-log索引字段

```json
{
  "sw8-online_log-20220507": {
    "mappings": {
      "properties": {
        "content": {
          "type": "keyword",
          "copy_to": [
            "content_match"
          ]
        },
        "content_match": {
          "type": "text",
          "analyzer": "oap_log_analyzer"
        },
        "content_type": {
          "type": "integer",
          "index": false
        },
        "endpoint_id": {
          "type": "keyword"
        },
        "service_id": {
          "type": "keyword"
        },
        "service_instance_id": {
          "type": "keyword"
        },
        "span_id": {
          "type": "integer"
        },
        "tags": {
          "type": "keyword"
        },
        "tags_raw_data": {
          "type": "binary"
        },
        "time_bucket": {
          "type": "long"
        },
        "timestamp": {
          "type": "long"
        },
        "trace_id": {
          "type": "keyword"
        },
        "trace_segment_id": {
          "type": "keyword"
        },
        "unique_id": {
          "type": "keyword"
        }
      }
    }
  }
}
```

### 查询语句

根据上边的索引字段，可以利用Elaticsearch数据监控logging，如:

```
service_id: bWFuYWdlLmFwaS5pY3NhcHAuY29ycGF1dG9ob21lLmNvbQ==.1 AND tags: level=error
```

### 消息模板

```
服务xxx最近${TIME_WINDOW}分钟内有异常日志${NUMBER}条。最近一条异常信息:
实例id: ${service_instance_id}
追踪id: ${trace_id}
日志内容: ${content}
```