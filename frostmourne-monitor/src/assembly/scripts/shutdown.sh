#!/bin/bash
SERVICE_NAME=frostmourne-monitor

SCRIPTS_DIR=$(cd "$(dirname "$0")";pwd)
dos2unix $SCRIPTS_DIR/../env
source $SCRIPTS_DIR/../env

export JAVA_HOME
export MODE

if [ -z $PID_FOLDER ];then
  PID_FOLDER=$SCRIPTS_DIR/../pid
  mkdir -p $PID_FOLDER
  echo "PID_FOLDER: ${PID_FOLDER}"
fi

export PID_FOLDER

cd `dirname $0`/..

if [[ ! -f $SERVICE_NAME".jar" && -d current ]]; then
    cd current
fi

if [[ -f $SERVICE_NAME".jar" ]]; then
  chmod a+x $SERVICE_NAME".jar"
  ./$SERVICE_NAME".jar" stop
fi
