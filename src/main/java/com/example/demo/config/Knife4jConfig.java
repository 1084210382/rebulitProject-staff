package com.example.demo.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("接口文档")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .description("5.13")
                        .version("1.0.0")
                        .build())
                //分组名称
                .groupName("培训考试")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.trainExam.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean(value = "defaultApi1")
    public Docket defaultApi1() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("接口文档")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .description("5.13")
                        .version("1.0.0")
                        .build())
                //分组名称
                .groupName("权限端")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.upc.permission.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean(value = "defaultApi3")
    public Docket defaultApi3() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("接口文档")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .description("5.13")
                        .version("1.0.0")
                        .build())
                //分组名称
                .groupName("企业端")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.upc.example.controller.enterprise"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
    @Bean(value = "defaultApi4")
    public Docket defaultApi4() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("接口文档")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .description("5.13")
                        .version("1.0.0")
                        .build())
                //分组名称
                .groupName("地图")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.upc.example.controller.map"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


}
