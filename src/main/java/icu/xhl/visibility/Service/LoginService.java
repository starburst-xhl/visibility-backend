package icu.xhl.visibility.Service;

import icu.xhl.visibility.Mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="loginService")
public class LoginService implements LoginMapper {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public String selectByUsername(String username) {
        return loginMapper.selectByUsername(username);
    }

    @Override
    public String selectRoleByUsername(String username) {
        return loginMapper.selectRoleByUsername(username);
    }
}
