package com.jishixin.blog.dao;/*
  User: 晨梦意志
  Date: 2019/9/3
  Time: 10:14
  Notes:
*/

import com.jishixin.blog.po.Tag;
import com.jishixin.blog.po.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao {

    @Insert("insert into t_tag values(null,#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Tag tag);

    @Select("select * from t_tag where id = #{id}")
    Tag selectOne(Long id);

    @Select("select * from t_tag order by id desc")
    List<Tag> selectAll();

    @Update("UPDATE t_tag SET name = #{name} WHERE id = #{id}")
    void updateTag(Tag tag);

    @Delete("DELETE FROM t_tag WHERE id = #{id} ")
    void deleteTag(Long id);

    @Select("select * from t_tag where name=#{name}")
    Tag getTagByName(String name);

    @Select("<script> select * from t_tag where id in " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>#{id} </foreach>" +
            "</script>")
    List<Tag> selectTagInIds(@Param("ids") List<Long> ids);
}
