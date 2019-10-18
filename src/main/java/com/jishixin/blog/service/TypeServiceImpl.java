package com.jishixin.blog.service;

import com.github.pagehelper.Page;
import com.jishixin.blog.dao.TypeDao;
import com.jishixin.blog.po.Type;
import com.jishixin.blog.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
  User: 晨梦意志
  Date: 2019/9/3
  Time: 10:13
  Notes:
*/
@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public void saveType(Type type) {
        typeDao.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.selectOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeDao.selectAll();
    }

    @Override
    public void updateType(Long id, Type type) {
        Type t = typeDao.selectOne(id);
        if (t==null){
            throw new NotFoundException("不存在该类型!");
        }
        typeDao.updateType(type);
    }

    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }
}
