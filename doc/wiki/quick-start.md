## Quick Start

### 第一步：数据库准备

依赖数据库有两个xxl-job和frostmourne，语句分别在[xxl-job.sql](../xxl-job/xxl-job.sql)和[frostmourne.sql](../mysql-schema/frostmourne.sql)
如果你已经有自己的测试库，你可以在自己的测试库中执行；如果没有，可以用mysql-docker启动一个，下面是一个mysql的docker-compose例子供参考使用：

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

直接将内容保存为docker-compose.yml文件存到本地目录mysql中，进入目录执行如下命令就可以启动一个mysql实例

```bash
docker-compose up
```

更多有关mysql-docker的内容请参考[官方地址](https://hub.docker.com/_/mysql)

### 第二步：配置参数

请将本项目的[docker-compose.yml](../docker/docker-compose.yml)文件保存到本地目录frostmourne中，看情况修改其中的数据库连接。
默认是我本地环境，我用的是docker for windows，容器之间通过host.docker.internal来访问主机，如果你和我环境一样，就不用任何改动。

如果你是自己的另外mysql实例，请将host.docker.internal修改为你的mysql服务地址，并同时修改mysql用户和密码。注意xxl-job和frostmourne-monitor两个服务都有
mysql连接配置，都需要修改。

然后就是frostmourne-spi服务的邮箱配置和其他相关发送方式相关配置。你可以只先配置邮箱来测试一下，后面再加入其他配置

```yaml
environment:
      email.smtp.host: smtp.qq.com
      email.smtp.port: 25
      email.smtp.auth: 'true'
      email.sender: xxx@qq.com
      email.sender.password: xxx
      dwz45.token: t8HGzRNv9TmvqUFICNoW3SaYNA1C9OAC
      wechat.corpid:
      wechat.agentid:
      wechat.secret=:

```

> 镜像服务说明：由于dockerhub推送镜像太难受，所以用阿里云的镜像管理代替，dockerhub也会更新，但是由于经常失败，可能不会那么及时。所以尽可能
使用阿里云。dockerhub的镜像地址为：frostmourne2020/frostmourne:tag

### 第三步：启动

进入第二步的frostmourne目录，执行如下命令

```yaml
docker-compose up
```

如果启动失败，请进入容器查看相关日志，日志目录为：

```
/opt/frostmourne/xxl-job-admin/logs
/opt/frostmourne/frostmourne-spi/logs
/opt/frostmourne/frostmourne-monitor/logs
```

启动成功后。frostmounre-monitor地址为： http://localhost:10054 ;
xxl-job-admin地址为: http://localhost:10052/xxl-job-admin ;





