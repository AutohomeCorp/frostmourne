### 利用`HTTP`监控`prometheus`数据举例

`Prometheus`自带`HTTP API`，可以非常方便得使用`HTTP`监控里面得数据。下面举例说明。

假设`local.prometheus.com`是`prometheus`使用的域名。那么查询语句可以这么写
```
http://local.prometheus.com/api/v1/query?query=sum by(cluster_name,cluster_env,pod) (kube_pod_info{pod_ip="127.0.0.1"})
```

点击预览数据可以看到返回的数据。

```
{
    "data": {
        "resultType": "vector",
        "result": [
            {
                "metric": {
                    "cluster_env": "prod",
                    "cluster_name": "cluster-1",
                    "pod": "my-api-fsfsf-fbbp"
                },
                "value": [
                    1621938928.222,
                    "1"
                ]
            }
        ]
    },
    "HTTP_STATUS": 200,
    "HTTP_COST": 65,
    "status": "success"
}
```

这样返回的`json`数据就可以用表达式来判断是否报警了

```
data.result[0].value[1] > 0
```

报警模板也可以根据返回的`json`定制消息。例如：

```
存在不合法的pod。
环境: ${data.result[0].metric.cluster_env}
集群名称: ${data.result[0].metric.cluster_name}
pod: ${data.result[0].metric.pod}
```

这样一个`Prometheus`数据监控就完成了，非常简便而且强大，快点试试。
