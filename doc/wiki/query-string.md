# Elasticsearch Query String 常用语法简易教程

## 字段匹配

* 语法格式 field: value

例1：
 
```
Level: ERROR
```

表示查询日志级别为ERROR的数据记录

## 条件关系 AND, OR, NOT

* AND 表示与关系

例2：

```
Level: ERROR AND Team: dealer.arch
```

表示查询日志级别为ERROR 并且团队是架构组的数据记录;

例3:

```
CustomMessage: (error AND exception)
```

表示查询自定义信息里既包含单词error，也包含单词exception的数据记录

* OR 表示或关系

例4：
```
Level: ERROR OR CustomMessage: error
```

表示查询日志级别为ERROR或自定义信息里包含单词error的数据记录

例5：
```
Level: (WARN OR ERROR)
```

表示查询日志级别是ERROR或者WARN的数据记录

* NOT 表示非关系

例6：
```
NOT Level: ERROR
```

表示查询日志级别不为ERROR的数据记录

## 使用括号对条件进行组合

例7：
```
CustomMessage: (error OR (warn AND exception)) 
```

表示查询自定义信息里包含error单词的数据记录或者同时包含单词warn和exception的数据记录

## 范围查询

例8：
```
Cost:[100 TO 500]
Cost: (>=100 AND <=500)
Cost: (+>=100 +<=500)
```

三条语句都表示查询耗时Cost大于等于100毫秒并且小于等于500毫秒的数据记录

例9：

```
Cost:{100 TO 500}
Cost: (>100 AND <500)
Cost: (+>100 +<500)
```

三条语句都表示查询耗时Cost大于100毫秒并且小于500毫秒的数据记录。注意和例8比较区间开闭的区别。

例子10:
```
Cost: [500 TO *}
Cost: >=500
```

两条语句都表示查询耗时字段Cost大于等于500毫秒的数据记录。TO右边的*表示正无穷

例子11:
```
Cost: {* TO 100]
Cost: <=100
```

表示查询耗时字段Cost小于等于100毫秒的数据记录。TO左边的*表示负无穷

例子12：
```
LogAt: [2012-01-01 TO 2012-12-31]
```
表示查询时间是2012年的数据记录

这里只是最常用的部分，更多用法请看<a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-query-string-query.html#query-string-syntax" target="_blank">官方文档</a>。



