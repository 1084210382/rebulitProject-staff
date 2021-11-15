package com.example.demo.common;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.example.demo.common.ResponseUtil.toMessage;


/**
 * @author zcc
 * @date 2019/3/25 20:13
 */
//返回通用格式
public class CommonReturnType {
    private String status;//success或fail

    private Object data;


    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result, "success", "返回成功");
    }

    public static CommonReturnType create(Object result, String status) {
        return CommonReturnType.create(result, status, "返回成功");
    }

    public static CommonReturnType create(Object result, String status, String message) {
        CommonReturnType type = new CommonReturnType();
        HttpServletResponse resp = ApplicationContextUtil.getServletActionContext().getResponse();
        assert resp != null;
        resp.setContentType(HttpConstants.CONTENT_TYPE_JSON);
        //将返回Object转化为Map键值对
        Map<String, Object> obj = toMessage(result);
        //去掉其中的空值
        String response = JSONUtil.stringify(obj, false);
        type.setStatus(status);
        JSONObject jsonObject = JSONObject.parseObject(String.valueOf(response));
        type.setData(jsonObject.get("data"));
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
