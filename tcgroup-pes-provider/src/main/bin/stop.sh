#!/bin/bash
#
# Java进程停止脚本: stop.sh
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
[[ -z "$pid" ]] && {
    echo "ERROR: The $proj is not running!"
    exit 1
}

echo -e "Stopping the $proj ...\c"
kill $pid

waittime=10
while [[ $waittime -gt 0 ]]; do    
    echo -e ".\c"
    sleep 1
    waittime=$((waittime-1))
    pid=$(get-pid $proj)
    [[ -z "$pid" ]] && {
        waittime=10
        break
    }
done

echo
[[ $waittime -gt 0 ]] && echo 'Stop the process successfully!' || echo "Failed to stop the process! PID: $pid"
