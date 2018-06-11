package org.xfs.scm.app.goods.item.service;

import org.xfs.scm.app.goods.item.vo.ItemVo;

import java.util.List;

public interface ItemServiceI {
    int remove(ItemVo vo);
    int add(ItemVo vo);
    List<ItemVo> find(ItemVo vo);

    ItemVo get(ItemVo vo);
    int modify(ItemVo vo);

}
