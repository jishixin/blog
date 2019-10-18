package com.jishixin.blog.service;

import com.jishixin.blog.dao.UserDao;
import com.jishixin.blog.po.User;
import com.jishixin.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
  User: 晨梦意志
  Date: 2019/9/3
  Time: 8:43
  Notes:
*/
@Service
@Transactional
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        password= MD5Utils.MD5Encode(password,"utf-8");
        User user=userDao.findByUsernameAndPassword(username,password);
        return user;
    }
}
