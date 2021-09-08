package com.example.demo.commonRequest.responsehandler;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.config.GetBeanUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author whn
 */
public class ResponseUtil {
    public static String FILEDOWNLOAD = "fileDownload";
    public static String FILEUPLOAD = "fileUpload";
    /**
     * 往response中输出JSON格式内容
     * @param req HttpServletRequest
     * @param resp  HttpServletResponse
     * @param result 转换的对象
     * @param status {@link HttpServletResponse}
     * @throws IOException
     */
    public static void toJSON(List<Map<String, Object>> list, int status) throws IOException{
        HttpServletResponse resp = GetBeanUtil.getServletActionContext().getResponse();
        resp.setStatus(status);
        resp.setHeader("Cache-Control", "no-cache"); //$NON-NLS-1$ //$NON-NLS-2$
        resp.setHeader("Cache-Control", "no-store"); //$NON-NLS-1$ //$NON-NLS-2$
        resp.setContentType(HttpConstants.CONTENT_TYPE_JSON);

        //文件下载前台根据这个来判断是否启用回调函数。
        Cookie cookie = new Cookie(FILEDOWNLOAD, "false");
        cookie.setPath("/");
        resp.addCookie(cookie);

        String response = JsonUtil.stringify(list);
        resp.getWriter().print(response);
    }

    public static void toJSON(Serializable bean, int status) throws IOException{
        HttpServletResponse resp = GetBeanUtil.getServletActionContext().getResponse();
        resp.setStatus(status);
        resp.setHeader("Cache-Control", "no-cache"); //$NON-NLS-1$ //$NON-NLS-2$
        resp.setHeader("Cache-Control", "no-store"); //$NON-NLS-1$ //$NON-NLS-2$
        resp.setContentType(HttpConstants.CONTENT_TYPE_JSON);
        String response = JsonUtil.stringify(bean);
        resp.getWriter().print(response);
    }

    public static void toJSON(Object object, int status) throws IOException{
        HttpServletResponse resp = GetBeanUtil.getServletActionContext().getResponse();
        resp.setStatus(status);
        resp.setHeader("Cache-Control", "no-cache"); //$NON-NLS-1$ //$NON-NLS-2$
        resp.setHeader("Cache-Control", "no-store"); //$NON-NLS-1$ //$NON-NLS-2$
        resp.setContentType(HttpConstants.CONTENT_TYPE_JSON);
        String response = JsonUtil.stringify(object);
        resp.getWriter().print(response);
    }

    /**
     * 生成json字符串, 返回的浏览器状态默认为 {@link HttpServletResponse#SC_OK}
     *
     * @param object
     * @param useUpperCaseKey true使用大写的key，false使用原来的key值
     * @throws IOException
     */
    public static void toJSON(Object object, boolean useUpperCaseKey) throws IOException{
        toJSON(object, useUpperCaseKey, HttpServletResponse.SC_OK);
    }


    /**
     * 生成json字符串
     *
     * @param object
     * @param useUpperCaseKey true使用大写的key，false使用原来的key值
     * @param status
     * @throws IOException
     */
    public static void toJSON(Object object, boolean useUpperCaseKey, int status) throws IOException{
        HttpServletResponse resp = GetBeanUtil.getServletActionContext().getResponse();
        resp.setStatus(status);
        resp.setHeader("Cache-Control", "no-cache"); //$NON-NLS-1$ //$NON-NLS-2$
        resp.setHeader("Cache-Control", "no-store"); //$NON-NLS-1$ //$NON-NLS-2$
        resp.setContentType(HttpConstants.CONTENT_TYPE_JSON);
        Object response = JsonUtil.stringify(object, useUpperCaseKey);
        resp.getWriter().print(response);
    }

