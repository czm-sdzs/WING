#!/bin/sh  

id=`ps -ef | grep "java -jar dap-gateway-businessloan.jar" | grep -v "grep" | awk '{print $2}'`  
echo $id
if [ ! -z "$id" ]; then
  echo "Killing process $id"
  kill -9 $id
fi
  source ./start.sh
