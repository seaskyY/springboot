#!/bin/bash
#
# Java进程启动脚本: start.sh
# 依赖同目录下env.sh
#
# author: kozz
# date: 2014/07/25
#

params="$@"
cd $(dirname $0)
[[ -e env.sh ]] && { 
    . env.sh 
    __z=1
} || {
    echo 'ERROR: env.sh does not exist!'
    exit 1
}

pid=$(get-pid $proj)

[[ $pid -gt 1 ]] && {
    echo "ERROR: the process has already been running. PID: $pid"
    exit 1
}

libs=$(echo $DEPLOY_HOME/lib/*.jar | tr " " ":")

JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dproject=$proj "
JAVA_DEBUG_OPTS=
JAVA_JMX_OPTS=
JAVA_MEM_OPTS=
 

trinity_lib=$(echo lib/*.jar | tr ' ' '\n' | grep trinity)
[[ -n $trinity_lib ]] && JAVA_OPTS="$JAVA_OPTS -javaagent:$DEPLOY_HOME/$trinity_lib "
[[ $params == *debug* ]] && JAVA_DEBUG_OPTS=" -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=6161 "
[[ $params == *jmx* ]] && JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "

BITS=$(java -version 2>&1 | grep -i 64-bit)
[[ -n "$BITS" ]] && {
    JAVA_MEM_OPTS=" -server -Xmx1024m -Xms1024m -Xmn256m -XX:PermSize=128m -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
} || {
    JAVA_MEM_OPTS=" -server -Xms1024m -Xmx1024m -XX:PermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
}

echo "Starting the ${proj}..."
#echo "nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -classpath $DEPLOY_HOME/conf:$libs $entry &>/dev/null &"
nohup java $JAVA_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -classpath $DEPLOY_HOME/conf:$libs $entry &

sleep 2
pid=$(get-pid $proj)
[[ -z "$pid" ]] || echo "Start task successfully. pid: $pid"


