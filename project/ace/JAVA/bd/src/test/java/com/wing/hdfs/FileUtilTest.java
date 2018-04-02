package com.wing.hdfs; /**
 * Created by Administrator on 2017/11/22.
 */

import com.wing.hdfs.FileUtil;
import org.junit.Test;

/**
 * description 文件操作工具类测试
 * author stephen.cui
 * date 2017/11/22 15:23
 **/
public class FileUtilTest {
    private static FileUtil fileUtil = new FileUtil();

    @Test
    public void readLocalFileTest() {
        String localtext = "pom.xml";
        fileUtil.readLocalFile(localtext);
    }

    @Test
    public void readHdfsTest() {
        String hdfsFile = "/data/file/word.txt";
        fileUtil.readHdfsFile(hdfsFile);
    }

    @Test
    public void copyLocal2HdfsTest(){
        String localPash = "E:\\git_repository\\pro\\WING\\project\\springBootRest\\data\\bigLottoData\\BigLotto.txt";
        String targetPath = "/tmp/log.log";
        fileUtil.copyLocalData2Hdfs(localPash,targetPath);
    }

    @Test
    public void isExistTest(){
        String pathStr = "/data";
        fileUtil.isExist(pathStr);
    }

    @Test
    public void createFolder(){
        String folderStr="/tmp/0";
        fileUtil.createFolder(folderStr);
    }
    @Test
    public void createFileTest(){
        String pathStr="/tmp/2";
        fileUtil.createFile(pathStr);
    }

    @Test
    public void isExistAndIsCreateTest(){
        String pathStr = "/tmp/5/log.log";
        fileUtil.isExistAndIsCreate(pathStr,true);
    }

    /**
     * 删除测试
     */
    @Test
    public void rmrTest(){
        String folderStr = "/tmp/5";
        String fileStr = "/tmp/2";
        fileUtil.rmr(folderStr);
    }

}