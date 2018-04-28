package com.wing.ace.hdfs;/**
 * Created by Administrator on 2017/11/21.
 */



import java.io.*;
import java.net.URI;

/**
 * description 文件操作
 * author stephen.cui
 * date 2017/11/21 22:17
 **/
public class FileUtil {
    private static Logger LOG = Logger.getLogger(FileUtil.class);
    private static final String hdfs = "hdfs://localhost:9000";
    public static FileSystem fs = null;//hdfs文件对象
    public static FileSystem localFs = null;//本地文件对象
    public static Configuration conf = null;
    /**
     * 获取hdfs的文件对象
     */
    public FileSystem getFs(String url,boolean hdfsFlag){
        FileSystem fileSystem = null;
        if(conf ==null){
            conf = new Configuration();
            conf.set("fs.default.name", "hdfs://localhost:9000");
        }
        if(hdfsFlag){//hdfs文件对象
            if(fs ==null){
                try {
                    fs = FileSystem.get(URI.create(url),conf);
                    fileSystem = fs;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                fileSystem = fs;
            }
        }else{
            if(localFs ==null){
                try {
                    localFs = FileSystem.getLocal(conf);
                    fileSystem = localFs;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                fileSystem = localFs;
            }
        }
        return fileSystem;
    }

    public void closeFs(){
        if(fs!=null){
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 读本地文件
     */
    public void readLocalFile(String uri){
        FileSystem fs= null;
        InputStream in = null;
        try {
            System.out.println(System.getProperty("user.dir"));
            in = getFs(null,false).open(new Path(uri));
            IOUtils.copyBytes(in,System.out,4096,false);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeStream(in);
        }
    }


    /**
     * 读取hdfs文件内容
     * @param
     */
    public void readHdfsFile(String hdfsFile){
        InputStream in = null;
        try {
            in = getFs(hdfs,true).open(new Path(hdfsFile));
            IOUtils.copyBytes(in,System.out,4096,false);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeStream(in);
        }
    }

    /**
     * 从本地copy数据到hdfs
     * @param targetPath
     * @param localPath
     */
    public void copyLocalData2Hdfs(String localPath,String targetPath){
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(new File(localPath));//local file
            os = getFs(hdfs,true).create(new Path(targetPath));
            IOUtils.copyBytes(fis,os,4096,true);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(fis !=null){
                    fis.close();
                }
                if(fs != null){
                    fs.close();
                }
                if(os!=null){
                    os.close();
                }
            }catch (Exception e){
              e.printStackTrace();
            }
        }
    }

    /**
     * 判断文件目录或者文件是否存在,以及是否创建
     *
     */
    public boolean isExist(String pathStr){
        boolean isExist = false;
        Path path = new Path(pathStr);
        try {
            isExist = getFs(hdfs,true).exists(path);//
            LOG.info("pathStr isExist: "+isExist);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isExist;
    }

    /**
     * 判断文件目录或者文件是否存在,以及是否创建
     *
     */
    public boolean isExistAndIsCreate(String pathStr,boolean isCreate){
        boolean isExist = false;
        Path path = new Path(pathStr);
        try {
            isExist = getFs(hdfs,true).exists(path);//
            LOG.info("pathStr isExist: "+isExist);
            if(!isExist){//不存在
                if(isCreate){//create path
                    getFs(hdfs,true).create(path);
                    LOG.info("mkdir "+pathStr+" is success!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isExist;
    }

    /**
     * 创建 目录
     */
    public void createFolder(String folderStr){
        Path folder = new Path(folderStr);
        try {
            if(! isExist(folderStr)){
                getFs(hdfs,true).mkdirs(folder);
                LOG.info("create folder: "+folderStr +" success!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 创建 文件
     * @param pathStr
     * @return
     */
    public OutputStream createFile(String pathStr){
        Path path = new Path(pathStr);
        OutputStream out = null;
        try {
            out = getFs(hdfs,true).create(path);
            LOG.info("create file: "+pathStr +" success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }


    /**
     * 删除目录或文件夹
     * @param pathStr
     */
    public void rmr(String pathStr){
        Path path = new Path(pathStr);
        try {

            boolean deleteFlag = getFs(hdfs,true).deleteOnExit(path);
            if(deleteFlag){
                LOG.info("rmr: "+pathStr+" success!");
            }else{
                LOG.info(pathStr+" not exist!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeFs();
        }
    }
}
