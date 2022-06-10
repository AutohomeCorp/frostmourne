FROM maven:3.6.3-ibmjava-8-alpine
LABEL description="Frostmourne监控平台"
WORKDIR /opt/frostmourne
EXPOSE 10054
ENV FROSTMOURNE_VERSION=0.9-SNAPSHOT
COPY frostmourne-monitor-${FROSTMOURNE_VERSION}.zip /opt/frostmourne/frostmourne-monitor.zip
COPY start.sh /opt/frostmourne/start.sh
RUN dos2unix /opt/frostmourne/start.sh && chmod +x /opt/frostmourne/start.sh
#ENTRYPOINT ["/opt/frostmourne/start.sh"]
#CMD ["all"]
