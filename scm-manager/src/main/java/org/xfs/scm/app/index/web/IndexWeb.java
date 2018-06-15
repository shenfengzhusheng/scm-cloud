package org.xfs.scm.app.index.web;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class IndexWeb {

    private int count=0;
    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message ;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "welcome";
    }


    @RequestMapping("index")
    public String index(Map<String, Object> model) {
        model.put("message", this.message);
        return "welcome";
    }

    @RequestMapping("job")
    public String job(Map<String, Object> model) {
        return "JobManager";
    }
    @ResponseBody
    @RequestMapping("/weclome")
    public Object weclome(){
        return this.message;
    }

    @RequestMapping("/test.do")
    public String test(Map<String, Object> model) {
        Long start = System.currentTimeMillis();

        RateLimiter limiter = RateLimiter.create(10000) ;
        //批量调用
        for (int i = 0 ;i< 10 ; i++){
            double acquire = limiter.acquire();
            System.out.println(Thread.currentThread().getName()+"call execute.." +acquire);

        }
        model.put("message", "天王盖地虎！");
        Long end = System.currentTimeMillis();

        System.out.println(end - start);
        return "welcome";
    }
}
