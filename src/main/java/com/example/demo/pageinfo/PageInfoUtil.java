package com.example.demo.pageinfo;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author whn
 */
public class PageInfoUtil {
    private static String[] HEADER_RANGES = {"Range", "range", "X-Range", "x-range"};

    public static PageInfo initPageInfo(HttpServletRequest request) {
        String range = null;
        PageInfo pageInfo = null;
        for (String header : HEADER_RANGES) {
            range = request.getHeader(header);
            if (range != null) {
                pageInfo = new PageInfo(range);
                break;
            }
        }
        return pageInfo;
    }

    public static PageInfo initPageInfo(HttpHeaders headers) {
        String range = null;
        List<String> rangeList = null;
        for (String each : HEADER_RANGES) {
            rangeList = headers.get(each);
            if (rangeList != null && !rangeList.isEmpty()) {
                range = rangeList.get(0);
                break;
            }
        }
        if (range != null) {
            return new PageInfo(range);
        }
        return null;
    }
}
