package com.jishixin.blog.po;

import org.springframework.stereotype.Component;

/*
  User: 晨梦意志
  Date: 2019/9/4
  Time: 14:56
  Notes:
*/
@Component
public class BlogTag {

    private Long blogId;

    private Long tagId;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public BlogTag() {
    }

    public BlogTag(Long blogId, Long tagId) {
        this.blogId = blogId;
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "BlogTag{" +
                "blogId=" + blogId +
                ", tagId=" + tagId +
                '}';
    }
}
