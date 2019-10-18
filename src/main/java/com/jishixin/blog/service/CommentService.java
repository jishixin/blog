package com.jishixin.blog.service;/*
  User: 晨梦意志
  Date: 2019/9/5
  Time: 13:41
  Notes:
*/

import com.jishixin.blog.po.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentById(Long blogId);

    Comment saveComment(Comment comment);

}
