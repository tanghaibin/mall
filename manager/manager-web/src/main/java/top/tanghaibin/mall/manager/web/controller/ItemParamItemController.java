package top.tanghaibin.mall.manager.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.tanghaibin.mall.manager.pojo.ItemParamItem;
import top.tanghaibin.mall.manager.service.ItemParamItemService;

/**
 * Created by tangh on 2017/1/14.
 */
@Controller
@RequestMapping("rest/item/param/item")
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping(value = "query/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<ItemParamItem> queryItemParamByItemId(ItemParamItem itemParamItem) {

        try {
            itemParamItem = itemParamItemService.queryOne(itemParamItem);
            if (itemParamItem == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(itemParamItem);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
