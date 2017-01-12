package top.tanghaibin.mall.manager.web.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.tanghaibin.common.bean.EasyUIResult;
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                             @RequestParam(value = "rows", defaultValue = "30") Integer rows) {
        try {
            PageInfo<ItemParam> pageInfo = itemParamService.queryPageListByWhereAndOrderBy(page, rows, new ItemParam(), "created");
            return ResponseEntity.status(HttpStatus.OK).body(new EasyUIResult(pageInfo.getTotal(), pageInfo.getList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "query/itemcatid/{itemCartId}", method = RequestMethod.GET)
    public ResponseEntity<ItemParam> query(@PathVariable(value = "itemCartId") Long itemCartId) {
        try{
            ItemParam itemParam = new ItemParam();
            itemParam.setItemCatId(itemCartId);
            itemParam = itemParamService.queryOne(itemParam);
            if(itemParam == null ) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(itemParam);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
