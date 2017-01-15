package top.tanghaibin.mall.manager.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.tanghaibin.common.bean.ItemCatResult;
import top.tanghaibin.mall.manager.service.ItemCatService;

/**
 * Created by tangh on 2017/1/15.
 */
@Controller
@RequestMapping("api/item/cat")
public class ApiItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @GetMapping
    public ResponseEntity<ItemCatResult> queryItemCat() {
        try {
            ItemCatResult itemCatResult = itemCatService.queryAllToTree();
            if (itemCatResult == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(itemCatResult);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
