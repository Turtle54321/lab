package com.xhk.lab.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.xhk.lab.common.ProjectException;
import com.xhk.lab.utils.HttpUtil;
import com.xhk.lab.utils.MobileDetect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import javax.servlet.http.HttpServletRequest;

/**
 * create by xhk on 18/3/4
 */
@Aspect
public class RetFormatAspect implements Ordered {

    //	@Autowired
//    ActionLogDao actionLogDao;
//	@Autowired
//    BackUserActionLogDao backUserActionLogDao;
//	@Autowired
//	BackUserService backUserService;
//	@Autowired
//	PlatformService platformService;
//	@Autowired
//	BackUserActionLogService backUserActionLogService;
    private static Logger logger = LoggerFactory.getLogger(RetFormatAspect.class);
    private String logAction;//日志操作类型信息


    @Around(value = "@annotation(pv)")
    public Object aroundMethod(ProceedingJoinPoint pjd, RetFormat pv) throws Throwable {
        logger.info("enter aspect");
        Object result = null;
        JSONObject jsonObject = new JSONObject();
        try {
            result = pjd.proceed();
            //方法执行之后
            jsonObject.put("error_no",0);
            jsonObject.put("error_message", "");
            jsonObject.put("data", result);
            logger.info("request success,ret="+ jsonObject.toJSONString()+",LOGID="+ HttpUtil.getLogId());

            if(pv.isPage()){
                return result;
            }
        } catch (ProjectException e) {
            jsonObject.put("error_no",e.getErrorNo());
            jsonObject.put("error_message",e.getErrorMsg());
            jsonObject.put("data", null);
            logger.error("request fail,ret=" + jsonObject.toJSONString()+",LOGID="+HttpUtil.getLogId());
        }catch (Throwable e) {
            jsonObject.put("error_no",9999);
            jsonObject.put("error_message","系统错误");
            jsonObject.put("data", null);
            logger.error("request fail,ret=" + jsonObject.toJSONString()+",LOGID="+HttpUtil.getLogId(), e);
        }
        if(pv.isPage()){
            //有异常了
            HttpServletRequest request = HttpUtil.getHttpRequest();
            String userAgent = request.getHeader("User-Agent");
            request.setAttribute("isMobile", MobileDetect.isMobile(userAgent));
            request.setAttribute("msg",jsonObject.get("error_message"));
            return "page/error";

        }else{
            return jsonObject;
        }

    }

    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 1;
    }
}
