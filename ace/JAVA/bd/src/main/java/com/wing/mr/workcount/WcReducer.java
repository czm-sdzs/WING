package com.wing.mr.workcount;/**
 * Created by Administrator on 2017/11/16.
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * description wordCountReducer
 * author stephen.cui
 * date 2017/11/16 0:26
 **/
public class WcReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
    protected void reducer(Text key,Iterable<IntWritable> value,Context context) throws IOException, InterruptedException {
        int sum = 0;
        for(IntWritable item : value){
            sum += item.get();
        }
        context.write(key,new IntWritable(sum));
    }
}
