package top.tanghaibin.mall.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tanghaibin.mall.manager.pojo.Item;
import top.tanghaibin.mall.manager.pojo.ItemDesc;

/**
 * Created by tangh on 2017/1/3.
 */
@Service
public class ItemService extends BaseService<Item> {

    @Autowired
    private ItemDescService itemDescService;

    public void saveItem(Item item, String desc) {
        super.save(item);
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        itemDescService.save(itemDesc);
    }
}
