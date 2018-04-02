package com.wing.interceptor;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.wing.http.HttpHelper;
import com.wing.utils.Constant;
import com.wing.utils.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Create By: CuiBo
 * Date: 2018/3/14 17:54
 * Description: 拦截收钱吧调用接口，处理签名
 */
@Configuration
@Component
public class PermissionInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("PermissionInterceptor preHandle ...");
        if(!validRequest(httpServletRequest)){
            httpServletResponse.addHeader("Content-Type","text/plain;charset=UTF-8");
            try(PrintWriter writer = httpServletResponse.getWriter()){
                writer.write(ResponseUtil.fail());
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("PermissionInterceptor postHandle ...");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("PermissionInterceptor afterCompletion ...");
    }

    /**
     * @Author: CuiBo
     * @Description: 参数校验
     * @Params:
     * @Return:
     * @Date : 2018/3/15 9:57
     */

    private boolean validRequest(HttpServletRequest request)throws Exception{
        String method = request.getMethod();
        boolean valid = false;
        switch (method){
            case Constant.POST:
                String body = HttpHelper.getBodyString(request);
                if(StringUtils.isEmpty(body)){
                    valid = false;
                    break;
                }
            Map map = JSONObject.parseObject(body);
                if(validParams(map)){
                    valid = true;
                }else{
                    //传输参数错误，可以这里细化
                    valid = false;
                    logger.error("ERROR params:{}",map);
                }
                break;
            case Constant.GET:
                return false;
        }
        return valid;
    }

    /**
     * @Author: CuiBo
     * @Description: 判断传入参数中是否正确
     * @Params:
     * @Return:
     * @Date : 2018/3/15 18:13
     */

    private boolean validParams(Map map){
        if(map.containsKey(Constant.DECODE_USER) && map.containsKey(Constant.DECODE_CODE) && map.containsKey(Constant.DATA) ){
            return true;
        }
        return false;
    }

}
