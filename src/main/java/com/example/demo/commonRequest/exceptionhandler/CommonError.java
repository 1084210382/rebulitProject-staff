package com.example.demo.commonRequest.exceptionhandler;

import java.util.List;

/**
 * @author web
 * 这是一个全局异常处理接口，用于被handler实现。
 */
public interface CommonError {
    public int getErrCode();

    public String getErrMsg();

    public CommonError setErrMsg(String errMsg);

    public CommonError setErrMsg(List errMsg);

    public List getErrList();
}
