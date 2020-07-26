FROM maven:3.6.3-ibmjava-8-alpine
LABEL description="Frostmourne监控平台"
WORKDIR /opt/frostmourne
EXPOSE 9999
EXPOSE 10052
EXPOSE 10053
EXPOSE 10054
ENV XXL_JOB_VERSION=2.1.0
ENV FROSTMOURNE_VERSION=0.3-SNAPSHOT
COPY xxl-job-admin-${XXL_JOB_VERSION}.zip /opt/frostmourne/xxl-job-admin.zip
COPY frostmourne-spi-${FROSTMOURNE_VERSION}.zip /opt/frostmourne/frostmourne-spi.zip
COPY frostmourne-monitor-${FROSTMOURNE_VERSION}.zip /opt/frostmourne/frostmourne-monitor.zip
COPY start.sh /opt/frostmourne/start.sh
RUN dos2unix /opt/frostmourne/start.sh && chmod +x /opt/frostmourne/start.sh
#ENTRYPOINT ["/opt/frostmourne/start.sh"]
#CMD ["all"]