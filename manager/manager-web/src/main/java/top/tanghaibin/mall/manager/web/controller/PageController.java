package top.tanghaibin.mall.manager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tangh on 2016/12/27.
 */
@Controller
@RequestMapping("rest/page")
public class PageController {

    @RequestMapping(value = "{pageName}", method = RequestMethod.GET)
    public String toPage(@PathVariable(value = "pageName") String pageName) {
        return pageName;
    }
}
