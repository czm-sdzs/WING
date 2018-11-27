package com.wing.pi.tools.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Create By: CuiBo
 * Date: 2018/8/3 9:29
 * Description: 自动生产sql
 */
public class AutoCreateSql {

    public static void main(String[] args) {
        try {
            bdm_src_meta_table_field_s_data();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 生成埋点元数据表sql
     */
    public static void bdm_src_meta_table_field_s_data()throws Exception{
        // List<Map> Listmap = ReadAndWriteFile.read_ky_text("sql_conf.txt");
        List<Map> Listmap = ReadAndWriteFile.read_ky_json("sql_conf.json");
        List<String> listStr = new ArrayList<String>();
        for(Map map : Listmap){
            //解析报文
            String msg = map.get("msg").toString();
            System.out.println(msg);
            String dbName = map.get("db_name").toString();
            String userName = map.get("user_name").toString();
            String operator = map.get("operator").toString();
            String type = map.get("type").toString();
            String tableName = "s_data_"+map.get("log_no").toString().toLowerCase();
            String operatorTime = DTime.getNowSDate();
            Map<String,String> maps =  (Map) JSONObject.parse(msg);
            String _null = "NULL";
            int i=1;
            //生成表
            StringBuilder tableStr = new StringBuilder("INSERT INTO src_meta_table (`db_name`, `user_name`," +
                "`table_name`, `schema_name`, `table_comment`, `ods_table`, `dwd_table`, `inc_cond`, `dwd_inc_flag`," +
                "`inc_count`, `partition_group`, `status`, `remark`, `update_time`, `update_user`, `monitor_flag`) VALUES ");
            tableStr.append("(");
            tableStr.append("'").append(dbName).append("'");//db_name
            tableStr.append(",'").append(userName).append("'");//user_name
            tableStr.append(",'").append(tableName).append("'");//table_name
            tableStr.append(",'").append("'");//schema_name
            tableStr.append(",").append(_null);//table_comment
            tableStr.append(",'").append("ods.").append(tableName).append("'");//ods_table
            tableStr.append(",'").append("dwd.").append(tableName).append("_").append(type).append("'");//dwd_table
            tableStr.append(",").append(_null);//inc_cond
            tableStr.append(",'Y'");//dwd_inc_flag
            tableStr.append(",").append(_null);//inc_count
            tableStr.append(",").append(_null);//partition_group
            tableStr.append(",'0'");//status
            tableStr.append(",").append(_null);//remark
            tableStr.append(",'").append(operatorTime).append("'");//update_time
            tableStr.append(",'").append(operator).append("'");//update_user
            tableStr.append(",").append("'0'");//monitor_flag
            tableStr.append(");");
            listStr.add(tableStr.toString());

            //生成字段
            for(String str : maps.keySet()){
                StringBuilder sb = new StringBuilder("INSERT INTO src_meta_field (`db_name`, `user_name`, " +
                        "`table_name`, `field_name`, `field_comment`, `field_type`, `field_length`, " +
                        "`pk_flag`, `fk_flag`, `split_flag`, `encrypt_type`, `increment_flag`, `hive_field_name`, " +
                        "`update_time`, `update_user`, `sort_id`) VALUES ");
                sb.append("(");
                sb.append("'").append(dbName).append("'");//db_name
                sb.append(",'").append(userName).append("'");//user_name
                sb.append(",'").append(tableName).append("'");//table_name
                sb.append(",'").append(str).append("'");//field_name
                sb.append(",'").append("'");//field_comment
                sb.append(",'").append("VARCHAR2(128)").append("'");//field_type
                sb.append(",").append(_null);//field_length
                sb.append(",'").append("'");//pk_flag
                sb.append(",").append(_null);//fk_flag
                sb.append(",'").append("'");//split_flag
                sb.append(",'").append("'");//encrypt_type
                sb.append(",'").append("'");//increment_flag
                sb.append(",'").append(str).append("'");//hive_field_name
                sb.append(",'").append(operatorTime).append("'");//update_time
                sb.append(",'").append(operator).append("'");//update_user
                sb.append(",").append(i).append(");");//sort_id
                listStr.add(sb.toString());
                i++;
            }
        }

        ReadAndWriteFile.write_text("out.txt",listStr);
    }
}
