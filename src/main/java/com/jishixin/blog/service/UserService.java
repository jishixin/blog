package com.jishixin.blog.service;/*
  User: 晨梦意志
  Date: 2019/9/3
  Time: 8:42
  Notes:
*/

import com.jishixin.blog.po.User;

public interface UserService {

    User checkUser(String username,String password);

}
