#!/bin/bash
source /etc/profile
SERVICE_NAME=frostmourne-monitor

SCRIPTS_DIR=$(cd "$(dirname "$0")";pwd)
dos2unix $SCRIPTS_DIR/../env
source $SCRIPTS_DIR/../env

if [ -z $LOG_DIR ];then
  LOG_DIR=$SCRIPTS_DIR/../logs
  mkdir -p $LOG_DIR
  echo "LOG_DIR: ${LOG_DIR}"
fi

if [ -z $PID_FOLDER ];then
  PID_FOLDER=$SCRIPTS_DIR/../pid
  mkdir -p $PID_FOLDER
  echo "PID_FOLDER: ${PID_FOLDER}"
fi

LOG_FOLDER=$LOG_DIR

export JAVA_HOME
export MODE
export PROFILE
export LOG_FOLDER
export SERVER_PORT
export LOG_DIR
export PID_FOLDER
export JAVA_OPTS="$JAVA_OPTS -Dserver.port=$SERVER_PORT -Dspring.profiles.active=$PROFILE -Dlogs.dir=$LOG_DIR -Dlog.console.level=${LOG_CONSOLE_LEVEL} -Xloggc:$LOG_DIR/heap_trace.txt -XX:HeapDumpPath=$LOG_DIR/HeapDumpOnOutOfMemoryError/ -Djava.security.egd=file:/dev/./urandom -Dlog4j2.formatMsgNoLookups=true"

PATH_TO_JAR=$SERVICE_NAME".jar"
SERVER_URL="http://localhost:$SERVER_PORT"

function checkPidAlive {
    return 0
}

if [ "$(uname)" == "Darwin" ]; then
    windows="0"
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
    windows="0"
elif [ "$(expr substr $(uname -s) 1 5)" == "MINGW" ]; then
    windows="1"
else
    windows="0"
fi

# for Windows
if [ "$windows" == "1" ] && [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]]; then
    tmp_java_home=`cygpath -sw "$JAVA_HOME"`
    export JAVA_HOME=`cygpath -u $tmp_java_home`
    echo "Windows new JAVA_HOME is: $JAVA_HOME"
fi

cd `dirname $0`/..

for i in `ls $SERVICE_NAME-*.jar 2>/dev/null`
do
    if [[ ! $i == *"-sources.jar" ]]
    then
        PATH_TO_JAR=$i
        break
    fi
done

if [[ ! -f PATH_TO_JAR && -d current ]]; then
    cd current
    for i in `ls $SERVICE_NAME-*.jar 2>/dev/null`
    do
        if [[ ! $i == *"-sources.jar" ]]
        then
            PATH_TO_JAR=$i
            break
        fi
    done
fi

if [[ -f $SERVICE_NAME".jar" ]]; then
  rm -rf $SERVICE_NAME".jar"
fi

printf "$(date) ==== Starting ==== \n"

ln $PATH_TO_JAR $SERVICE_NAME".jar"
chmod a+x $SERVICE_NAME".jar"

if [ $PARAMS ];then
    ./$SERVICE_NAME".jar" start $PARAMS
else
    ./$SERVICE_NAME".jar" start
fi

rc=$?;

if [[ $rc != 0 ]];
then
    echo "$(date) Failed to start $SERVICE_NAME.jar, return code: $rc"
    exit $rc;
fi

declare -i counter=0
declare -i max_counter=48 # 48*5=240s
declare -i total_time=0

printf "Waiting for server startup"
until [[ (( counter -ge max_counter )) || "$(curl -X GET --silent --connect-timeout 1 --max-time 2 --head $SERVER_URL | grep "200")" != "" ]];
do
    printf "."
    counter+=1
    sleep 5

    checkPidAlive
done

total_time=counter*5

if [[ (( counter -ge max_counter )) ]];
then
    printf "\n$(date) Server failed to start in $total_time seconds!\n"
    exit 1;
fi

printf "\n$(date) Server started in $total_time seconds!\n"

exit 0;
