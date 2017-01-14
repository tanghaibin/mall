package top.tanghaibin.mall.manager.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tanghaibin.mall.manager.pojo.Item;
import top.tanghaibin.mall.manager.pojo.ItemDesc;
import top.tanghaibin.mall.manager.pojo.ItemParamItem;

import java.util.Date;

/**
 * Created by tangh on 2017/1/3.
 */
@Service
public class ItemService extends BaseService<Item> {

    @Autowired
    private ItemDescService itemDescService;
    @Autowired
    private ItemParamItemService itemParamItemService;

    public void saveItem(Item item, String paramData, String desc) {
        super.save(item);
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        itemDescService.save(itemDesc);
        if(StringUtils.isNotBlank(paramData)) {
            ItemParamItem itemParamItem = new ItemParamItem();
            itemParamItem.setParamData(paramData);
            itemParamItem.setItemId(item.getId());
            itemParamItemService.save(itemParamItem);
        }
    }

    public void updateItem(Item item, String paramData, String desc) {
        super.updateBySelective(item);
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        itemDescService.updateBySelective(itemDesc);
        if(StringUtils.isNotBlank(paramData)) {
            ItemParamItem itemParamItem = new ItemParamItem();
            itemParamItem.setParamData(paramData);
            itemParamItem.setItemId(item.getId());
            itemParamItemService.updateItemParamItemByItemId(itemParamItem);
        }
    }
}
