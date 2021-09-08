package com.example.demo.config;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author web
 * 拦截器
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {
    public final static String APP_KEY = "111";
    public final static String SECRET = "111";
    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (request.getHeader("APP_KEY")!=null && request.getHeader("SECRET") != null && request.getHeader("APP_KEY").equals(APP_KEY) && request.getHeader("SECRET").equals(SECRET)){
//            System.out.println("Interceptor preHandler method is running !");
//            return true;
//        }
//        response.setCharacterEncoding("utf-8");
//        response.setHeader("Content-type", "text/html; charset=utf-8");
//        response.setHeader("errorMsg","please check APP_KEY and SECRET");
//        response.sendError(403,"error");
//        return false;
//    }

    /**
     * postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操作。
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor postHandler method is running !");
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。
     * 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行。
     * （这个方法的主要作用是用于清理资源的）
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Interceptor afterCompletion method is running !");
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Interceptor afterConcurrentHandlingStarted method is running !");
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
