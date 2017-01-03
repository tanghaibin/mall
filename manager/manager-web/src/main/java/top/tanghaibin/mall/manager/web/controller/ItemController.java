package top.tanghaibin.mall.manager.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.tanghaibin.mall.manager.pojo.Item;
import top.tanghaibin.mall.manager.service.ItemService;

/**
 * Created by tangh on 2017/1/3.
 */
@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "saveItem", method = RequestMethod.POST)
    public ResponseEntity<Void> saveItem(Item item, String desc) {
        if(StringUtils.isBlank(item.getTitle()) || StringUtils.isBlank(desc)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            item.setId(null);
            itemService.saveItem(item, desc);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
