package com.cjt.dao.demo;

import com.cjt.entity.demo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDao {

  List<User> findAll();

  void saveUser(User user);

  void saveUserBatch(List<User> users);

  void updateUserBatch(@Param("users") List<User> users, @Param("ids") List<Integer> ids);
}
