## 报警发送

### 关于消息头

消息头可以通过配置变量frostmourne_message_title进行自定义，不过中文需要转成unicode，否则发出来会乱码。自定义了消息头，钉钉机器人等有关的关键词设置也需要
修改。

### 发送方式

现在支持短信, email, 钉钉(机器人), 企业微信(机器人)，HTTP, 飞书机器人发送方式，其中email和钉钉机器人, 企业微信(机器人)，飞书机器人， HTTP默认可用，短信和钉钉需要自己适配实现。。

* 钉钉机器人消息发送

钉钉机器人使用说明请看钉钉官方文档: <a href="https://developers.dingtalk.com/document/robots/custom-robot-access?spm=ding_open_doc.document.0.0.155d6573BLQXUd#topic-2026027" target="_blank">钉钉机器人使用</a> ;
在钉钉群组里创建好机器人后，把地址复制到钉钉机器人地址输入栏即可。其中钉钉机器人的安全策略选择自定义关键词: 霜之哀伤

* 邮件消息发送

在配置中将邮件发送人相关配置填写正确即可

```
email.smtp.host=${your.email.smtp.host}
email.smtp.port=${your.email.smtp.port}
email.smtp.auth=${your.email.smtp.auth}
email.sender=${your.email.sender}
email.sender.password=${your.email.sender.password}
```

* 企业微信消息发送

在配置中将企业微信相关配置填写正确即可

```
wechat.corpid=${your.wechat.corpid}
wechat.agentid=${your.wechat.agentid}
wechat.secret={your.wechat.secret}
```

企业微信机器人和钉钉机器人类似，创建好机器人后，将机器人地址复制到企业微信机器人地址输入栏即可。

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
	"title": "标题",
	"context": {
	    "NUMBER": 1,
	    "start": "2021-05-01T09:00:00.000+0800",
	    "end": "2021-05-01T09:02:00.000+0800"
	}
}
```

context是监控运行的上下文数据，所有需要用到的数据都在这里面，自己按需使用即可。其中wxid是企业微信的userid。如果现有的发送方式不能满足你的需求，你就可以使用HTTP方式自己扩展一下，还是很方便的。
