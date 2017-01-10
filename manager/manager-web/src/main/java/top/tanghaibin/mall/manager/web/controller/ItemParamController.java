package top.tanghaibin.mall.manager.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.tanghaibin.mall.manager.pojo.ItemParam;
import top.tanghaibin.mall.manager.service.ItemParamService;

/**
 * Created by tangh on 2017/1/10.
 */
@Controller
@RequestMapping("rest/item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping(value = "{itemCartId}", method = RequestMethod.GET)
    public ResponseEntity<Void> queryItemParamByItemCartId(Long itemCartId) {
        try {
            ItemParam itemParam = new ItemParam();
            itemParam.setItemCatId(itemCartId);
            itemParam = itemParamService.queryOne(itemParam);
            if (itemParam == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "{itemCartId}", method = RequestMethod.POST)
    public ResponseEntity<Void> saveItemParam(ItemParam itemParam, @PathVariable("itemCartId") Long itemCartId) {
        try {
            itemParam.setItemCatId(itemCartId);
            itemParamService.save(itemParam);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
