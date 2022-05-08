## Quick Start

### 第一步：数据库准备

依赖数据库`frostmourne`，语句 [frostmourne.sql](../mysql-schema/frostmourne.sql)
如果你已经有自己的测试库，你可以在自己的测试库中执行；如果没有，可以用`mysql-docker`启动一个，下面是一个`MySQL`的`docker-compose`例子供参考使用：

```yaml
version: '3.1'

services:
  db:
    image: mysql:latest
    command: --default-authentication-plugin=mysql_native_password
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: example
    ports:
      - 3306:3306
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data: {}

```

直接将内容保存为`docker-compose.yml`文件存到本地目录`MySQL`中，进入目录执行如下命令就可以启动一个`MySQL`实例

```bash
docker-compose up
```

更多有关`mysql-docker`的内容请参考[官方地址](https://hub.docker.com/_/mysql)

### 第二步：配置参数

请将本项目的 [docker-compose.yml](../docker/docker-compose.yml) 文件保存到本地目录`frostmourne`中，看情况修改其中的数据库连接。
默认是我本地环境，我用的是`docker for windows`，容器之间通过`host.docker.internal`来访问主机，如果你和我环境一样，就不用任何改动。

如果你是自己的另外`MySQL`实例，请将`host.docker.internal`修改为你的`MySQL`服务地址，并同时修改`MySQL`用户和密码。

> 镜像服务说明：由于`dockerhub`推送镜像太难受，所以用阿里云的镜像管理代替，`dockerhub`也会更新，但是由于经常失败，可能不会那么及时。所以尽可能
使用阿里云。`dockerhub`的镜像地址为：`frostmourne2020/frostmourne:tag`

### 第三步：启动

进入第二步的`frostmourne`目录，执行如下命令

```yaml
docker-compose up
```

如果启动失败，请进入容器查看相关日志，日志目录为：

```
/opt/frostmourne/frostmourne-monitor/logs
```

启动成功后，`frostmounre-monitor`地址为： http://localhost:10054 ;





