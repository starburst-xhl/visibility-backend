package icu.xhl.visibility.Mapper;

public interface LoginMapper {
    String selectByUsername(String username);
    String selectRoleByUsername(String username);
}
