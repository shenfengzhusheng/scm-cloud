package org.xfs.scm.app.goods.item.vo;

import org.xfs.scm.app.goods.item.entity.ItemEntity;

public class ItemVo extends ItemEntity {

    public ItemVo(){}
    public ItemVo(Long itemId){
        super.setItemId(itemId);
    }
}
