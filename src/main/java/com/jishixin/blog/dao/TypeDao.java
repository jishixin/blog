package com.jishixin.blog.dao;/*
  User: 晨梦意志
  Date: 2019/9/3
  Time: 10:14
  Notes:
*/

import com.jishixin.blog.po.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDao{

    @Insert("insert into t_type values(null,#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Type type);

    @Select("select * from t_type where id = #{id}")
    Type selectOne(Long id);

    @Select("select * from t_type order by id desc")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(property = "blogs",column = "id",javaType = List.class,
                    many = @Many(select = "com.jishixin.blog.dao.BlogDao.listBlogByType"))
    })
    List<Type> selectAll();

    @Update("UPDATE t_type SET name = #{name} WHERE id = #{id}")
    void updateType(Type type);

    @Delete("DELETE FROM t_type WHERE id = #{id} ")
    void deleteType(Long id);

    @Select("select * from t_type where name=#{name}")
    Type getTypeByName(String name);
}
