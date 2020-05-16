package com.edwin.myshop.common.config;

import com.edwin.myshop.common.base.BaseSwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig {
    @Value("${spring.application.name:}")
    private String name;
    @Value("${swagger.title:}")
    private String title;
    @Value("${swagger.description:}")
    private String description;
    @Value("${swagger.version:}")
    private String version;
    @PostConstruct
    private void checkProperties(){

        log.info("------------------------swagger-------------------------------------------------------");
        log.info(String.format("Application Name:%s--->CheckProperties--->swagger configuration--->Start",name));
        if(StringUtils.isBlank(title)){
            log.info(String.format("Application Name:%s--->CheckProperties--->swagger configuration--->没有找到配置文件｛%s,%s,%s｝",name,"title","description","version"));
        }else{
            log.info(String.format("Application Name:%s--->CheckProperties--->swagger configuration--->配置数据正常",name));
        }
        log.info(String.format("Application Name:%s--->CheckProperties--->swagger configuration--->Completed",name));
        log.info("--------------------------------------------------------------------------------------");

    }

    @Bean
    public Docket createRestApi() {
        BaseSwaggerConfig baseSwagger=new BaseSwaggerConfig();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(baseSwagger.apiInfo(title,description,version,"","","")).select()
                .apis(RequestHandlerSelectors.basePackage("com.edwin.myshop"))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(baseSwagger.pars).securitySchemes(baseSwagger.securitySchemes())
                .securityContexts(baseSwagger.securityContexts());
    }

    /**
     * 获取当前项目默认Swagger扫描包路径
     * @return
     */
    private String getSwaggerScannerPackage(){
        String nowpackage=this.getClass().getPackage().getName();
        String scannerPackage="controller";
        scannerPackage=nowpackage.substring(0,nowpackage.lastIndexOf(".")).concat(".").concat(scannerPackage);
        System.out.println(title.concat("-->").concat("Swagger Scanner Package :").concat(scannerPackage));
        return scannerPackage;
    }


}