package com.example.demo.common.doPage;


import com.example.demo.config.GetBeanUtil;
import com.github.pagehelper.PageHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;


/**

 * @author whn

 * 分页实现
 */
@Component
@Scope("prototype")
@Aspect
public class DoPageAspect {
    PageInfo pageInfo = new PageInfo("0:10");

    @Pointcut("@annotation(com.example.demo.common.doPage.DoPage)")
    public void annotationPointCut() {

    }

    @Around("annotationPointCut()")
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T> List<T> around(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取连接点方法运行时的入参列表
        Object[] params = joinPoint.getArgs();
        //获取请求/响应
        ServletRequestAttributes servletRequestAttributes = GetBeanUtil.getServletActionContext();
        pageInfo = PageInfoUtil.initPageInfo(servletRequestAttributes.getRequest());
        setPageInfo(pageInfo);
        //请求中不包含分页信息时不做分页处理
        if (pageInfo != null) {
            System.out.println(pageInfo.getStart());
            System.out.println(pageInfo.getEnd());
            PageHelper.startPage(pageInfo.getStart(), pageInfo.getEnd());
        }
        Object list = joinPoint.proceed(params);
        List<T> result = null;
        if (list instanceof ArrayList) {
            result = (List) list;
        }
        if (pageInfo != null) {
            com.github.pagehelper.PageInfo<?> page = new com.github.pagehelper.PageInfo<>(result);
            if (pageInfo.getCount() == 0) {
                pageInfo.setCount((int) page.getTotal());
            }
        } else {
            pageInfo = new PageInfo("0:10");
            if (result == null) {
                pageInfo.setCount(0);
            } else {
                pageInfo.setCount(result.size());
            }
        }

        servletRequestAttributes.getResponse().setHeader("Access-Control-Expose-Headers","Content-Range");
        servletRequestAttributes.getResponse().setHeader("Content-Range", pageInfo.toString());
        return result;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

}