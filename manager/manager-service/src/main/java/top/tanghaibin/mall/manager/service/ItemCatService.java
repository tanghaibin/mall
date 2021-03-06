package top.tanghaibin.mall.manager.service;

import org.springframework.stereotype.Service;
import top.tanghaibin.common.bean.ItemCatData;
import top.tanghaibin.common.bean.ItemCatResult;
import top.tanghaibin.mall.manager.pojo.ItemCat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangh on 2017/1/2.
 */
@Service
public class ItemCatService extends BaseService<ItemCat> {

    /**
     * 全部查询，并且生成树状结构
     * @return
     */
    public ItemCatResult queryAllToTree() {
        ItemCatResult result = new ItemCatResult();
        // 全部查出，并且在内存中生成树形结构
        List<ItemCat> itemCats = queryAll();
        // 转为map存储，key为父节点ID，value为数据集合
        Map<Long, List<ItemCat>> itemCatMap = new HashMap<Long, List<ItemCat>>();
        for (ItemCat itemCat : itemCats) {
            if(!itemCatMap.containsKey(itemCat.getParentId())){
                itemCatMap.put(itemCat.getParentId(), new ArrayList<ItemCat>());
            }
            itemCatMap.get(itemCat.getParentId()).add(itemCat);
        }

        // 封装一级对象
        List<ItemCat> itemCatList1 = itemCatMap.get(0L);
        for (ItemCat itemCat : itemCatList1) {
            ItemCatData itemCatData = new ItemCatData();
            itemCatData.setUrl("/products/" + itemCat.getId() + ".html");
            itemCatData.setNname("<a href='"+itemCatData.getUrl()+"'>"+itemCat.getName()+"</a>");
            result.getItemCats().add(itemCatData);
            if(!itemCat.getIsParent()){
                continue;
            }

            // 封装二级对象
            List<ItemCat> itemCatList2 = itemCatMap.get(itemCat.getId());
            List<ItemCatData> itemCatData2 = new ArrayList<ItemCatData>();
            itemCatData.setItems(itemCatData2);
            for (ItemCat itemCat2 : itemCatList2) {
                ItemCatData id2 = new ItemCatData();
                id2.setNname(itemCat2.getName());
                id2.setUrl("/products/" + itemCat2.getId() + ".html");
                itemCatData2.add(id2);
                if(itemCat2.getIsParent()){
                    // 封装三级对象
                    List<ItemCat> itemCatList3 = itemCatMap.get(itemCat2.getId());
                    List<String> itemCatData3 = new ArrayList<String>();
                    id2.setItems(itemCatData3);
                    for (ItemCat itemCat3 : itemCatList3) {
                        itemCatData3.add("/products/" + itemCat3.getId() + ".html|"+itemCat3.getName());
                    }
                }
            }
            if(result.getItemCats().size() >= 14){
                break;
            }
        }
        return result;
    }
}
