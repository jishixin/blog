package com.jishixin.blog.service;

import com.jishixin.blog.dao.TagDao;
import com.jishixin.blog.dao.TypeDao;
import com.jishixin.blog.po.Tag;
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
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public void saveTag(Tag tag) {
        tagDao.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDao.selectOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.selectAll();
    }

    @Override
    public void updateTag(Long id, Tag tag) {
        Tag t = tagDao.selectOne(id);
        if (t==null){
            throw new NotFoundException("不存在该类型!");
        }
        tagDao.updateTag(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagDao.deleteTag(id);
    }

    @Override
    public List<Tag> selectTagInIds(List<Long> ids) {
        return tagDao.selectTagInIds(ids);
    }
}
