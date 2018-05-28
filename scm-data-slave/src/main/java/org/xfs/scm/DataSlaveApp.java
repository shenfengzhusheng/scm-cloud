package org.xfs.scm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
@SpringBootApplication
@EnableHystrixDashboard
public class DataSlaveApp {
    public static void main(String[]args){
        SpringApplication.run(DataSlaveApp.class,args);
    }

}
