package com.wing.pi.tools.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

/**
 * Create By: CuiBo
 * Date: 2018/8/3 9:19
 * Description:
 */
public class ReadAndWriteFile {

    public static List<Map> read_ky_text(String fileName)throws FileNotFoundException,IOException{
        List<Map> list = new ArrayList<>();
        String s ="";
        File directory = new File("conf/"+fileName);

        System.out.println("file_path:"+directory.getAbsolutePath());
        BufferedReader br = new BufferedReader(new FileReader(directory));
        Map<String,String> map = new HashMap<String,String>();
        while ((s = br.readLine()) != null){
            String[] ss = s.split("=",2);
            map.put(ss[0],ss[1]);
        }
        list.add(map);
        br.close();
        return list;
    }

    public static List<Map> read_ky_json(String fileName)throws FileNotFoundException,IOException{
        List<Map> list = new ArrayList<>();
        File directory = new File("conf/"+fileName);
        System.out.println("file_path:"+directory.getAbsolutePath());
        FileInputStream fileInputStream = new FileInputStream(directory.getAbsolutePath());
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
        BufferedReader br = new BufferedReader(inputStreamReader);
        String s = null;
        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null){
            sb.append(s);
        }
        br.close();
        list = (List)JSONArray.parse(sb.toString());
        return list;
    }

    public static void write_text(String fileName,List<String> listStr)throws IOException{
        File file = new File("conf/"+fileName);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for(String str:listStr){
            bw.write(str+"\n");
            bw.flush();
        }
        bw.close();
        fw.close();
    }


}
