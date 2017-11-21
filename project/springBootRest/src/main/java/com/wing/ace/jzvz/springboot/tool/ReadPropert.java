package com.wing.ace.jzvz.springboot.tool;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

/**
 * 读取配置文件
 * Created by Administrator on 2017/11/21.
 */
public class ReadPropert {
    private static org.apache.log4j.Logger LOG = Logger.getLogger(ReadPropert.class);

    /**
     * 读取c3p0
     * @return
     */
    public Hashtable getC3p0Property(){
        Hashtable hashtable = new Hashtable();
        Properties p = new Properties();
        try {
            InputStream is=Thread.currentThread().getContextClassLoader().getSystemResourceAsStream("c3p0.properties");
            p.load(is);
            hashtable = (Hashtable)p;
            LOG.info(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashtable;
    }
}
