package com.example.demo.commonRequest.requesthandler;

import com.example.demo.config.GetBeanUtil;
import com.example.demo.pageinfo.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RequestUtil {
    private static final String HEADER_REQUESTED_WITH = "X-Requested-With";//$NON-NLS-1$
    private static final String VALUE_REQUESTED_WITH = "XMLHttpRequest";//$NON-NLS-1$

    /**
     * 判断请求是否是ajax请求。如果返回false，则是页面跳转。
     * @param req
     * @return 是则返回true;否则返回false
     */
    public static boolean isAjax(HttpServletRequest req) {
        String xRequestedWith = req.getHeader(HEADER_REQUESTED_WITH);
        if(VALUE_REQUESTED_WITH.equals(xRequestedWith)){
            return true;
        }
        return false;
    }

    /**
     * 查询name属性对应的值，该函数的功能与{@link HttpServletRequest#getParameter(String)}类似。
     * 之所以需要这个方法，是因为在filter中根据{@link HttpServletRequest#getParameter(String)}获取参数后，
     * 如果name的值并不存在，会从{@link HttpServletRequest#getInputStream()}中获取，然后后面的servlet或
     * filter就无法获取到这个值了。
     * @param request
     * @param name
     * @return 对应的值
     */
    public static String getQueryParameter(HttpServletRequest request, String name) {
        String queryString = request.getQueryString();
        if (queryString == null) {
            return null;
        }

        for (String paramString : queryString.split("&")) { //$NON-NLS-1$
            if (paramString.startsWith(name)) {
                String[] nameAndValue = paramString.split("=", 2); //$NON-NLS-1$
                if (nameAndValue.length == 2) {
                    return nameAndValue[1];
                }
                return ""; // parameter has no value //$NON-NLS-1$
            }
        }
        // parameter not found
        return null;
    }

    /**
     * 获取HttpServletRequest中的对象对乱码进行自动转换
     * 主要用于中文的乱码问题，如果是参数有中文一定要使用该方法
     * 乱码存在于tomcat和weblogic中,jetty中则正常
     * @param req
     * @param paramName
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public static String getParameter(HttpServletRequest req,String paramName) throws ServletException, IOException{
        String reqParameter = req.getParameter(paramName);
        //req.getServletContext() 也可以获取，不这么写的原因是低版本的servlet不兼容该方法
        if(reqParameter == null){
            return "";
        }
        if(req.getSession().getServletContext().getServerInfo().contains("jetty")){
            return URLDecoder.decode(reqParameter, "UTF-8");
        }else{
            // 可以直接设置tomcat的字符集，就不再需要调用这个方法
            return URLDecoder.decode(new String(reqParameter.getBytes("iso-8859-1"), "UTF-8"), "UTF-8");
        }
    }

    /**
     * 获取请求路径
     * @param request
     * @return
     */
    public static String requestUrl(HttpServletRequest request){
        String strBackUrl = "http://" + request.getServerName() //服务器地址
                + ":"
                + request.getServerPort()  //端口号
                + request.getContextPath() //项目名称
                + request.getServletPath(); //请求页面或其他地址
        return strBackUrl;
    }


    /**
     * 从 request 获取参数 Map
     * @return Map<String,Object> 对象
     */
    public static Map<String, Object> getRequestParameters(){
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String,String[]> parameters = GetBeanUtil.getServletActionContext().getRequest().getParameterMap();
        Iterator<Map.Entry<String, String[]>> iterator = parameters.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, String[]> entry = iterator.next();
            if(entry.getValue().length == 1){
                map.put(entry.getKey(), entry.getValue()[0]);
            }else{
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }


    private static String[] HEADER_RANGES = { "Range", "range", "X-Range", "x-range" };

    public static PageInfo getPageInfo() {
        HttpServletRequest req = GetBeanUtil.getServletActionContext().getRequest();
        String range = null;
        for (String each : HEADER_RANGES) {
            range = req.getHeader(each);
            if (range != null) {
                break;
            }
        }
        if (range != null) {
            PageInfo pageInfo = new PageInfo(range);
            return pageInfo;
        }
        return null;
    }

    /**
     * 获取header信息
     * @param name
     * @return
     */
    public static String getTokenInfo(String name) {
        if(GetBeanUtil.getServletActionContext() == null) {
            return "";
        }
        HttpServletRequest req = GetBeanUtil.getServletActionContext().getRequest();
        return req.getHeader(name);
    }
}
