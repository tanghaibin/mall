package top.tanghaibin.mall.manager.service;

import top.tanghaibin.mall.manager.pojo.ItemCat;

import java.util.List;

/**
 * Created by tangh on 2016/12/27.
 */
public interface ItemCatService {

    List<ItemCat> queryItemCatListByParentId(Long parentId);
}