    /**
     * 生成json字符串
     *
     * @param object
     * @param useUpperCaseKey true使用大写的key，false使用原来的key值
     * @param status
     * @param SerializerFeature 特征,如SerializerFeature.WriteMapNullValue表示保留map中空的数据
     * @throws IOException
     */
    public static void toJSON(Object object, boolean useUpperCaseKey, int status, SerializerFeature... features) throws IOException{
        HttpServletResponse resp = GetBeanUtil.getServletActionContext().getResponse();
        resp.setStatus(status);
        resp.setHeader("Cache-Control", "no-cache"); //$NON-NLS-1$ //$NON-NLS-2$
        resp.setHeader("Cache-Control", "no-store"); //$NON-NLS-1$ //$NON-NLS-2$
        resp.setContentType(HttpConstants.CONTENT_TYPE_JSON);
        Object response = JsonUtil.stringify(object, useUpperCaseKey, features);
        resp.getWriter().print(response);
    }

    /**
     * 往response中输出JSON格式内容
     * @param req HttpServletRequest
     * @param resp  HttpServletResponse
     * @param result 转换的对象
     * @throws IOException
     */
    public static void toJSON(List<Map<String, Object>> list) throws IOException{
        toJSON(list, HttpServletResponse.SC_OK);
    }

    public static void toJSON(Serializable bean) throws IOException{
        toJSON(bean, HttpServletResponse.SC_OK);
    }

    public static void toJSON(Object object) throws IOException{
        toJSON(object, HttpServletResponse.SC_OK);
    }

    /**
     * 封装需要返回的信息
     * @param status	返回状态     ReturnStateEnum.SUCCESS.getValue()、 ReturnStateEnum.WARNING.getValue()
     * @param message	提示信息
     * @param datas		返回数据
     * @return
     */
    public static Map<String, Object> toMessage(String status, String message, Object datas){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put(ReturnStateEnum.STATUS.getValue(), status);
        returnMap.put(ReturnStateEnum.MESSAGE.getValue(), message);
        returnMap.put(ReturnStateEnum.DATA.getValue(), datas);
        return returnMap;
    }

    /**
     * 封装成功返回信息
     * @param message	提示信息
     * @param datas		返回数据
     * @return
     */
    public static Map<String, Object> toSuccessMessage(String message, Object datas){
        return toMessage(ReturnStateEnum.SUCCESS.getValue(), message, datas);
    }

    /**
     * 封装警告返回信息
     * @param message	提示信息
     * @param datas		返回数据
     * @return
     */
    public static Map<String, Object> toWarningMessage(String message, Object datas){
        return toMessage(ReturnStateEnum.WARNING.getValue(), message, datas);
    }

    /**
     * 返回提示信息至前端
     * @param status	返回状态     ReturnStateEnum.SUCCESS.getValue()、 ReturnStateEnum.WARNING.getValue()
     * @param message	提示信息
     * @param datas		返回数据
     * @throws IOException
     */
    public static void showMessage(String status, String message, Object datas) throws IOException {
        HttpServletResponse resp = GetBeanUtil.getServletActionContext().getResponse();
        assert resp != null;
        resp.setContentType(HttpConstants.CONTENT_TYPE_JSON);
        Map<String, Object> obj = toMessage(status, message, datas);
        String response = JsonUtil.stringify(obj, false);
        resp.getWriter().print(response);
    }

    /**
     * 返回成功提示信息至前端
     * @param message	提示信息
     * @param datas		返回数据
     * @throws IOException
     */
    public static void showSuccessMessage(String message, Object datas) throws IOException {
        HttpServletResponse resp = GetBeanUtil.getServletActionContext().getResponse();
        resp.setContentType(HttpConstants.CONTENT_TYPE_JSON);
        Map<String, Object> obj = toSuccessMessage(message, datas);
        String response = JsonUtil.stringify(obj, false);
        resp.getWriter().print(response);
    }

    /**
     * 返回警告提示信息至前端
     * @param message	提示信息
     * @param datas		返回数据
     * @throws IOException
     */
    public static void showWarningMessage(String message, Object datas) throws IOException {
        HttpServletResponse resp = GetBeanUtil.getServletActionContext().getResponse();
        resp.setContentType(HttpConstants.CONTENT_TYPE_JSON);
        Map<String, Object> obj = toWarningMessage(message, datas);
        String response = JsonUtil.stringify(obj, false);
        resp.getWriter().print(response);
    }
}
