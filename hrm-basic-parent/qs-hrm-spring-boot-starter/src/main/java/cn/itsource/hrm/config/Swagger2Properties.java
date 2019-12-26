package cn.itsource.hrm.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Getter
@Setter
@ConfigurationProperties(prefix = "hrm.swagger")
public class Swagger2Properties {
    private String basePackage="cn.itsource.hrm.web.controller";
    private String title;
    private String description;
    private String name="qs";
    private String url="";
    private String email="3041109656@qq.com";
    private String version="1.0";
}
