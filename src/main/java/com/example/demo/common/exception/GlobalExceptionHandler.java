package com.example.demo.common.exception;

import com.alibaba.excel.exception.ExcelAnalysisException;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.demo.common.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zcc
 * @date 2019/3/25 20:24
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    // 吃掉不可预知的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void handleException(HttpServletRequest request, Exception e) throws IOException {
        e.printStackTrace();
        Map<String, Object> responseData = new HashMap<>();
        if(e instanceof BusinessException){
            BusinessException businessException =(BusinessException) e;
            responseData.put("errCode",businessException.getErrCode());
            responseData.put("errMsg",businessException.getErrMsg());
            if (CollectionUtils.isNotEmpty(businessException.getErrList()))
            responseData.put("errList",businessException.getErrList());
        }
        else if(e instanceof ExcelAnalysisException){
            ExcelAnalysisException exception = (ExcelAnalysisException) e;
            responseData.put("errCode", 200);
            responseData.put("errMsg",exception.getMessage());
        }
        else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }

        ResponseUtil.showMessage("fail", "请求出错", responseData);
    }
}
