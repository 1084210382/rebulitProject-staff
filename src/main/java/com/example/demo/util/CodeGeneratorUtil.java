package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author whn
 * 创建时间戳+随机数的一个code
 */
public class CodeGeneratorUtil {
    public static String generateCode() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date()) + "_" + (int) (Math.random() * 100);
    }
}
