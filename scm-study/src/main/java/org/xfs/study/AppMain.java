package org.xfs.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2//启动swagger
public class AppMain {
    public static void main(String[]args){
        SpringApplication.run(AppMain.class,args);
    }
}
