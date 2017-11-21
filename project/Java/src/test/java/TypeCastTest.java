/**
 * Created by Administrator on 2017/11/21.
 */

import com.wing.ace.common.TypeCast;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Map;

/**
 * description 类型转换测试
 * author stephen.cui
 * date 2017/11/21 23:53
 **/
public class TypeCastTest {
    private static Logger LOG = Logger.getLogger(TypeCastTest.class);

    @Test
    public void String2MapTest(){
        TypeCast typeCast = new TypeCast();
        String str= "driver-class-name=com.mysql.jdbc.Driver, max-active=10, password=stephen, url=jdbc:mysql://localhost:3306/stephen?useUnicode=true&characterEncoding=UTF-8, min-idle=0, max-idle=5, username=root";
        Map<String,Object> map = typeCast.string2Map3(str);
        System.out.println(map.toString());
    }

}
