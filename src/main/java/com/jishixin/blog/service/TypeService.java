package com.jishixin.blog.service;/*
  User: 晨梦意志
  Date: 2019/9/3
  Time: 10:13
  Notes:
*/

import com.github.pagehelper.Page;
import com.jishixin.blog.dao.TypeDao;
import com.jishixin.blog.po.Tag;
import com.jishixin.blog.po.Type;

import java.awt.print.Pageable;
import java.util.List;

public interface TypeService {

    void saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    List<Type> listType();

    void updateType(Long id ,Type type);

    void deleteType(Long id);

}
