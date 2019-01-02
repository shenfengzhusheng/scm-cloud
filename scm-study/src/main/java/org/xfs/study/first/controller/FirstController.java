package org.xfs.study.first.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xfs.study.first.model.UserModel;

@Api(tags = "用户相关api")
@RestController
@RequestMapping("/user")
public class FirstController {

    @RequestMapping(value = "/sayHi.json",method = {RequestMethod.GET,RequestMethod.POST})
    public String sayHi(){
        return "hello this my first spring boot program!";
    }

    @ApiOperation(value = "根据用户ID查找用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name="id",dataType = "int",required =true,value = "用户的Id",defaultValue = "1")
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code = 404,message = "请求路径没有或页面跳转路径不对！")

    })
    @RequestMapping(value = "getUserInfo.json",method = RequestMethod.GET)
    public UserModel getUserInfo(@RequestParam("id") int id){

        if(id==1){
            return new UserModel(1,"小红","1111111");
        }else{
            return new UserModel(2,"小刚","123456");
        }
    }
}
