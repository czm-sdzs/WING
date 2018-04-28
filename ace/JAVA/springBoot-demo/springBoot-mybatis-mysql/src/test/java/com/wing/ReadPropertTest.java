package com.wing;

import com.wing.tool.ReadPropert;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Hashtable;

/**
 * Created by Administrator on 2017/11/21.
 */
public class ReadPropertTest {
    private static Logger LOG = Logger.getLogger(ReadPropertTest.class);
    @Test
    public void getC3p0PropertyTest(){
        ReadPropert rp = new ReadPropert();
        Hashtable hash = rp.getC3p0Property();
        LOG.info(hash.get("driver-class-name"));
    }
}
