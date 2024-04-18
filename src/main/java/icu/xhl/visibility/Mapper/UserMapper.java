package icu.xhl.visibility.Mapper;

import icu.xhl.visibility.Entity.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();
    User selectById(int id);
}
