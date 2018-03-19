package com.wing.ace.common.file;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * 读取文件工具类
 * Created by Administrator on 2017/11/21.
 */
public class FileUtil {
    private static Logger LOG = Logger.getLogger(FileUtil.class);

    /**
     * 读取文件
     * @param file
     */
    public String readFile(File file){
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String s =null;
            try {
                while ((s = reader.readLine())!=null){
                    sb .append(System.lineSeparator()+s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(reader !=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
