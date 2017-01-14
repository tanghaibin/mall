package top.tanghaibin.mall.manager.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.tanghaibin.mall.manager.pojo.ItemDesc;
import top.tanghaibin.mall.manager.service.ItemDescService;

/**
 * Created by tangh on 2017/1/14.
 */
@Controller
@RequestMapping("rest/item/desc")
public class ItemDescController {

    @Autowired
    private ItemDescService itemDescService;

    @RequestMapping(value = "{itemId}", method = RequestMethod.GET)
    public ResponseEntity<ItemDesc> queryItemDescByItemId(@PathVariable(value = "itemId") Long itemId) {
        try {
            ItemDesc itemDesc = new ItemDesc();
            itemDesc.setItemId(itemId);
            itemDesc = itemDescService.queryOne(itemDesc);
            if (itemDesc == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(itemDesc);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
