package top.tanghaibin.mall.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tangh on 2017/1/15.
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public ModelAndView toIndexPage() {
        return new ModelAndView("index");
    }
}
