package com.example.demo.commonRequest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author whn
 */
@Aspect
@Component
public class LoginAspect {
    @Pointcut("@annotation(com.example.demo.commonRequest.aop.Login)")
    private void anyOldTransfer() {}
//
//    @Before("@annotation(com.example.demo.commonRequest.aop.Login)")
//    public void cutBefore(JoinPoint joinPoint) throws Throwable {
//        //获取连接点方法运行时的入参列表
//        Object[] params = joinPoint.getArgs();
//        SysRole sysRole = (SysRole)params[0];
//        System.out.println(sysRole.getId());
//        System.out.println("前置AOP成功");
//    }
//
//    @Around("@annotation(com.example.demo.commonRequest.aop.Login)")
//    public Map<String, Object> cut(ProceedingJoinPoint joinPoint) throws Throwable {
//        //获取连接点方法运行时的入参列表
//        Object[] params = joinPoint.getArgs();
//        SysRole sysRole = (SysRole)params[0];
//        System.out.println(sysRole.getName());
//        Map<String, Object> map = (Map<String, Object>) joinPoint.proceed(params);
//        System.out.println("环绕AOP成功");
//        return map;
//    }
//
//    @After("@annotation(com.example.demo.commonRequest.aop.Login)")
//    public void cutAfter(JoinPoint joinPoint) throws Throwable {
//        //获取连接点方法运行时的入参列表
//        Object[] params = joinPoint.getArgs();
//        SysRole sysRole = (SysRole)params[0];
//        System.out.println(sysRole.getName());
//        System.out.println("后置AOP成功");
//    }
}
