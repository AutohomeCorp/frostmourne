# 日志规范化落地方案

## 问题背景

程序日志规范化的必要性，相信很多人早就意识到了，但是接下来马上就会面临如何快速简单的落地日志规范的问题。本文主要介绍一下我们是如何解决这个问题的

## 规范

当然首先需要一个日志规范，我们收集了最常用的字段，最后定的规范如下表: 

序号 | 字段名  | 类型     | 说明     | Elasticsearch存储
----|-------- |----------| ------- | -----
1   | LogAt   | DateTime | 日志时间 | iso8601
2   | TraceId | string   | 跟踪Id   | 不分词
3   | Department | string | 部门    | 不分词,统一小写
4   | Team    | string   | 团队  | 不分词,统一小写
5   | Project | string   | 项目名称  | 不分词,统一小写
6   | Host | string   | 域名  | 不分词,统一小写
7   | ServerIP | string   | 服务器IP  | 不分词,统一小写
8   | ContextPath | string   | 虚拟目录  | 不分词,统一小写
9   | UriStem | string   | url主干  | 不分词,统一小写
10  | QueryString | string   | GET参数  | 分词
11  | FormString | string   | POST参数  | 分词
12  | UserAgent | string   | UserAgent  | 分词
13  | Level | string   | 日志级别  | 不分词,统一大写
14  | Class | string   | 记录日志所在类名  | 不分词
15  | Method | string   | 记录日志所在方法名  | 不分词
16  | MethodParams | string   | 抛出自定义异常方法参数  | 分词
17  | Line | int   | 行号  | 整数
18  | Logger | string   | 日志名  | 不分词
19  | IOType | string   | 自定义异常io类型  | 不分词
20  | ExceptionType | string   | 异常类型  | 不分词
21  | ExceptionMessage | string   | 异常信息  | 分词
22  | CustomMessage | string   | 自定义信息  | 分词
23  | StackTrace | string   | 堆栈信息  | 分词
24  | HawkKey  | string | Key  | 不分词 

这个日志格式设计具有如下特点: 

* 异常消息(ExceptionMessage)和自定义消息(CustomMessage)分离
* 异常类型(ExceptionType),异常堆栈(StackTrace)，异常消息(ExceptionMessage)相互分离
* 扩展了Web相关字段。域名(Host)， url主干(UriStem)等

这些设计都是为了后面方便日志搜索和监控报警。  

另外我们发现很多同事打日志害怕异常信息和堆栈丢失，喜欢把异常消息，堆栈和自定义消息拼接到一起，例如:

```java
logger.error("my message, " + ex.toString, ex);
```

造成异常信息，堆栈丢失的原因是因为原先的日志里没有自动把异常信息，堆栈，包含到格式里。这也是我们顺带需要解决的一个问题。

## 规范化实现

我们团队统一使用的是slf4j + log4j2来打日志，log4j2本身提供了非常友好的插件扩展，
这样我们就可以扩展一个自己的Layout出来，将日志格式规范内化到Layout里，这样使用者
只需要应用我们的Layout，无需关心日志格式，打出来就是规范的日志格式，这样推广就会
简单很多，而且还可以方便的扩展字段，顺便解决掉异常消息和堆栈丢失的问题。

我们扩展的Layout实现类名为: Autolog4jCsvLayout, 以下配置就可以实现日志规范化。

```xml
<RollingFile name="ProgramError" ignoreExceptions="false"
                     fileName="${sys:log.path}/project_error.log"
                     filePattern="${sys:log.path}/project_error.log_%d{yyyy-MM-dd}">
    <Autolog4jCsvLayout charset="UTF-8" department="${sys:department}" team="${sys:team}" project="${sys:project}" />
    <Policies>
        <TimeBasedTriggeringPolicy interval="1" modulate="true" />
    </Policies>
    <EnumFilter allowLevels="WARN,ERROR,FATAL" />
</RollingFile>

<RollingFile name="ProgramRun" ignoreExceptions="false"
             fileName="${sys:log.path}/project_run.log"
             filePattern="${sys:log.path}/project_run.log_%d{yyyy-MM-dd}">
    <Autolog4jCsvLayout charset="UTF-8" department="${sys:department}" team="${sys:team}" project="${sys:project}" />
    <Policies>
        <TimeBasedTriggeringPolicy interval="1" modulate="true" />
    </Policies>
    <EnumFilter allowLevels="TRACE,DEBUG,INFO" />
</RollingFile>
```

这里将错误日志和INFO日志分开写入。另外我们发现log4j2的日志级别过滤器很难理解，所以实现了一种直接枚举日志级别的过滤器(EnumFilter)，简单好懂。
解决了异常信息堆栈丢失的问题，大家就可以开心的使用如下方式打日志:

```java
logger.error("my message", ex);
```

打出的文本日志举例: 

```
"2018-04-20T15:18:59.773+08:00"	"-"	"dealer"    "dealer.arch"	"projectname"	"-"	"10.1.1.1"	"-"	"-"	"-"	"-"	"-"	"ERROR"	"org.springframework.test.context.TestContextManager"	"prepareTestInstance"	"-"	"234"	"org.springframework.test.context.TestContextManager"	"unknown"	"java.lang.IllegalStateException"	"Failed to load ApplicationContext"	"Caught exception while allowing TestExecutionListener [org.springframework.test.context.web.ServletTestExecutionListener@17a756db] to prepare test instance [com.autohome.daimon.job.service.integration.HawkeyeServiceTest@2d10160a]"	"java.lang.IllegalStateException: Failed to load ApplicationContext
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:124)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'hawkeyeService': Injection of resource dependencies failed; nested exception is org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named 'redisDao' is expected to be of type 'RedisDao' but was actually of type 'RedisDao$$EnhancerBySpringCGLIB$$17f5ad58'
	at org.springframework.context.annotation.CommonAnnotationBeanPostProcessor.postProcessPropertyValues(CommonAnnotationBeanPostProcessor.java:321)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1268)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:98)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:116)
	... 24 more
Caused by: org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named 'redisDao' is expected to be of type 'IRedisDao' but was actually of type 'RedisDao$$EnhancerBySpringCGLIB$$17f5ad58'
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:384)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202)
	... 40 more
"	"b83539ac4a40de0d"
```

目前，我们已经把日志扩展类库开源，希望对别人也有所帮助。github地址: <a href="https://github.com/AutohomeCorp/autolog4j" target="_blank">autolog4j</a>  

另外日志规范只是日志建设的第一步，我们还开源了基于Elasticsearch数据的开源日志监控系统, 欢迎使用。 github地址: <a href="https://github.com/AutohomeCorp/frostmourne" target="_blank">frostmourne(霜之哀伤)</a>  