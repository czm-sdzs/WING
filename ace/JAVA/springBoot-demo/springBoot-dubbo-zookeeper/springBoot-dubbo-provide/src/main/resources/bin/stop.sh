#!/bin/bash  
id=`ps -ef | grep "java -jar dc-ds-provide-1.0-SNAPSHOT.jar" | grep -v "grep" | awk '{print $2}'`
if [ ! -z "$id" ]; then
 kill -2 $id
 echo "killed process $id"
fi

