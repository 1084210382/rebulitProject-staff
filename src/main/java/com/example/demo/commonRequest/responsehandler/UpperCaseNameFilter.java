package com.example.demo.commonRequest.responsehandler;

import com.alibaba.fastjson.serializer.NameFilter;

/**
 * @author web
 */
public class UpperCaseNameFilter implements NameFilter {
    @Override
    public String process(Object object, String name, Object value) {
        if (name.equals("dbid") || name.equals("bizid") || name.equals("bindLiziForm")) {
            return name;
        }
        return name.toUpperCase();
    }
}