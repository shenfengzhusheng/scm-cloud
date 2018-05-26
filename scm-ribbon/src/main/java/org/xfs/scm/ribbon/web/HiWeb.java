package org.xfs.scm.ribbon.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xfs.scm.ribbon.service.HiService;

import javax.annotation.Resource;

@RestController
public class HiWeb {

    @Resource
    HiService hiService;
    @RequestMapping(value = "/hi/{name}",method = RequestMethod.GET)
    public String sayHi(@PathVariable String name){
        return this.hiService.sayHi(name);
    }
}