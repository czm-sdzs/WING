package wing.utils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Create By: CuiBo
 * Date: 2018/3/13 17:49
 * Description: 接口返回封装工具类
 */
public class ResponseUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * @Author: CuiBo
     * @Description:  处理成功
     * @Params:
     * @Return:
     * @Date : 2018/3/14 9:10
     */

    public static String succeed(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","00");
        map.put("message","成功");
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();

    }

    /**
     * @Author: CuiBo
     * @Description:  处理成功
     * @Params:
     * @Return:
     * @Date : 2018/3/14 9:10
     */

    public static String succeed(String msg){
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","200");
        map.put("message",msg);
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();

    }

/**
 * @Author: CuiBo
 * @Description: 处理失败
 * @Params:
 * @Return:
 * @Date : 2018/3/14 9:10
 */

    public static String fail(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","01");
        map.put("message","失败");
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }


    /**
     * @Author: CuiBo
     * @Description: 程序异常
     * @Params:
     * @Return:
     * @Date : 2018/3/13 16:10
     */

    public static String error(Exception e){
        Map<String,String> map = new HashMap<String,String>();
        map.put("code","01");
        map.put("message",e.toString());
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }

}
