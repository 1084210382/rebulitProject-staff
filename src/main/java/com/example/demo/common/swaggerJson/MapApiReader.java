package com.example.demo.common.swaggerJson;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;

import java.util.Map;
import java.util.Optional;

@Component
@Order //plugin加载顺序，默认是最后加载
public class MapApiReader implements ParameterBuilderPlugin {

    @Autowired
    private TypeResolver typeResolver;

    @SneakyThrows
    @Override
    public void apply(ParameterContext parameterContext) {
        ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
        //判断是否需要修改对象ModelRef,这里我判断的是Map类型和String类型需要重新修改ModelRef对象
        if (methodParameter.getParameterType().canCreateSubtype(Map.class) || methodParameter.getParameterType().canCreateSubtype(String.class)) {
            Optional<ApiJsonObject> optional = methodParameter.findAnnotation(ApiJsonObject.class);

            //根据参数上的ApiJsonObject注解中的参数动态
            // 生成Class
            if (optional.isPresent()) {
                String name = optional.get().name();  //model 名称
                String str = "com.upc.example.controller.viewobject.";
                ResolvedType resolvedType = typeResolver.resolve(Class.forName(str + name));
                parameterContext.getDocumentationContext().getAdditionalModels().add(resolvedType);
                parameterContext.parameterBuilder()
                        .parameterType("body")
                        .modelRef(new ModelRef(name))
                        .name(name);
            }
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}