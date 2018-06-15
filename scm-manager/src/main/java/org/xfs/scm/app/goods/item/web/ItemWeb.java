package org.xfs.scm.app.goods.item.web;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.common.base.model.BaseResponse;
import org.xfs.scm.app.goods.item.service.ItemServiceI;
import org.xfs.scm.app.goods.item.vo.ItemVo;

@Controller
@RequestMapping("/rest/goods/item/")
public class ItemWeb {

    @Autowired
    private ItemServiceI itemService;

    @ResponseBody
    @RequestMapping(value = "info.do/{itemId}",method = RequestMethod.GET)
    public Object info(@PathVariable Long itemId){
        RateLimiter limiter = RateLimiter.create(300) ;

        ItemVo data=this.itemService.get(new ItemVo(itemId));
        if(data!=null){
            return BaseResponse.success("查询成功!",data);
        }
        return BaseResponse.fail("查询失败！");
    }
}
