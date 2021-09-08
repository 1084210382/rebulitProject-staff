package com.example.demo.pageinfo;


import com.example.demo.config.GetBeanUtil;
import com.github.pagehelper.PageHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whn
 * 分页实现
 */
@Component
@Aspect
public class DoPageAspect {
    PageInfo pageInfo = new PageInfo("items=0-0");

    @Pointcut("@annotation(com.example.demo.pageinfo.DoPage)")
    public void annotationPointCut() {

    }

    @Around("annotationPointCut()")
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> List<T> around(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取连接点方法运行时的入参列表
        Object[] params = joinPoint.getArgs();
        //获取请求/响应
        ServletRequestAttributes servletRequestAttributes = GetBeanUtil.getServletActionContext();
        pageInfo = PageInfoUtil.initPageInfo(servletRequestAttributes.getRequest());
        setPageInfo(pageInfo);
        //请求中不包含分页信息时不做分页处理
        if(pageInfo != null){
            //后期扩展Oracle的分页，此处需要添加一个路由的方法，根据不同的数据库类型使用不同的分页方式
            PageHelper.startPage(pageInfo.getStart(), pageInfo.getEnd());
        }

        Object list = joinPoint.proceed(params);
        List<T> result = null;
        if (list instanceof ArrayList) {
            result = (List) list;
        }
        if(pageInfo != null){
            com.github.pagehelper.PageInfo<?> page = new com.github.pagehelper.PageInfo<>(result);
            if(pageInfo.getCount() == 0) {
                pageInfo.setCount((int) page.getTotal());
            }
        }else {
            pageInfo = new PageInfo("items=0-0");
            if(result == null) {
                pageInfo.setCount(0);
            }else {
                pageInfo.setCount(result.size());
            }
        }
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
