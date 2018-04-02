package com.wing.common.file;


import com.wing.ace.common.jdbc.JdbcUtil;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 读取文件使用
 * Created by Administrator on 2017/11/21.
 */
public class FileConcrete {
    private static Logger LOG = Logger.getLogger(FileConcrete.class);
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    private FileUtil fileUtil;
    /**
     * 读取text文件
     */
    public void readTextFile(){
        fileUtil = new FileUtil();
        String textPath = "E:\\git_repository\\pro\\WING\\project\\springBootRest\\data\\bigLottoData\\BigLotto.txt";
        File file = new File(textPath);
        String str = fileUtil.readFile(file);
    }

    /**
     *
     */
    public void saveData2Mysql(){
        long current = System.currentTimeMillis();
        int errorCount = 0;
        int successCount =0;
        BufferedReader reader = null;
        PreparedStatement ps = null;
        Connection connection = JdbcUtil.getConnect();
        String sql = "insert into big_lotto(date_no,r1,r2,r3,r4,r5,b1,b2,fp,date) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);  // 关闭事务自动提交
            StringBuilder sb = new StringBuilder();
            String textPath = "E:\\git_repository\\pro\\WING\\project\\springBootRest\\data\\bigLottoData\\BigLotto.txt";
            File file = new File(textPath);
            reader  = new BufferedReader(new FileReader(file));
            String line =null;
                while ((line = reader.readLine())!=null){
                    //解析读取的每行数据
                    String[] data = line.split(" ");
                    if(data.length<15){
                        LOG.info("error data: "+line);
                        errorCount ++;
                    }else{
                        ps.setString(1,data[0]);
                        ps.setString(2,data[1]);
                        ps.setString(3,data[2]);
                        ps.setString(4,data[3]);
                        ps.setString(5,data[4]);
                        ps.setString(6,data[5]);
                        ps.setString(7,data[6]);
                        ps.setString(8,data[7]);
                        ps.setString(9,data[9]);
                        Date udate = sf.parse(data[14]);
                        java.sql.Date sdate = new java.sql.Date(udate.getTime());
                        ps.setDate(10,sdate);
                        ps.addBatch();
                        successCount ++;
                    }
                }
                ps.executeBatch();
                connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.psClose(ps);
            JdbcUtil.connClose(connection);
            LOG.info("save success "+successCount+" item text data to mysql expend milliscond "+ (System.currentTimeMillis() - current));
            LOG.info("error "+errorCount+" item");
        }
    }
}
