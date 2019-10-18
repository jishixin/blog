package com.jishixin.blog.service;

import com.jishixin.blog.dao.CommentDao;
import com.jishixin.blog.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/*
  User: 晨梦意志
  Date: 2019/9/5
  Time: 13:43
  Notes:
*/
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;


    @Override
    public List<Comment> listCommentById(Long blogId) {
        List<Comment> comments = commentDao.findByBlogIdAndNoParent(blogId);
        for (int i=0;i<comments.size();i++){
            List<Comment> replyComments = commentDao.findByParentId(comments.get(i).getId());
            System.out.println(replyComments);
            comments.get(i).setReplyComments(replyComments);
        }
        return comments;
    }

    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId=comment.getParentComment().getId();
        comment.setAvatar("https://picsum.photos/id/1011/100/100");
        if (parentCommentId!=-1){
            comment.setParentComment(commentDao.selectOne(parentCommentId));
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        commentDao.save(comment);
        return comment;
    }

}
