package top.tanghaibin.mall.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import top.tanghaibin.mall.manager.mapper.ItemParamItemMapper;
import top.tanghaibin.mall.manager.pojo.ItemParamItem;

/**
 * Created by tangh on 2017/1/10.
 */
@Service
public class ItemParamItemService extends BaseService<ItemParamItem> {

    @Autowired
    private ItemParamItemMapper itemParamItemMapper;

    public void updateItemParamItemByItemId(ItemParamItem itemParamItem) {
        Example example = new Example(ItemParamItem.class);
        example.createCriteria().andEqualTo("itemId", itemParamItem.getItemId());
        itemParamItemMapper.updateByExampleSelective(itemParamItem, example);
    }
}
