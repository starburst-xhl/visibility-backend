package icu.xhl.visibility.Service;

import icu.xhl.visibility.Entity.User;
import icu.xhl.visibility.Mapper.UserMapper;
import icu.xhl.visibility.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service(value="userService")
public class UserService implements UserMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll(String token) {
        if (!TokenUtil.verifyManager(token)) {
            return null;
        }
        return userMapper.selectAll(token);
    }

    @Override
    public User selectByUsername(String username, String token) {
        return userMapper.selectByUsername(username,token);
    }

    @Override
    public int updateInfo(int id, String username, String password, String phoneNbr) {
        return userMapper.updateInfo(id,username, password, phoneNbr);
    }

    @Override
    public Object delete(int id) {
        return userMapper.delete(id);
    }

    @Override
    public int updatePassword(int id, String password) {
        return userMapper.updatePassword(id, password);
    }

    @Override
    public int updateUser(int id, String username, String password, String phoneNbr, String role) {
        return userMapper.updateUser(id, username, password, phoneNbr, role);
    }

    @Override
    public Object insert(String username, String password, String phoneNbr, String role, Timestamp createAt) {
        return userMapper.insert(username, password, phoneNbr, role, createAt);
    }
}
