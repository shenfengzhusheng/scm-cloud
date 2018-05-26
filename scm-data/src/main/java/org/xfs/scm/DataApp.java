package org.xfs.scm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
@SpringBootApplication
public class DataApp {
    public static void main(String[]args){
        SpringApplication.run(DataApp.class,args);
    }

}
