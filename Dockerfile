FROM maven:3.6.3-ibmjava-8-alpine

MAINTAINER Pinkr <1621523332@qq.com>

LABEL description="Frostmourne ELK日志监控告警系统"
#安装node环境
RUN true \
    &&sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories \
    && apk update \
    && apk add make \
    && apk add gcc \
    && apk add g++ \
    && apk add python3 \
    && cd /usr/local/bin \
    && ls -l \
    && ln -s python3 python \
    && python3 --version \
    && apk add curl \
    && apk add vim \
    && apk add git \
    && apk add nodejs \
    && node -v \
    && apk add npm \
    && npm -v

RUN mkdir -p /opt/frostmourne \
    && mkdir -p /opt/core \
    && mkdir -p /opt/frostmourne/frostmourne-monitor \
    && mkdir -p /opt/frostmourne/frostmourne-spi \
    && mkdir -p /opt/frostmourne/xxl-job
 
COPY ./ /usr/src/mymaven
RUN cd /usr/src/mymaven && ls -l

WORKDIR /opt/frostmourne
RUN chmod +x /opt/frostmourne

#编译node项目
#RUN true \
#    && cd /opt/frostmourne/frostmourne-vue \
#    && npm config set registry https://registry.npm.taobao.org \
#    && npm install webpack -g \
#    && npm install -g vue-cli \
#    && npm install \
#    && npm run build:stage

#编译jar包

RUN cd /usr/src/mymaven \
    && npm config set registry https://registry.npm.taobao.org \
    && mvn clean install -DskipTests \
    && ls -l

#整理文件
RUN cp /usr/src/mymaven/frostmourne-monitor/target/frostmourne-monitor-0.2-SNAPSHOT.zip /opt/core/ \
    && cp /usr/src/mymaven/frostmourne-spi/target/frostmourne-spi-0.2-SNAPSHOT.zip /opt/core/ \
    && cp /usr/src/mymaven/doc/xxl-job/xxl-job-admin-2.1.0.zip /opt/core/ \
    && cp -r /usr/src/mymaven/doc/mysql-schema/* /opt/core/mysql \
    && rm -rf /usr/src/mymaven/*

#RUN cp /usr/src/mymaven/frostmourne-monitor/target/frostmourne-monitor-0.2-SNAPSHOT.zip /opt/frostmourne/frostmourne-monitor/ \
#    && unzip /opt/frostmourne/frostmourne-monitor/frostmourne-monitor-0.2-SNAPSHOT.zip \
#    && cp 


RUN true \
   &&{ \
        echo "#!/bin/bash"; \
        echo "if [ ! -d \"/opt/frostmourne/frostmourne-monitor/scripts\" ];then"; \
        echo "mkdir -p /opt/frostmourne/frostmourne-monitor &&mkdir -p  /opt/frostmourne/frostmourne-spi "; \
        echo "mkdir -p /opt/frostmourne/xxl-job &&mkdir -p  /opt/frostmourne/mysql";
        echo "cp /opt/core/frostmourne-monitor-0.2-SNAPSHOT.zip /opt/frostmourne/frostmourne-monitor/"; \
        echo "cp /opt/core/frostmourne-spi-0.2-SNAPSHOT.zip /opt/frostmourne/frostmourne-spi/"; \
        echo "cp /opt/core/xxl-job-admin-2.1.0.zip /opt/frostmourne/xxl-job/"; \
        echo "cp -r /opt/core/mysql/* /opt/frostmourne/mysql/"; \
        echo "cd /opt/frostmourne/"; \
        echo "unzip ./frostmourne-monitor/frostmourne-monitor-0.2-SNAPSHOT.zip -d ./frostmourne-monitor/"; \
        echo "unzip ./frostmourne-spi/frostmourne-spi-0.2-SNAPSHOT.zip -d ./frostmourne-spi/"; \
        echo "unzip ./xxl-job/xxl-job-admin-2.1.0.zip -d ./xxl-job"; \
        echo ""; \
        echo "fi"; \
        echo "sed -i 's/\".jar\" start/\".jar\" start \$PARAMS/p' /opt/frostmourne/xxl-job/scripts/startup.sh"; \
        echo "/opt/frostmourne/xxl-job/scripts/startup.sh"; \
        echo "/opt/frostmourne/frostmourne-spi/scripts/startup.sh"; \
        echo "/opt/frostmourne/frostmourne-monitor/scripts/startup.sh"; \
        echo "tail -f /dev/null"; \
    } | tee /start.sh \
  && chmod -R 777 /start.sh



VOLUME /opt/frostmourne
EXPOSE 9999
EXPOSE 10054
EXPOSE 10055
EXPOSE 8080


CMD ["/start.sh"]
