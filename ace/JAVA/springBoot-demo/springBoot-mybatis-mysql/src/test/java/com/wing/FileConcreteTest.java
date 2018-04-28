package com.wing;

import com.wing.common.file.FileConcrete;
import org.junit.Test;

/**
 * Created by Administrator on 2017/11/21.
 */
public class FileConcreteTest {
    FileConcrete  fileConcrete = new FileConcrete();

    @Test
    public void readTextFileTest(){
        fileConcrete = new FileConcrete();
        fileConcrete.readTextFile();
    }

    @Test
    public void saveData2Mysql(){
        fileConcrete.saveData2Mysql();
    }
}
