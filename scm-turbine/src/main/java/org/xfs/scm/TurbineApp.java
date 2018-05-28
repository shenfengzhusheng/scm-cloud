package org.xfs.scm;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine
public class TurbineApp {
    public static void main(String[] args) {

        new SpringApplicationBuilder(TurbineApp.class).web(true).run(args);
    }
}
