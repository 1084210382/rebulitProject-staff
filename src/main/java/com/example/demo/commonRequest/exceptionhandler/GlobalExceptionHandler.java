package com.example.demo.commonRequest.exceptionhandler;

import com.example.demo.commonRequest.responsehandler.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author whn
 * 全局异常处理器
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
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            responseData.put("errCode", businessException.getErrCode());
            responseData.put("errMsg", businessException.getErrMsg());
        } else if (e instanceof HttpMessageNotReadableException) {
            responseData.put("errCode", "10005");
            responseData.put("errMsg", "数据格式有误");
        } else if (e instanceof BadSqlGrammarException) {
            responseData.put("errCode", "10006");
            responseData.put("errMsg", "SQL语句有误");
            responseData.put("SQL", ((BadSqlGrammarException) e).getSQLException());
        } else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
        ResponseUtil.showMessage("fail", "请求出错", responseData);
    }
}
