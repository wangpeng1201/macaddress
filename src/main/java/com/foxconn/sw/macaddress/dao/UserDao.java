package com.foxconn.sw.macaddress.dao;

import com.foxconn.sw.macaddress.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    List<User> getUser(String username);

    User getUserById(Integer pid);

    int addUser(User user);

    int updateUser(User user);

    int deleteUserByPid(Integer pid);

    String selectPassword(String username);

    int updatePassword(@Param("old") String old, @Param("news") String news, @Param("name") String name);

}
