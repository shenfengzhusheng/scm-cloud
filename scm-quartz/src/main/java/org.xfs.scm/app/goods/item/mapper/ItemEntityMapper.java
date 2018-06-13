package org.xfs.scm.app.goods.item.mapper;

import org.xfs.scm.app.goods.item.entity.ItemEntity;
import org.xfs.scm.app.goods.item.vo.ItemVo;

import java.util.List;

public interface ItemEntityMapper {
    int removeItem(ItemVo vo);


    int addItem(ItemVo vo);

    List<ItemVo> findItems(ItemVo vo);


    int modifyItem(ItemVo vo);

}