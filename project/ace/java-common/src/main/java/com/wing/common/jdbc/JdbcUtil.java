package com.wing.common.jdbc;

import com.wing.ace.common.PropertiesLoaderUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Hashtable;

/**
 * Created by Administrator on 2017/11/21.
 */
public class JdbcUtil {

    /**
     *
     */
    public static  Connection  getConnect(){
        PropertiesLoaderUtil rp = new PropertiesLoaderUtil();
        Connection conn =null;
        Hashtable hashtable = rp.getC3p0Property();
        String driver = hashtable.get("driver-class-name").toString();
        String url = hashtable.get("url").toString();
        String username = hashtable.get("username").toString();
        String password = hashtable.get("password").toString();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void psClose(PreparedStatement ps){
        if(ps !=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void connClose(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
