package icu.xhl.visibility.Controller;

import icu.xhl.visibility.Entity.User;
import icu.xhl.visibility.Mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserMapper userMapper;

    @GetMapping
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
