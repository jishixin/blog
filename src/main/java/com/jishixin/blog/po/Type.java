package com.jishixin.blog.po;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*
  User: 晨梦意志
  Date: 2019/9/2
  Time: 15:02
  Notes:
*/
@Component
public class Type {
    private Long id;
    private String name;
    private List<Blog> blogs =new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
