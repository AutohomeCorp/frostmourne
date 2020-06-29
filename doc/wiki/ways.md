## 报警发送

现在支持短信,email, 钉钉(机器人), 企业微信，HTTP 五种发送方式，其中email和钉钉机器人, 企业微信， HTTP默认可用，短信和钉钉需要自己适配实现。。

* 钉钉机器人消息发送

钉钉机器人使用说明请看钉钉官方文档: <a href="https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq/26eaddd5" target="_blank">钉钉机器人使用</a> ;
在钉钉群组里创建好机器人后，把地址复制到钉钉机器人地址输入栏即可。其中钉钉机器人的安全策略选择自定义关键词: 霜之哀伤

* 邮件消息发送

在frostmourne-spi中将邮件发送人相关配置填写正确即可

```
email.smtp.host=${your.email.smtp.host}
email.smtp.port=${your.email.smtp.port}
email.smtp.auth=${your.email.smtp.auth}
email.sender=${your.email.sender}
email.sender.password=${your.email.sender.password}
```

* 企业微信消息发送

在frostmourne-spi中将企业微信相关配置填写正确即可

```
wechat.corpid=${your.wechat.corpid}
wechat.agentid=${your.wechat.agentid}
wechat.secret={your.wechat.secret}
```

* HTTP消息发送

HTTP消息发送方式，需要使用者自己实现一个用于接收消息发送请求的POST接口，接口POST内容JSON格式如下: 

```json
{
	"recipients": [
		{
			"account": "admin",
			"fullName": "管理员",
			"teamName": "team1",
			"mobile": "150****0501",
			"email": "admin@qq.com",
			"wxid": "00001",
			"roles": [
				"admin"
			]
		},
		{
			"account": "zhangsan",
			"fullName": "张三",
			"teamName": "team2",
			"mobile": "150****0311",
			"email": "zhangsan@163.com",
			"wxid": "00002",
			"roles": [
				"admin"
			]
		}
	],
	"content": "报警消息",
	"title": "标题"
}
```

其中wxid是企业微信的userid