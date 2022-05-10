### 用HTTP监控复杂Elasticsearch Aggregation数据

`HTTP`可以有更为复杂的使用方法，比如：`Elasticsearch` 有丰富的 `rest api`，都是可以用`HTTP`方式
来获取到结果然后监控报警的。例如：

```
POST http://localhost:9200/applog-*/_search

{
	"size": 1,
	"query": {
		"bool": {
			"must": [
				{
					"query_string": {
						"query": "Level: ERROR",
						"analyze_wildcard": true
					}
				},
				{
					"range": {
						"LogAt": {
							"gte": "now-1h",
							"format": "epoch_millis"
						}
					}
				}
			]
		}
	},
	"aggs": {
		"check": {
			"cardinality": {
				"field": "IP.keyword"
			}
		}
	}
}
```

返回值

```json
{
	"_shards": {
		"total": 25,
		"failed": 0,
		"successful": 25,
		"skipped": 0
	},
	"hits": {
		"hits": [
			{
				"_index": "applog-2020.06",
				"_type": "applog",
				"_source": {
					"Level": "ERROR",
					"CustomMessage": "访问接口报错",
					"ExceptionType": "java.lang.NullPointerException",
					"LogAt": "2020-06-11T10:35:08.547+0800"
				},
				"_id": "juA7oXIBULp1bxFiRzYT",
				"_score": 2.0
			}
		],
		"total": 14,
		"max_score": 2.0
	},
	"took": 56,
	"QTime": 61,
	"HttpStatus": 200,
	"timed_out": false,
	"aggregations": {
		"check": {
			"value": 11
		}
	}
}
```

看到这么丰富的返回数据，是不是有了很多想法。你还可以结合kibana的分享功能，将kibana的短链接，放到你
的报警消息里，收到报警的人直接点击kibana短链接就可以查看数据或者你定制的图表。你的判断可以这么写：

```
hits.total > 1
```

`InfluxDb`也有自带`HTTP`服务，同样可以使用`HTTP`来实现`InfluxDB`数据监控，非常方便，自己试试吧。
