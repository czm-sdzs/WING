package com.wing.pi.tools.datasource.hbase.impl;


import com.wing.pi.tools.datasource.hbase.client.HbaseClient;
import com.wing.pi.tools.datasource.hbase.service.HbaseQuery;
import org.apache.hadoop.hbase.client.Put;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cuibo
 * @description test
 * @date 2018/11/22
 */
public class HbaseTest {


    public static void main(String[] args) throws Exception {
        String zkAdds = "192.168.0.33:2181,192.168.0.229:2181,192.168.0.242:2181";
        String user = "dm_ds";
        HbaseClient.initHbaseConf(zkAdds,user);
        write();
        query();
    }


    //test
    private static void write() throws Exception{
        HbaseWriteImpl hbaseWriteImpl = new HbaseWriteImpl();
        List<Put> list = new ArrayList<>();
        String fam = "fam";
        for(int i=0;i<3;i++){
            Put put = new Put((""+i).getBytes());
            put.addColumn(fam.getBytes(),"test".getBytes(),("test_"+i).getBytes());
            list.add(put);
        }
        hbaseWriteImpl.write("dwd:md5_aes",list,2);
    }



    //test
    private static void query() {
        HbaseQuery hbaseQuery = new HbaseQueryImpl();
        System.out.println(hbaseQuery.getListByRowKey("dwd:md5_aes","0"));
    }
}
