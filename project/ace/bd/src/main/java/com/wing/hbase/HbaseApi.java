package com.wing.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Create By: CuiBo
 * Date: 2018/3/29 9:39
 * Description:
 */
public class HbaseApi {
    private Logger logger = LoggerFactory.getLogger(getClass());
    static Configuration conf = HBaseConfiguration.create();
    static Connection conn = null;
    static Random ra = new Random();
    private static String zookeepers="192.168.0.33:2181,192.168.0.229:2181,192.168.0.247:2181,192.168.0.242:2181";


    static {
        //配置访问
        conf.set("hbase.zookeeper.quorum", zookeepers);
        try {
            conn = ConnectionFactory.createConnection(conf);
            System.setProperty("HADOOP_USER_NAME", "hadoop");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(conf.get("hbase.master"));
    }

    public String get(String tablename, String row, String family,String qualifier) throws Exception {
        Table table = conn.getTable(TableName.valueOf(tablename));
        Get get = new Get(Bytes.toBytes(row));
        get.addFamily(family.getBytes());
        get.addColumn(family.getBytes(),qualifier.getBytes());
        Result result = table.get(get);
        System.out.println("Get: " + result);
        return resultToMap(result).toString();
    }


    public void getResult(String tablename, String row, String family,String qualifier) throws IOException {
        Get get =  new Get(Bytes.toBytes("dr_ds_cry"));
        HTable table = new HTable(conf,Bytes.toBytes("dwd:md5_aes"));
        get.addFamily(family.getBytes());
        get.addColumn(family.getBytes(),qualifier.getBytes());
        Result result = table.get(get);
        for(KeyValue keyValue: result.list()){
            System.out.println("family :" + Bytes.toString(keyValue.getFamily()));//列簇名
            System.out.println("qualifier :"+ Bytes.toString(keyValue.getQualifier()));//列名
            System.out.println("value :"+Bytes.toString(keyValue.getValue()));//列对应的值
            System.out.println("timestamp :" +keyValue.getTimestamp());//数据最后更新时间
            System.out.println("---------------------------------");
        }
    }

    //把result转换成map，方便返回json数据
    public Map<String, Object> resultToMap(Result result) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<Cell> listCell = result.listCells();
        Map<String, Object> tempMap = new HashMap<String, Object>();
        String rowname = "";
        List<String> familynamelist = new ArrayList<String>();
        for (Cell cell : listCell) {
            byte[] rowArray = cell.getRowArray();
            byte[] familyArray = cell.getFamilyArray();
            byte[] qualifierArray = cell.getQualifierArray();
            byte[] valueArray = cell.getValueArray();
            int rowoffset = cell.getRowOffset();
            int familyoffset = cell.getFamilyOffset();
            int qualifieroffset = cell.getQualifierOffset();
            int valueoffset = cell.getValueOffset();
            int rowlength = cell.getRowLength();
            int familylength = cell.getFamilyLength();
            int qualifierlength = cell.getQualifierLength();
            int valuelength = cell.getValueLength();

            byte[] temprowarray = new byte[rowlength];
            System.arraycopy(rowArray, rowoffset, temprowarray, 0, rowlength);
            String temprow = Bytes.toString(temprowarray);
//            System.out.println(Bytes.toString(temprowarray));

            byte[] tempqulifierarray = new byte[qualifierlength];
            System.arraycopy(qualifierArray, qualifieroffset, tempqulifierarray, 0, qualifierlength);
            String tempqulifier = Bytes.toString(tempqulifierarray);
//            System.out.println(Bytes.toString(tempqulifierarray));

            byte[] tempfamilyarray = new byte[familylength];
            System.arraycopy(familyArray, familyoffset, tempfamilyarray, 0, familylength);
            String tempfamily = Bytes.toString(tempfamilyarray);
//            System.out.println(Bytes.toString(tempfamilyarray));

            byte[] tempvaluearray = new byte[valuelength];
            System.arraycopy(valueArray, valueoffset, tempvaluearray, 0, valuelength);
            String tempvalue = Bytes.toString(tempvaluearray);
//            System.out.println(Bytes.toString(tempvaluearray));


            tempMap.put(tempfamily + ":" + tempqulifier, tempvalue);
//            long t= cell.getTimestamp();
//            tempMap.put("timestamp",t);
            rowname = temprow;
            String familyname = tempfamily;
            if (familynamelist.indexOf(familyname) < 0) {
                familynamelist.add(familyname);
            }
        }
        resMap.put("rowname", rowname);
        for (String familyname : familynamelist) {
            HashMap<String, Object> tempFilterMap = new HashMap<String, Object>();
            for (String key : tempMap.keySet()) {
                String[] keyArray = key.split(":");
                if (keyArray[0].equals(familyname)) {
                    tempFilterMap.put(keyArray[1], tempMap.get(key));
                }
            }
            resMap.put(familyname, tempFilterMap);
        }

        return resMap;
    }


    public static void main(String[] args) {
        String htable = "dwd:md5_aes";
        String rowKey = "dr_ds_cry";
        String family = "fam";
        String qualifier = "cry11";
        try {
            HbaseApi hbaseApi = new HbaseApi();
            hbaseApi.getResult(htable,rowKey,family,qualifier);
            hbaseApi.get(htable,rowKey,family,qualifier);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
