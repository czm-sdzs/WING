#!/bin/bash  
id=`ps -ef | grep "java -jar dap-gateway-businessloan.jar" | grep -v "grep" | awk '{print $2}'`  
if [ ! -z "$id" ]; then
 kill -9 $id
 echo "killed process $id"
fi

