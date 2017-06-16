#!/bin/bash
#
# 环境相关脚本: env.sh
#
# 项目布局假设：
#   $DEPLOY_HOME/bin/*.sh
#   $DEPLOY_HOME/conf/resource
#   $DEPLOY_HOME/lib/*.jar
#
# 依赖项目下config.properties
# config.properties 必须指定启动入口 e.g. java.main=a.b.c.App
# config.properties 必须指定项目名称 e.g. project_name=xxx-task
#
# author: kozz
# date: 2014/07/25
#

MAIN_ENTRY='java.main'
PROJECT_NAME='project_name'

cd ..
DEPLOY_HOME=$(pwd)

get-pid(){
    ps aux | grep java | grep "[p]roject=$1 " |awk '{print $2}'   
}

conf_file=$(find . -name config.properties | grep -v target | grep -v test | head -1)
[[ -z $conf_file ]] && {
    echo "ERROR: no 'config.properties' found!!!"    
    exit 1
} || {
    proj=$(sed -r "/.*$PROJECT_NAME\s*=/!d;s/.*$PROJECT_NAME\s*=\s*([^[:space:]]+).*/\1/" $conf_file)
    entry=$(sed -r "/.*$MAIN_ENTRY\s*=/!d;s/.*$MAIN_ENTRY\s*=\s*([^[:space:]]+).*/\1/" $conf_file)
    [[ -z $entry ]] && {
        echo "ERROR: no application entry specified. please define 'java.main=a.b.c.App' in config.properties"
        exit 1
    }
    [[ -z $proj ]] && {
        echo "ERROR: no project name specified. please define 'project_name=xxx' in config.properties"
        exit 1
    }
}
