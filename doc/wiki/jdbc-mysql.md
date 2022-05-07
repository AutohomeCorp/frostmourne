## Mysql数据监控指南

以某个表的数据监控为例子，说明创建一个mysql数据监控的过程

### 1. 添加Mysql数据源

打开页面：数据管理->数据源，点击新增按钮，弹出窗口，填写字段并保存。

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/mysql-datasource.png" />

* 服务地址为mysql的jdbc链接地址

### 2. 添加Mysql数据名

打开页面：数据管理->数据名，点击新增按钮，弹出窗口，填写字段并保存。

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/mysql-dataname.png" />

数据名在mysql场景可以理解为表名，主要为了维护时间字段

### 3. 添加监控

打开页面：监控管理->监控编辑。填入信息并保存，保存前可以先进行测试。

<img src="https://gitee.com/tim_guai/frostmourne/raw/master/doc/img/mysql-alarm.png" />

#### Mysql-报警规则-数值比较

* 查询语句如下，表示查询某表的数据

    ```sql
    select * from table1
    ```

* 根据选择的数据名的时间字段以及数值比较或同比比较报警规则，实际执行sql如下

    ```sql
    -- 查询数据条数
    select count(*) from (select * from table1 where 1=1 and created_at>=? and created_at<?)
    -- 查询最新一条数据
    select * from table1 where 1=1 and created_at>=? and created_at<? order by created_at desc limit 1
    ```

> 注意不要在查询语句里添加时间查询条件，因为时间需要在报警规则里设置，由程序自动附加上去   
> 查询语句必须包含```where```   

#### Mysql-报警规则-表达式规则

* 查询语句如下

```sql
SELECT * FROM alarm_log WHERE create_at > ADDDATE(now(), INTERVAL '-3' DAY) ORDER BY id DESC
```

任意可执行查询语句均可，可以各种join都上，你随意。

> 注意表达式规则不需要配置查询时间段，所以需要你自己在语句里利用数据库自带的时间函数做时间限制

#### 报警规则填写

报警规则和Elasticsearch数据监控无异

#### 报警模板

本例设置的报警模板如下，其中```${db_name}```、```${node_name}```为查询语句查询的列名

```
表最近${TIME_WINDOW}分钟内dbtree新增${NUMBER}个表信息。最近一条信息:
数据库名称: ${db_name}
节点名称: ${node_name}
```

配置完就可以进行测试，和保存运行了。 mysql还没有查询页面所以，默认没有查询短链接。