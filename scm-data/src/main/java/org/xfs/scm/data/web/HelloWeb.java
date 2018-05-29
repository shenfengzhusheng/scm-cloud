package org.xfs.scm.data.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController

public class HelloWeb {

    @Value("${server.port}")
    String port;
    @Value("${jdbc.validationQuery}")
    String validationQuery;

    @Resource
    private RedisTemplate<String,String>redisTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CounterService counterService;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "hiError")
    public String sayHi(@RequestParam String name){
        String cacheValue=this.redisTemplate.opsForValue().get("test");
        if(cacheValue!=null && "88 gun".equals(cacheValue) ){
            System.out.println("had cache 88gun!");
        }else{
            System.out.println("not cache 88gun!");
            this.redisTemplate.opsForValue().set("test","88 gun",7200, TimeUnit.SECONDS);
        }
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
    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
    @RequestMapping(value = "/admin/getConfig",method = RequestMethod.GET)
    public String get(){
        return "validationQuery:"+validationQuery;
    }
}
