package com.cjt.service.impl;

import com.cjt.common.dto.UserDto;
import com.cjt.common.encrypt.EncryptUtil;
import com.cjt.entity.demo.User;
import com.cjt.service.IUserService;
import com.cjt.dao.demo.IUserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {

  @Resource
  private IUserDao userDao;

  @Override
  public Long login(String account, String password) {
    return userDao.login(account, EncryptUtil.encrypt(password));
  }

  @Override
  public User getUserByDto(UserDto userDto) {
    return userDao.getUserByDto(userDto);
  }

  @Override
  public boolean existAccount(String account) {
    return userDao.existAccount(account);
  }

  public List<User> listAllUsers() {
    return userDao.findAll();
  }

  /**
   * 若发生checked exception(IOException且抛出)不会发生回滚，可以采用setRollbackOnly
   */
  @Transactional
  public void saveUser(User user) {
    userDao.saveUser(user);
  }

  public void saveUsers(List<User> users) {
    userDao.saveUserBatch(users);
  }

  public void updateUsers(List<User> users) {
    List<Long> ids = new ArrayList<>();
    for (User user : users) {
      ids.add(user.getId());
    }
    userDao.updateUserBatch(users, ids);
  }
}