package com.jishixin.blog.service;/*
  User: 晨梦意志
  Date: 2019/9/3
  Time: 10:13
  Notes:
*/

import com.jishixin.blog.po.Tag;
import com.jishixin.blog.po.Type;

import java.util.List;

public interface TagService {

    void saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> listTag();

    void updateTag(Long id, Tag tag);

    void deleteTag(Long id);

    List<Tag> selectTagInIds(List<Long> ids);

}
