#!/bin/bash

startFrostmourneMonitor() {
  echo "frostmourne-monitor"
  if [ ! -d "/opt/frostmourne/frostmourne-monitor" ];then
    mkdir -p /opt/frostmourne/frostmourne-monitor
    unzip /opt/frostmourne/frostmourne-monitor.zip -d /opt/frostmourne/frostmourne-monitor
  fi
  /opt/frostmourne/frostmourne-monitor/scripts/startup.sh
}

startFrostmourneMonitor

tail -f /dev/null