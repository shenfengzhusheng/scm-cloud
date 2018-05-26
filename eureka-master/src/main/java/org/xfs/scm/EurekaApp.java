package org.xfs.scm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class EurekaApp {
    private static Logger logger= LoggerFactory.getLogger(EurekaApp.class);
    public static void main(String[]args){
        logger.debug("start app!");
        SpringApplication.run(EurekaApp.class,args);
    }
}
