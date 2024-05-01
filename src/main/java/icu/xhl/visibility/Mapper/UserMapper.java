package icu.xhl.visibility.Mapper;

import icu.xhl.visibility.Entity.User;
import org.apache.ibatis.annotations.Select;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface UserMapper {
    List<User> selectAll(String token);
    User selectByUsername(String username, String token);

    int updateInfo(int id,String username, String password, String phoneNbr);

    int delete(int id);

    int updatePassword(int id, String password);

    int updateUser(int id, String username, String password, String phoneNbr, String role);

    int insert(String username, String password, String phoneNbr, String role, Timestamp createAt);
}
