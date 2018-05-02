#!/bin/bash
id=`ps -ef | grep "java -jar dc-ds-consume-1.0-SNAPSHOT.jar" | grep -v "grep" | awk '{print $2}'`
if [ ! -z "$id" ]; then
 echo "Application started"
else
 echo "starting Application..."
 nohup java -jar dc-ds-consume-1.0-SNAPSHOT.jar >/dev/null 2>&1 &
fi
