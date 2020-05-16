package com.edwin.myshop.common.base;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class BaseSwaggerConfig {
   public List<Parameter> pars = new ArrayList<Parameter>();

    public BaseSwaggerConfig(){
        //添加head参数start
       /*  ParameterBuilder tokenPar = new ParameterBuilder();
            tokenPar.name("api-token").description("用户令牌").modelRef(new ModelRef("String")).parameterType("header").required(false);
            pars.add(tokenPar.build());*/
        }


    public ApiInfo apiInfo(String title, String description, String version, String name, String url, String email) {

        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .contact(new Contact("edwin", "", "1635290773@qq.com"))
                .version(version)
                .build();
    }


    public List<ApiKey> securitySchemes() {
        return newArrayList(
                new ApiKey("Authorization", "api-token", "header"));
    }

    public List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build()
        );
    }

    public List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference("Authorization", authorizationScopes));
    }


}
