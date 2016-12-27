package top.tanghaibin.mall.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tanghaibin.mall.manager.mapper.ItemCatMapper;
import top.tanghaibin.mall.manager.pojo.ItemCat;
import top.tanghaibin.mall.manager.service.ItemCatService;

import java.util.List;

/**
 * Created by tangh on 2016/12/27.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat> queryItemCatListByParentId(Long parentId) {

        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(parentId);
        return itemCatMapper.select(itemCat);
    }
}
