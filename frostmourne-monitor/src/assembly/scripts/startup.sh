#!/bin/bash
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

## check java path
if type -p java; then
    _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    _java="$JAVA_HOME/bin/java"
else
    echo "no found java"
fi

## Adjust memory settings if necessary
export JAVA_OPTS="-Xms256m -Xmx1024m -Xss256k -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=256m -XX:NewRatio=4 -XX:SurvivorRatio=8"

## jdk parameters
if [[ "$_java" ]]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo jdk version: "$version"
    if [[ "$version" < "1.8" ]]; then
        echo "only support jdk version 1.8 ~ 14"
    elif [[ "$version" =~ ^1\.8* ]]; then
        # use UseParNewGC
        export JAVA_OPTS="$JAVA_OPTS -XX:+UseParNewGC -XX:ParallelGCThreads=4 -XX:MaxTenuringThreshold=9 -XX:+UseConcMarkSweepGC -XX:+DisableExplicitGC -XX:+UseCMSInitiatingOccupancyOnly -XX:+ScavengeBeforeFullGC -XX:+UseCMSCompactAtFullCollection -XX:+CMSParallelRemarkEnabled -XX:CMSFullGCsBeforeCompaction=9 -XX:CMSInitiatingOccupancyFraction=60 -XX:+CMSClassUnloadingEnabled -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+CMSPermGenSweepingEnabled -XX:CMSInitiatingPermOccupancyFraction=70 -XX:+ExplicitGCInvokesConcurrent -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC -XX:+HeapDumpOnOutOfMemoryError -XX:-OmitStackTraceInFastThrow"
    elif [[ "$version" > "1.8" ]] && [[ "$version" < "15" ]]; then
        # use G1
        export JAVA_OPTS="$JAVA_OPTS -XX:+UseG1GC -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails"
    else
        echo "support jdk version 1.8 ~ 14"
    fi
fi

## Only uncomment the following when you are using server jvm
#export JAVA_OPTS="$JAVA_OPTS -server -XX:-ReduceInitialCardMarks"

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
