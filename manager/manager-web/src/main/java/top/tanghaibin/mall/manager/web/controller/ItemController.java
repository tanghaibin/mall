package top.tanghaibin.mall.manager.web.controller;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.tanghaibin.common.bean.EasyUIResult;
import top.tanghaibin.mall.manager.pojo.Item;
import top.tanghaibin.mall.manager.pojo.ItemParam;
import top.tanghaibin.mall.manager.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by tangh on 2017/1/3.
 */
@Controller
@RequestMapping("rest/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Void> saveItem(Item item, String itemParams, String desc) {
        if (StringUtils.isBlank(item.getTitle()) || StringUtils.isBlank(desc)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            item.setId(null);
            item.setStatus(1);
            itemService.saveItem(item, itemParams, desc);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateItem(Item item, String itemParams, String desc, HttpServletRequest request) {
        Enumeration parameterNames = request.getParameterNames();
        if (StringUtils.isBlank(item.getTitle()) || StringUtils.isBlank(desc)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            itemService.updateItem(item, itemParams, desc);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<EasyUIResult> queryItemList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                      @RequestParam(value = "rows", defaultValue = "30") Integer rows) {
        try {
            PageInfo<Item> items = itemService.queryPageListByWhereAndOrderBy(page, rows, new Item(), "created");
            EasyUIResult result = new EasyUIResult(items.getTotal(), items.getList());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
