package top.tanghaibin.mall.manager.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.tanghaibin.commom.exception.BizRuntimeException;
import top.tanghaibin.mall.manager.pojo.User;
import top.tanghaibin.mall.manager.service.UserService;

/**
 * Created by tanghaibin on 2016/12/27.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("add/{name}/{age}")
    @ResponseBody
    public ResponseEntity<Object> add(User user) {
        try {
            userService.create(user);
            return ResponseEntity.ok(null);
        }catch (BizRuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id) {
        userService.delete(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    @ResponseBody
    public ResponseEntity get(User user) {

          user = userService.search(user);
          if(user == null) {
              return ResponseEntity.noContent().build();
          }
          return ResponseEntity.ok(user);
    }
}
