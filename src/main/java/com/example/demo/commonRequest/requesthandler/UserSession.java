package com.example.demo.commonRequest.requesthandler;

import com.example.demo.config.GetBeanUtil;
import com.example.demo.config.RedisService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author whn
 * 获取用户信息
 */
@Component
public class UserSession {
    private final static String USER_ID = "userId";//用户标识
    private final static String ORG_ID = "orgId";//用户机构标识
    private final static String USER_NAME = "userName";//用户名称
    private final static String LOGIN_NAME = "loginNm";//用户登录名称
    private final static String IP = "ip";//IP地址
    private final static String MAC = "mac";//mac地址
    private final static String OS = "os";//操作系统
    private final static String BROWSER_NAME = "browserName";//浏览器名称
    private final static String BROWSER_VERSION = "browserVersion";//浏览器版本
    private final static String ACCOUNT_TYPE = "account_type";

    public static String getUserId() {
        return getRedisInfo(USER_ID);
    }

    public static String getOrgId() {
        return getRedisInfo(ORG_ID);
    }

    public static String getUserName() {
        return getRedisInfo(USER_NAME);
    }

    public static String getLoginName() {
        return getRedisInfo(LOGIN_NAME);
    }

    public static String getIp() {
        return getRedisInfo(IP);
    }

    public static String getMac() {
        return getRedisInfo(MAC);
    }

    public static String getOs() {
        return getRedisInfo(OS);
    }

    public static String getBrowserName() {
        return getRedisInfo(BROWSER_NAME);
    }

    public static String getBrowserVersion() {
        return getRedisInfo(BROWSER_VERSION);
    }

    public static String getAccountType() {
        return getRedisInfo(ACCOUNT_TYPE);
    }

    /**
     * 根据token获取redis用户信息
     *
     * @param name
     * @return
     */
    @SuppressWarnings("deprecation")
    private static String getRedisInfo(String name) {
        String tokenId = RequestUtil.getTokenInfo("token");
        if (StringUtils.isEmpty(tokenId)) {
            return "";
        }
        Map<Object, Object> userMap = getBean().hmget(tokenId);
        return ObjectUtils.toString(userMap.get(name));
    }

    /**
     * 获取RedisUtil
     *
     * @return
     */
    private static RedisService getBean() {
        return GetBeanUtil.getApplicationContext().getBean(RedisService.class);
    }
}
