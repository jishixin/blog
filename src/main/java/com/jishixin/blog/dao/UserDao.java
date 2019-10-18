package com.jishixin.blog.dao;/*
  User: 晨梦意志
  Date: 2019/9/3
  Time: 8:44
  Notes:
*/

import com.jishixin.blog.po.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Select("select id,username,password,nickname,email from t_user where username = #{username} and password = #{password}")
    User findByUsernameAndPassword(String username,String password);

    @Select("select * from t_user where id = #{id}")
    User findById(Long id);
}
