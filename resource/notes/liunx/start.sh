#!/bin/bash
id=`ps -ef | grep "java -jar dap-gateway-businessloan.jar" | grep -v "grep" | awk '{print $2}'`
if [ ! -z "$id" ]; then
 echo "Application started"
else
 echo "starting Application..."
 nohup java -jar dap-gateway-businessloan.jar >/dev/null 2>&1 &
fi
