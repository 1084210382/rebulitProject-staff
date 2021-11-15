package com.example.demo.common.commonRequest;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.config.GetBeanUtil;
import com.example.demo.config.RedisService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author whn
 * 获取用户信息
 */
@Component
public class UserSession {

    private final static String USER_ID = "userId";//用户标识
    private final static String USER_NAME = "userName";//用户名称
    private final static String ENTERPRISE_NAME = "enterpriseName";//企业名称
    private final static String LOGIN_NAME = "loginName";//用户登录名称
    private final static String AREA_LIST = "areaList";//政府用户管辖范围
    private final static String ENT_CREDIT_CODE = "entCreditCode";//企业用户社会信用代码
    private final static String COA_COAE = "coaCode";//企业用户许可证号代码
    private final static String USER_TYPE = "userType";//用户类型
    private final static String GA_ID = "gaId";//
//    private final static String ORG_ID = "orgId";//用户机构标识
//    private final static String IP = "ip";//IP地址
//    private final static String MAC = "mac";//mac地址
//    private final static String OS = "os";//操作系统
//    private final static String BROWSER_NAME = "browserName";//浏览器名称
//    private final static String BROWSER_VERSION = "browserVersion";//浏览器版本
//    private final static String ACCOUNT_TYPE = "account_type";

    public static String getEntCreditCode() {
        return getRedisInfo(ENT_CREDIT_CODE);
    }
    public static String getCoaCode() {
        return getRedisInfo(COA_COAE);
    }
    public static Integer getUserId() {
        return getRedisIntInfo(USER_ID);
    }
    public static Integer getGaId() {
        return getRedisIntInfo(GA_ID);
    }
    public static List<Integer> getAreaList() {
        return getRedisListInfo(AREA_LIST);
    }
    public static String getUserName() {
        return getRedisInfo(USER_NAME);
    }
    public static String getLoginName() {
        return getRedisInfo(LOGIN_NAME);
    }
    public static Integer getUserType() {
        return getRedisIntInfo(USER_TYPE);
    }
    public static String getEnterpriseName() {
        return getRedisInfo(ENTERPRISE_NAME);
    }
    //    public static String getOrgId() {
//        return getRedisInfo(ORG_ID);
//    }
//    public static String getIp() {
//        return getRedisInfo(IP);
//    }
//
//    public static String getMac() {
//        return getRedisInfo(MAC);
//    }
//
//    public static String getOs() {
//        return getRedisInfo(OS);
//    }
//
//    public static String getBrowserName() {
//        return getRedisInfo(BROWSER_NAME);
//    }
//
//    public static String getBrowserVersion() {
//        return getRedisInfo(BROWSER_VERSION);
//    }
//
//    public static String getAccountType() {
//        return getRedisInfo(ACCOUNT_TYPE);
//    }
    /**
     * 根据token获取redis用户String信息
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
     * 根据token获取redis用户ID
     *
     * @param name
     * @return
     */
    private static Integer getRedisIntInfo(String name) {
        String tokenId = RequestUtil.getTokenInfo("token");
        if (StringUtils.isEmpty(tokenId)) {
            return -1;
        }
        Map<Object, Object> userMap = getBean().hmget(tokenId);
        return Integer.parseInt(userMap.get(name).toString());
    }
    /**
     * 根据token获取redis用户信息中的LIST信息
     *
     * @param listName
     * @return
     */
    private static List<Integer> getRedisListInfo(String listName) {
        String tokenId = RequestUtil.getTokenInfo("token");
        if (StringUtils.isEmpty(tokenId)) {
            return null;
        }
        Map<Object, Object> userMap = getBean().hmget(tokenId);
        List<Integer> list = JSONArray.parseArray(userMap.get(listName).toString(),Integer.class);
//        List<Integer> list =  StringUtil.splitToListInt(userMap.get(listName).toString());
        return list;
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
