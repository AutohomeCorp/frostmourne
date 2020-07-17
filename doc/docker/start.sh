#!/bin/bash

if [ -z "$1" ]; then
        echo "first arg module_name(such as 'all', 'xxl-job', 'frostmourne-spi', 'frostmourne-monitor') could not be empty."
        exit
fi

MODULE_NAME=$1
echo "starting ${MODULE_NAME}"

startXxlJob() {
  echo "starting xxl-job"
  if [ ! -d "/opt/frostmourne/xxl-job-admin" ];then
    mkdir -p /opt/frostmourne/xxl-job-admin
    unzip /opt/frostmourne/xxl-job-admin.zip -d /opt/frostmourne/xxl-job-admin
  fi
  /opt/frostmourne/xxl-job-admin/scripts/startup.sh
}

startFrostmourneSpi() {
  echo "frostmourne-spi"
  if [ ! -d "/opt/frostmourne/frostmourne-spi" ];then
    mkdir -p /opt/frostmourne/frostmourne-spi
    unzip /opt/frostmourne/frostmourne-spi.zip -d /opt/frostmourne/frostmourne-spi
  fi
  /opt/frostmourne/frostmourne-spi/scripts/startup.sh
}

startFrostmourneMonitor() {
  echo "frostmourne-monitor"
  if [ ! -d "/opt/frostmourne/frostmourne-monitor" ];then
    mkdir -p /opt/frostmourne/frostmourne-monitor
    unzip /opt/frostmourne/frostmourne-monitor.zip -d /opt/frostmourne/frostmourne-monitor
  fi
  /opt/frostmourne/frostmourne-monitor/scripts/startup.sh
}

if [ $MODULE_NAME == 'xxl-job' ];then
  startXxlJob
fi

if [ $MODULE_NAME == 'frostmourne-spi' ];then
  startFrostmourneSpi
fi

if [ $MODULE_NAME == 'frostmourne-monitor' ];then
  startFrostmourneMonitor
fi

if [ $MODULE_NAME == 'all' ];then
  startXxlJob
  startFrostmourneSpi
  startFrostmourneMonitor
fi

tail -f /dev/null