package icu.xhl.visibility.Controller;

import icu.xhl.visibility.Service.LoginService;
import icu.xhl.visibility.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public Map<String, Object> login(@RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password) {
        Map<String, Object> map = new HashMap<>();
        String realPassword =  loginService.selectByUsername(username);
        if (realPassword == null) {
            map.put("code",100);
            map.put("msg","用户名不存在");
        } else if (!realPassword.equals(password)) {
            map.put("code",101);
            map.put("msg","密码错误");
        } else {
            map.put("code",200);
            map.put("msg","登录成功");
            map.put("token", TokenUtil.token(username, password));
            map.put("role", loginService.selectRoleByUsername(username));
        }
        System.out.println(map);
        return map;
    }

    @GetMapping("/token")
    public Map<String, Object> verify(@RequestHeader(value = "Authorization") String token) {
        Map<String, Object> map = new HashMap<>();
        if (TokenUtil.verify(token)) {
            map.put("code",200);
            map.put("msg","验证成功");
            map.put("isLogin", true);
            map.put("role", loginService.selectRoleByUsername(TokenUtil.getUsername(token)));
        } else {
            map.put("code",101);
            map.put("msg","验证失败");
            map.put("isLogin", false);
        }
        System.out.println(map);
        return map;
    }
}
