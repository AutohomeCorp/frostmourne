#编译vue项目
FROM node:8.11.3-slim as frostmourne-vue

RUN mkdir /workspace \
    && chmod +x /workspace
WORKDIR /workspace
COPY ./frostmourne-vue /workspace
RUN true \
    # debian china mirrors
    && sed -i 's/deb.debian.org/mirrors.ustc.edu.cn/g' /etc/apt/sources.list \
    && sed -i 's/security.debian.org/mirrors.ustc.edu.cn/g' /etc/apt/sources.list \
    # timezone to china
    && ln -sf /usr/share/zoneinfo/PRC /etc/localtime \
    && apt-get update \
    && apt-get install -y \
    # node-sass 等编译依赖
    make gcc g++ python \
    # 命令行工具
    zsh curl wget vim git \
    && npm config set registry https://registry.npm.taobao.org \
    && npm install webpack -g \
    && npm install cnpm -g\
    && npm install -g vue-cli \
    && cnpm install \
    && npm install -g @vue/cli \
    && cd /workspace \
    && npm run build:stage

#编译springboot项目
FROM maven:3.6.3-ibmjava-8-alpine as frostmourne-build

RUN true \
    && mkdir -p /opt/frostmourne \
    && mkdir -p /opt/core \
    && mkdir -p /opt/core/mysql \
    && mkdir -p /opt/frostmourne/frostmourne-monitor \
    && mkdir -p /opt/frostmourne/frostmourne-spi \
    && mkdir -p /opt/frostmourne/xxl-job

WORKDIR /opt/frostmourne
COPY ./ /usr/src/mymaven
COPY --from=frostmourne-vue /workspace/target/dist /usr/src/mymaven/frostmourne-monitor/src/main/resources/

RUN chmod +x /opt/frostmourne \
    && cd /usr/src/mymaven \
    && sed -i '/<module>frostmourne-vue/d' pom.xml \
    && mvn clean install -DskipTests \
    && ls -l \
    && cp /usr/src/mymaven/frostmourne-monitor/target/frostmourne-monitor-0.2-SNAPSHOT.zip /opt/core/ \
    && cp /usr/src/mymaven/frostmourne-spi/target/frostmourne-spi-0.2-SNAPSHOT.zip /opt/core/ \
    && cp /usr/src/mymaven/doc/xxl-job/xxl-job-admin-2.1.0.zip /opt/core/ \
    && cp -r /usr/src/mymaven/doc/mysql-schema/* /opt/core/mysql \
    && rm -rf /usr/src/mymaven/*

#生成镜像
FROM maven:3.6.3-ibmjava-8-alpine
MAINTAINER Pinkr <1621523332@qq.com>
LABEL description="Frostmourne ELK日志监控告警系统"

WORKDIR /opt/frostmourne
COPY --from=frostmourne-build /opt /opt
RUN { \
            echo "#!/bin/bash"; \
            echo "/opt/frostmourne/xxl-job/scripts/startup.sh"; \
            echo "/opt/frostmourne/frostmourne-spi/scripts/startup.sh"; \
            echo "/opt/frostmourne/frostmourne-monitor/scripts/startup.sh"; \
            echo "tail -f /dev/null"; \
        } | tee /start.sh \
      &&{ \
            echo "#!/bin/bash"; \
            echo "if [ ! -d \"/opt/frostmourne/frostmourne-monitor/scripts\" ];then"; \
            echo "mkdir -p /opt/frostmourne/frostmourne-monitor &&mkdir -p  /opt/frostmourne/frostmourne-spi "; \
            echo "mkdir -p /opt/frostmourne/xxl-job &&mkdir -p  /opt/frostmourne/mysql"; \
            echo "cp /opt/core/frostmourne-monitor-0.2-SNAPSHOT.zip /opt/frostmourne/frostmourne-monitor/"; \
            echo "cp /opt/core/frostmourne-spi-0.2-SNAPSHOT.zip /opt/frostmourne/frostmourne-spi/"; \
            echo "cp /opt/core/xxl-job-admin-2.1.0.zip /opt/frostmourne/xxl-job/"; \
            echo "cp -r /opt/core/mysql/* /opt/frostmourne/mysql/"; \
            echo "cd /opt/frostmourne/"; \
            echo "unzip ./frostmourne-monitor/frostmourne-monitor-0.2-SNAPSHOT.zip -d ./frostmourne-monitor/"; \
            echo "unzip ./frostmourne-spi/frostmourne-spi-0.2-SNAPSHOT.zip -d ./frostmourne-spi/"; \
            echo "unzip ./xxl-job/xxl-job-admin-2.1.0.zip -d ./xxl-job"; \
            echo ""; \
            echo "sed -i 's/\".jar\" start/\".jar\" start \$PARAMS/p' /opt/frostmourne/xxl-job/scripts/startup.sh"; \
            echo "fi"; \
        } | tee /init.sh \
      && chmod -R 777 /start.sh \
      && chmod -R 777 /init.sh

VOLUME /opt/frostmourne
EXPOSE 9999
EXPOSE 10054
EXPOSE 10055
EXPOSE 8080


CMD /init.sh&&/start.sh
