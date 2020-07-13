# 如何使用docker快速安装和使用frostmourne
docker部署有两种方式：
* 一、从dockerhub拉取编译好的镜像直接运行 
* 二、从git拉取代码并通过Dockerfile自动编译并运行

## 一、从dockerhub拉取编译好的镜像直接运行 
从dockerhub拉取镜像直接运行步骤非常简单，而且快速。我们只需要执行命令从dockerhub拉取镜像下来，然后创建一个docker-compose.yml文件，即可运行

    docker pull frostmourne2020/frostmourne:latest

创建docker-compose.yml文件：
    
    version: '3.6'
    services:
      xxl-job:
        image: frostmourne2020/frostmourne:latest
        container_name: xxl-job
        environment:
          - PARAMS= --spring.datasource.url=jdbc:mysql://127.0.0.1:3306/xxl_job?Unicode=true&characterEncoding=UTF-8 --spring.datasource.username=root --spring.datasource.password=root
        ports:
          - "18080:10052"
        expose:
          - "18080"
        volumes:
          - ./runtime:/opt/frostmourne
        networks:
          frostmourne_net:

        command: bash -c "/init.sh&&/opt/frostmourne/xxl-job/scripts/startup.sh&&tail -f /dev/null"

      frostmourne-spi:
        image: frostmourne2020/frostmourne:latest
        container_name: frostmourne-spi

        ports:
          - "10055:10055"
        expose:
          - "10055"
        volumes:
          - ./runtime:/opt/frostmourne
        networks:
          frostmourne_net:

        command: bash -c "/init.sh&&/opt/frostmourne/frostmourne-spi/scripts/startup.sh&&tail -f /dev/null"

      frostmourne-monitor:
        image: frostmourne2020/frostmourne:latest
        container_name: frostmourne-monitor
        environment:
          - PARAMS= --xxljob_mock=false --frostmourne_spi_mock=false --xxl.job.admin.addresses=http://xxl-job:10052/xxl-job-admin --xxl.job.executor.id=2 --xxl.job.executor.appname=frostmourne --druid.datasource.frostmourne.url=jdbc:mysql://127.0.0.1:3306/frostmourne?characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai --druid.datasource.frostmourne.username=root --druid.datasource.frostmourne.password=root --frostmourne.spi.service-addr=http://frostmourne-spi:10055 --frostmourne.monitor.address=http://192.168.1.222:10054
        ports:
          - "10054:10054"
          - "9999:9999"
        expose:
          - "10054"
          - "9999"
        volumes:
          - ./runtime:/opt/frostmourne
        networks:
          frostmourne_net:

        command: bash -c "/init.sh&&/opt/frostmourne/frostmourne-monitor/scripts/startup.sh&&tail -f /dev/null"

    networks:
      frostmourne_net:
    
将里面的配置改为您自己的，然后执行启动命令即可启动：

    docker-compose up
  
## 二、从git拉取代码并通过Dockerfile自动编译并运行
docker可以很方便的实现frostmourne的编译部署和使用,我们先从github上将frostmourne代码拉下来

    git clone https://github.com/AutohomeCorp/frostmourne.git

代码拉取下来后我们能看到根目录下有 **Dockerfile和docker-compose.yml**这两个文件
### 编译frostmourne:

    docker build -t frostmourne:latest ./


编译过程可能比较慢,编译成功后,我们通过命令查看镜像
    
    docker images

    [root@localhost frostmourne]# docker images
    REPOSITORY                                     TAG                      IMAGE ID            CREATED             SIZE
    frostmourne                                    latest                   e6a1aca0312c        15 hours ago        1.54 GB

### 启动frostmourne:
启动之前我们要先确保数据库已经还原到您的mysql中,将mysql-schema中的sql还原到frostmourne数据库.将xxl-job中的xxl-job.sql还原到xxl_job数据库
编辑docker-compose,将其中的sql地址和账号密码更换为您的

在根目录创建runtime文件夹,然后执行命令:

    docker-compose up
    #守护进程模式启动
    docker-compose up -d
即可启动项目

访问地址:

* localhost:18080 xxl-job
* localhost:10054 frostmourne

### 停用服务:

    docker-compose stop

### 如何保证服务高可用?
镜像本身是支持将不同的服务分别部署在不同的服务器的,我们只需要将docker-compose中的启动命令拆分出来,单独在不同的服务器部署即可,通过supervisor来启动可以保证服务不会意外挂掉

### 启动失败问题汇总:

在docker-compose启动monitor过程中,在日志中出现:

     >>>>>>>>>>> xxl-job registry error, registryParam:RegistryParam{registGroup='EXECUTOR', registryKey='frostmourne', registryValue='172.19.0.2:9999'}
 解决办法:
 请检查xxl-job是否有启动成功,或docker-compose中的端口配置是否正确
 
 xxl-job在启动过程中出现:
 
    ***************************
    
    Description:
    
    Cannot determine embedded database url for database type NONE
    
    Action:
    
    If you want an embedded database please put a supported one on the classpath. If you have database settings to be loaded from a particular profile you may need to active it (the profiles "dev" are currently active).


这个出现这个问题请将./runtime/xxl-job/application.properties中的内容替换为以下内容即可:

    
    
    
    ### xxl-job, datasource
    spring.datasource.url=
    spring.datasource.username=
    spring.datasource.password=
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    
    ### xxl-job email
    spring.mail.host=smtp.qq.com
    spring.mail.port=25
    spring.mail.username=xxx@qq.com
    spring.mail.password=xxx
    
    ### xxl-job, access token
    xxl.job.accessToken=
    
    ### xxl-job, i18n (default empty as chinese, "en" as english)
    xxl.job.i18n=
    
   
    ### xxl-job, log retention days
    xxl.job.logretentiondays=30
