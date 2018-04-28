package com.wing.ace.mr.workcount;/**
 * Created by Administrator on 2017/11/16.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * description wordCount
 * author stephen.cui
 * date 2017/11/16 0:25
 **/
public class WordCount {

    public static void main(String[] args) throws IOException {

        String dst = "hdfs://localhost:9000/data/wordCountIn.txt";
        //输出路径，必须是不存在的，空文件加也不行。
        String dstOut = "hdfs://localhost:9000/wing/tmp/tmp1";


        Configuration conf = new Configuration();
        Job job = new Job(conf,"wordCount");//job的位置和名字
        job.setJarByClass(WordCount.class);//

        //设置job运行的map和reducer
        job.setMapperClass(WcMap.class);
        job.setReducerClass(WcReducer.class);
        //设置计算数据的输入输出格式
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        //设置job 的数据数据的路径

        FileInputFormat.addInputPath(job,new Path(dst));
        FileOutputFormat.setOutputPath(job,new Path(dstOut));



    }
}
