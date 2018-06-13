package org.xfs.scm.app.goods.item.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xfs.scm.app.goods.item.entity.ItemEntity;
import org.xfs.scm.app.goods.item.mapper.ItemEntityMapper;
import org.xfs.scm.app.goods.item.service.ItemServiceI;
import org.xfs.scm.app.goods.item.vo.ItemVo;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemServiceI {
    @Autowired
    private ItemEntityMapper itemEntityMapper;
    @Override
    public int remove(ItemVo vo) {
        return this.itemEntityMapper.removeItem(vo);
    }

    @Override
    public int add(ItemVo vo) {
        return this.itemEntityMapper.addItem(vo);
    }

    @Override
    public List<ItemVo> find(ItemVo vo) {
        return this.itemEntityMapper.findItems(vo);
    }

    @Override
    public ItemVo get(ItemVo vo) {
        List<ItemVo> list=this.find(vo);
        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int modify(ItemVo vo) {
        return this.itemEntityMapper.modifyItem(vo);
    }
}
