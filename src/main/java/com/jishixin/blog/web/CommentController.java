package com.jishixin.blog.web;

import com.jishixin.blog.po.Comment;
import com.jishixin.blog.po.User;
import com.jishixin.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/*
  User: 晨梦意志
  Date: 2019/9/5
  Time: 13:37
  Notes:
*/
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        List<Comment> comments = commentService.listCommentById(blogId);
        model.addAttribute("comments",comments);
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user!=null){
            comment.setAdminComment(true);
            comment.setAvatar("https://picsum.photos/id/1005/100/100");
        }else {
            comment.setAdminComment(false);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+ comment.getBlog().getId();
    }

}
