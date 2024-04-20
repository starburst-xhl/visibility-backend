package icu.xhl.visibility.Controller;

import icu.xhl.visibility.Service.UserService;
import icu.xhl.visibility.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/All")
    public Map<String,Object> selectAll(@RequestHeader(value = "Authorization")String token) {
        Map<String,Object> map = new HashMap<>();
        if(userService.selectAll(token) == null) {
            map.put("code",101);
            map.put("msg","没有权限");
        } else {
            map.put("code",200);
            map.put("msg","验证成功");
            map.put("data",userService.selectAll(token));
        }
        return map;
    }

    @GetMapping
    public Map<String, Object> selectByUsername(@RequestParam String username, @RequestHeader(value = "Authorization")String token) {
        Map<String,Object> map = new HashMap<>();
        if(userService.selectByUsername(username,token) == null || !TokenUtil.verifyManager(token)) {
            map.put("code",101);
            map.put("msg","没有权限");
        } else {
            map.put("code",200);
            map.put("msg","验证成功");
            map.put("data",userService.selectByUsername(username,token));
        }
        return map;
    }

    @GetMapping("/info")
    public Map<String, Object> selectByToken(@RequestHeader(value = "Authorization")String token) {
        Map<String,Object> map = new HashMap<>();
        if (TokenUtil.verify(token)) {
            map.put("code",200);
            map.put("msg","验证成功");
            map.put("data",userService.selectByUsername(TokenUtil.getUsername(token),token));
        } else {
            map.put("code",101);
            map.put("msg","没有权限");
        }
        return map;
    }

    @PutMapping("/info")
    public Map<String, Object> updateInfo(@RequestHeader(value = "Authorization")String token,
                                          @RequestParam String password,
                                          @RequestParam String phone_nbr) {
        Map<String,Object> map = new HashMap<>();
        if (TokenUtil.verify(token)) {
            int id = userService.selectByUsername(TokenUtil.getUsername(token),token).getId();
            map.put("code",200);
            map.put("msg","验证成功");
            map.put("data",userService.updateInfo(id,TokenUtil.getUsername(token),password,phone_nbr));
        } else {
            map.put("code",101);
            map.put("msg","没有权限");
        }
        return map;
    }

    @PutMapping
    public Map<String, Object> updateInfoManage(@RequestHeader(value = "Authorization")String token,
                                                @RequestParam String username,
                                                @RequestParam String password,
                                                @RequestParam String phone_nbr,
                                                @RequestParam String role) {
        Map<String,Object> map = new HashMap<>();
        if (TokenUtil.verifyManager(token)) {
            int id = userService.selectByUsername(username,token).getId();
            map.put("code",200);
            map.put("msg","验证成功");
            map.put("data",userService.updateUser(id,username,password,phone_nbr,role));
        } else {
            map.put("code",101);
            map.put("msg","没有权限");
        }
        return map;
    }

    @DeleteMapping
    public Map<String, Object> delete(@RequestHeader(value = "Authorization")String token,
                                      @RequestParam String username) {
        Map<String,Object> map = new HashMap<>();
        if (TokenUtil.verifyManager(token)) {
            int id = userService.selectByUsername(username,token).getId();
            map.put("code",200);
            map.put("msg","验证成功");
            map.put("data",userService.delete(id));
        } else {
            map.put("code",101);
            map.put("msg","没有权限");
        }
        return map;
    }

    @PostMapping
    public Map<String, Object> insert(@RequestHeader(value = "Authorization")String token,
                                      @RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String phone_nbr,
                                      @RequestParam String role) {
        Map<String,Object> map = new HashMap<>();
        if (TokenUtil.verifyManager(token)) {
            Timestamp createAt = new Timestamp(System.currentTimeMillis());
            map.put("code",200);
            map.put("msg","验证成功");
            map.put("data",userService.insert(username,password,phone_nbr,role,createAt));
        } else {
            map.put("code",101);
            map.put("msg","没有权限");
        }
        return map;
    }
}
