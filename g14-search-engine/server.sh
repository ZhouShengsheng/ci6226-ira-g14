#!/bin/sh
# Desc: Search engin service script.
# Author: Zhou Shengsheng

# java env
export JAVA_HOME=/opt/jdk1.8.0_112
export JRE_HOME=$JAVA_HOME/jre

# configurations
APP_NAME=search-engine
SERVICE_DIR=/home/shengsheng/Workspaces/IR/projects/$APP_NAME
SERVICE_NAME=g14-$APP_NAME
JAR_NAME=$SERVICE_NAME\.jar
PID=$SERVICE_NAME\.pid

cd $SERVICE_DIR

case "$1" in

    start)
        nohup $JRE_HOME/bin/java -Xms2g -Xmx4g -jar -Dspring.profiles.active=prod $JAR_NAME >/dev/null 2>&1 &
        echo $! > $SERVICE_DIR/$PID
        echo "=== starting $SERVICE_NAME"
        ;;

    stop)
        kill `cat $SERVICE_DIR/$PID 2> /dev/null` 2> /dev/null
        rm -rf $SERVICE_DIR/$PID
        echo "=== stopping $SERVICE_NAME"

        sleep 2
        P_ID=`ps -ef | grep -w "$SERVICE_NAME" | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" != "" ]; then
            kill -9 $P_ID 2> /dev/null
        fi
        ;;

    restart)
        $0 stop
        sleep 2
        $0 start
        ;;

    *)
        # restart
        $0 restart
        ;;
esac
exit 0
