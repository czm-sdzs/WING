package com.wing.ace.mr.workcount;/**
 * Created by Administrator on 2017/11/16.
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * description wordCountMap
 * author stephen.cui
 * date 2017/11/16 0:26
 **/
public class WcMap extends Mapper<LongWritable,Text,Text,IntWritable> {
    private static final IntWritable one = new IntWritable(1);
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.split(" ");
        for(String word : words){
            context.write(new Text(word),one);
        }
    }
}
