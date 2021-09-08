package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author whn
 * 获取bean实例的工具类，线程创建时防注入导致某些bean无法使用
 */
@Component
public class GetBeanUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        GetBeanUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据Bean Class从上下文里获取Bean 对象
     *
     * @param requiredType bean 类型
     * @return bean对象
     */
    public static Object getBean(Class<?> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static ServletRequestAttributes getServletActionContext() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }
}
