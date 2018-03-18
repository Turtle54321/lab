package com.xhk.lab.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * create by xhk on 18/3/2
 */
public class ErrorCodeMap {

    //错误码规范，模块（000），子模块代码（000），错误编码（001）
    public final static int UNKNOWN = 100000001;
    public final static int NOT_FOUND_PARAM = 1000000002;
    public final static int PARAMETER_ERROR = 1000000003;

    private static Map<Integer, String> errorCodeMap = new HashMap<>();
    static {
        errorCodeMap.put(UNKNOWN, "请求出错");
        errorCodeMap.put(NOT_FOUND_PARAM, "找不到参数");
        errorCodeMap.put(PARAMETER_ERROR, "参数格式不对");
    }
}
