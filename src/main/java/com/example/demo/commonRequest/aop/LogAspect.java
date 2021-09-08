package com.example.demo.commonRequest.aop;

import com.example.demo.config.GetBeanUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author whn
 */
@Aspect
@Component
public class LogAspect {
    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.example.demo.commonRequest.aop.Log)")
    public void logPointCut() {
    }

    //切面 配置为前置通知
    @Before("logPointCut()")
    public void saveOperation(JoinPoint joinPoint) {
        //创建一个日志对象
        //AdminLog adminLog = new AdminLog();
        //获取切面织处入点的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作日志的属性值
        Log myLog = method.getAnnotation(Log.class);
        if (myLog != null) {
            //操作事件
            String operation = myLog.operation();
            //adminLog.setOperation(operation);
            //日志类型
            int type = myLog.type();
            //adminLog.setType(type);
        }
        //获取url
        ServletRequestAttributes servletRequestAttributes = GetBeanUtil.getServletActionContext();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String requestURL = request.getRequestURI().toString();
        //adminLog.setUrl(requestURL);
        //获取客户端ip
        String ip = request.getRemoteAddr();
        //adminLog.setLoginip(ip);
        //获取操作人账号、姓名(需要提前将用户信息保存到Session)
        //Admin admin = (Admin) request.getSession().getAttribute("admin");
//        if (admin != null) {
//            Integer id = admin.getId();
//            String name = admin.getName();
//            adminLog.setUserId(id);
//            adminLog.setUserName(name);
//        }
        //调用service保存Operation实体类到数据库
        //可以在这设置id，因为是测试，这里就使用的是数据库的自增id
//        myLogMapper.insertLog(adminLog);
    }
}
