package com.cjt.dao.demo;

import com.cjt.common.dto.UserDto;
import com.cjt.entity.demo.User;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.List;

public interface IUserDao {

  boolean existAccount(String account);

  Long login(@Param("account") String account, @Param("password") String password);

  User getUserByDto(UserDto userDto);

  List<User> findAll();

  void saveUser(User user);

  void saveUserBatch(List<User> users);

  void updateUserBatch(@Param("users") List<User> users, @Param("ids") List<Long> ids);
}
