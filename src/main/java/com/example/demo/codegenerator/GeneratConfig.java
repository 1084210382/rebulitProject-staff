package com.example.demo.codegenerator;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whn
 * mybatis-plus持久层框架生成器配置类。
 */
public class GeneratConfig {

    public static void generator(String author, String moduleName, String dataBaseType, String dataBaseParam, Boolean wholeFlag, String[] tableName) {
        if (StringUtils.isEmpty(author)) {
            author = "Mybatis自动生成";
        }

//        if (StringUtils.isEmpty(moduleName)) {
//            throw new MybatisPlusException("请输入目录名称");
//        }

        String[] dataBaseInfo = dataBaseParam.split(",");
        //数据库地址
        String dataBaseIp = dataBaseInfo[0];
        //数据库端口
        String dataBasePort = dataBaseInfo[1];
        //数据库服务名称
        String serviceName = dataBaseInfo[2];
        //数据库用户
        String userName = dataBaseInfo[3];
        //数据库用户密码
        String userPwd = dataBaseInfo[4];

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //输出文件路径
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setOpen(false);
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columnList
        gc.setBaseColumnList(false);
        // 作者
        gc.setAuthor(author);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(getDataBaseUrl(dataBaseType, dataBaseIp, dataBasePort, serviceName));
        dsc.setDriverName(getDriverName(dataBaseType));
        dsc.setUsername(userName);
        dsc.setPassword(userPwd);
        if("Oracle".equals(dataBaseType)) {
            dsc.setDbType(DbType.ORACLE);
            dsc.setTypeConvert(new OracleTypeConvert() {

                // 自定义数据库表字段类型转换
                @Override
                public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                    //将数据库中datetime转换成date
                    if (fieldType.toLowerCase().contains( "datetime" )) {
                        return DbColumnType.DATE;
                    }
                    return super.processTypeConvert(globalConfig, fieldType);
                }
            });
        }else if("Mysql".equals(dataBaseType)) {
            dsc.setDbType(DbType.MYSQL);
            dsc.setTypeConvert(new MySqlTypeConvert() {

                // 自定义数据库表字段类型转换
                @Override
                public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {

                    //将数据库中datetime转换成date
                    if (fieldType.toLowerCase().contains( "datetime") || fieldType.toLowerCase().contains( "timestamp")) {
                        return DbColumnType.DATE;
                    }
                    return super.processTypeConvert(globalConfig, fieldType);
                }
            });
        }
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //修改为自己的项目路径
        pc.setEntity("model");
        pc.setMapper("dao");
        //目录
        if (StringUtils.isEmpty(moduleName))
            pc.setModuleName(null);
        else
            pc.setModuleName(moduleName);
        pc.setParent("com.example");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/templates/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setSuperEntityClass("com.baomidou.mybatisplus.extension.activerecord.Model");
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass("com.example.demo.codegenerator.BaseController");
        if(!wholeFlag) {
            //添加对应的表  全库时注释该行
            strategy.setInclude(tableName);
        }
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    private static String getDataBaseUrl(String dataBaseType, String dataBaseIp, String dataBaseProt, String serviceName) {
        String url = "";
        if("Oracle".equals(dataBaseType)) {
            url = "jdbc:oracle:thin:@"+dataBaseIp+":"+dataBaseProt+"/"+serviceName;
        }else if("Mysql".equals(dataBaseType)) {
            url = "jdbc:mysql://"+dataBaseIp+":"+dataBaseProt+"/"+serviceName+"?useUnicode=true&characterEncoding=utf8";
        }
        return url;
    }

    private static String getDriverName(String dataBaseType) {
        String driverName = "";
        if("Oracle".equals(dataBaseType)) {
            driverName = "oracle.jdbc.driver.OracleDriver";
        }else if("Mysql".equals(dataBaseType)) {
            driverName = "com.mysql.cj.jdbc.Driver";
        }
        return driverName;
    }
}
