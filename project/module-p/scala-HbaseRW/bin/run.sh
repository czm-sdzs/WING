#!/bin/bash
# 执行方法：/home/dwh/srcRootDir/run_job.sh ./
function execJob {
    LIB_DIR=lib
    #将所有的jar组成*.jar,*.jar
    LIB_JARS=`ls -r $LIB_DIR | grep .jar | awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ","`

    echo "==============="
    $echo $LIB_JARS
    echo "==============="

    #export CLASSPATH=.:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar:$CLASSPATH

 spark-submit \
     --master yarn \
     --deploy-mode client \
     --class com.wing.pi.md5aes.HbaseRW \
     --driver-memory 2G \
     --executor-memory 10G \
     --num-executors 5 \
     --executor-cores 2 \
     --conf spark.shuffle.compress=true \
+                                                                                       h
     --jars ${LIB_JARS} \
      Test2-1.0-SNAPSHOT.jar
    [ $? -eq 0 ] || exit 100;

}
     #--jars ${LIB_JARS} \
     #--jars aes-lib.jar,lib/datacrypto-test-1.1.0.jar,lib/datadecrypto-test-1.1.0.jar,lib/data-normal-rule-1.1.0.jar \