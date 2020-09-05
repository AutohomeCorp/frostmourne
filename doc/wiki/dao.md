# 为什么混用mybatis的xml和dynamic

## 问题

项目的数据库ORM使用的是mybatis，一直以来mybatis的xml配置sql深受国内开发欢迎，因为
它非常的灵活，能比较好的应付快速多变的的迭代需求。但是缺点也比较明显。

* 对于一些简单的查询来说，xml定义过于繁琐
* 太灵活了，稍不注意sql就会写得很复杂，后面维护艰难

mybatis最新推出了新的模块[mybatis-dynamic-sql](https://github.com/mybatis/mybatis-dynamic-sql)，完成代码即sql
的查询，大部分基于生成的代码可以直接写代码完成，不需要写任何sql。

## 我的做法

为了兼顾方便和灵活，我同时在项目里引入了mybatis-dynamic-sql和xml-sql两种方式，让他们互补配合一起完成数据访问。
大部分(90%以上)查询直接用mybatis-dynamic-sql，对于一些很少的需要灵活的稍复杂sql使用xml-sql来完成。既提高了
编码效率，又保留了原来的灵活强大的xml-sql。我们只需要按需选择使用，非常舒适。