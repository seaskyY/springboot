#!/bin/bash
#
# dump运行状况: dump.sh
# 依赖同目录下env.sh
#
# author: kozz
# date: 2014/07/25
#

cd $(dirname $0)
[[ -e env.sh ]] && { 
    . env.sh 
    __z=1
} || {
    echo 'ERROR: env.sh does not exist!'
    exit 1
}

pid=$(get-pid $proj)

[[ -z "$pid" ]] && {
    echo "ERROR: the project $proj is not running!"
    exit 1
}

DUMP_DIR=$DEPLOY_HOME/dump
[[ -d $DUMP_DIR ]] || mkdir $DUMP_DIR
DUMP_DATE=`date +%Y%m%d%H%M%S`
DATE_DIR=$DUMP_DIR/$DUMP_DATE
[[ -d $DATE_DIR ]] || mkdir $DATE_DIR

echo -e "Dumping the $SERVER_NAME ...\c"
for PID in $PIDS ; do
    jstack $PID > $DATE_DIR/jstack-$PID.dump 2>&1
    echo -e ".\c"
    jinfo $PID > $DATE_DIR/jinfo-$PID.dump 2>&1
    echo -e ".\c"
    jstat -gcutil $PID > $DATE_DIR/jstat-gcutil-$PID.dump 2>&1
    echo -e ".\c"
    jstat -gccapacity $PID > $DATE_DIR/jstat-gccapacity-$PID.dump 2>&1
    echo -e ".\c"
    jmap $PID > $DATE_DIR/jmap-$PID.dump 2>&1
    echo -e ".\c"
    jmap -heap $PID > $DATE_DIR/jmap-heap-$PID.dump 2>&1
    echo -e ".\c"
    jmap -histo $PID > $DATE_DIR/jmap-histo-$PID.dump 2>&1
    echo -e ".\c"
    if [ -r /usr/sbin/lsof ]; then
    /usr/sbin/lsof -p $PID > $DATE_DIR/lsof-$PID.dump
    echo -e ".\c"
    fi
done

if [ -r /bin/netstat ]; then
/bin/netstat -an > $DATE_DIR/netstat.dump 2>&1
echo -e ".\c"
fi
if [ -r /usr/bin/iostat ]; then
/usr/bin/iostat > $DATE_DIR/iostat.dump 2>&1
echo -e ".\c"
fi
if [ -r /usr/bin/mpstat ]; then
/usr/bin/mpstat > $DATE_DIR/mpstat.dump 2>&1
echo -e ".\c"
fi
if [ -r /usr/bin/vmstat ]; then
/usr/bin/vmstat > $DATE_DIR/vmstat.dump 2>&1
echo -e ".\c"
fi
if [ -r /usr/bin/free ]; then
/usr/bin/free -t > $DATE_DIR/free.dump 2>&1
echo -e ".\c"
fi
if [ -r /usr/bin/sar ]; then
/usr/bin/sar > $DATE_DIR/sar.dump 2>&1
echo -e ".\c"
fi
if [ -r /usr/bin/uptime ]; then
/usr/bin/uptime > $DATE_DIR/uptime.dump 2>&1
echo -e ".\c"
fi

echo "OK!"
echo "DUMP: $DATE_DIR"

