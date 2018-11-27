package com.wing.pi.tools.datasource.hbase.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

/**
 * Create By: CuiBo
 * Date: 2018/4/8 17:39
 * Description:
 */
public abstract class HbaseAbstract{

    protected  static String zkAddrs =null;

    protected  static String user = null;

    protected  static Configuration conf = HBaseConfiguration.create();

}
