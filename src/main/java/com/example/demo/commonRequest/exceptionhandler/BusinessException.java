package com.example.demo.commonRequest.exceptionhandler;

import java.util.List;

/**
 * @author web
 * 这是一个全局异常处理器，用于在异常抛出的地方做抛出。
 */
public class BusinessException extends RuntimeException implements CommonError {
    private CommonError commonError;

    //直接EmBusinessError传参用于构造异常
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    //自定义
    public BusinessException(CommonError commonError, String errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    //List类型异常抛出
    public BusinessException(CommonError commonError, List errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public List getErrList() {
        return this.commonError.getErrList();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }

    @Override
    public CommonError setErrMsg(List errMsg) {
        this.commonError.setErrMsg(errMsg.toString());
        return this;
    }
}
