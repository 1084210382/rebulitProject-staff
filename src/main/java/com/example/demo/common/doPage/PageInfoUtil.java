package com.example.demo.common.doPage;

import javax.servlet.http.HttpServletRequest;

/**

 * @author whn
 */
public class PageInfoUtil {
    private static String[] HEADER_RANGES = {"Range", "range", "X-Range", "x-range"};

    public static PageInfo initPageInfo(HttpServletRequest request) {
        String range = request.getHeader("Range");
        PageInfo pageInfo = null;
        if (range != null) {
            pageInfo = new PageInfo(range);
        }
        return pageInfo;
    }
}