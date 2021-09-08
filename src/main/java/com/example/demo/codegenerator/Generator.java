package com.example.demo.codegenerator;

/**
 * @author web
 * 代码生成器，参数分别为
 * 作者
 * 项目子包名
 * 数据库类型
 * 地址+端口+密码+数据库名+账户名+密码（中间以，隔开）
 * 默认false
 * 数组中为表名
 */
public class Generator {
    public static void main(String[] args) {
//        GeneratConfig.generator("Webgasd","","Mysql","82.156.208.107,3306,manage,root,Whn39258376!",true,new String[]{});
        GeneratConfig.generator("demo", "demo", "Mysql", "rm-uf6t378ap6w4q09q1ko.mysql.rds.aliyuncs.com,3306,test,root,Liu123456", false, new String[]{"sup_employees"});

    }
}
