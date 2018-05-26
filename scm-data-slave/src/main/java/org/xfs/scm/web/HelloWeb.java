package org.xfs.scm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class HelloWeb {

    @Value("${server.port}")
    String port;

    @Value("${jdbc.validationQuery}")
    String validationQuery;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CounterService counterService;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        counterService.increment(port+":begin services.sayHi.invoked");
        try {
            Thread.sleep(new Random().nextInt(2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counterService.decrement(port+":end services.sayHi.invoked");

        return "hi "+name+",i am from port:" +port;    }

    //服务发现
    @RequestMapping("/discovery")
    public String getRegistered(){
        StringBuilder buf = new StringBuilder();
        List<String> serviceIds = discoveryClient.getServices();
        if(!CollectionUtils.isEmpty(serviceIds)){
            for(String s : serviceIds){
                System.out.println("serviceId:" + s);
                List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(s);
                if(!CollectionUtils.isEmpty(serviceInstances)){
                    for(ServiceInstance si:serviceInstances){
                        buf.append("["+si.getServiceId() +" host=" +si.getHost()+" port="+si.getPort()+" uri="+si.getUri()+"]");
                    }
                }else{
                    buf.append("no web.");
                }
            }
        }


        return buf.toString();
    }

    @RequestMapping(value = "/admin/getConfig",method = RequestMethod.GET)
    public String get(){
        return "validationQuery:"+validationQuery;
    }
}
