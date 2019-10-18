package com.jishixin.blog.web;

import com.github.pagehelper.PageHelper;
import com.jishixin.blog.po.Blog;
import com.jishixin.blog.po.Comment;
import com.jishixin.blog.po.Tag;
import com.jishixin.blog.po.Type;
import com.jishixin.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
  User: 晨梦意志
  Date: 2019/9/2
  Time: 12:48
  Notes:
*/
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogTagService blogTagService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(@RequestParam(required = true,defaultValue = "1") Integer page, @RequestParam(required = false) Blog blog, Model model){
        PageHelper.startPage(page,6);
        List<Blog> blogs = blogService.listBlogs();
        PageHelper.startPage(1,6);
        List<Type> types = typeService.listType();
        PageHelper.startPage(1,6);
        List<Tag> tags = tagService.listTag();
        PageHelper.startPage(1,6);
        List<Blog> blogList = blogService.listBlogByRecommend();
        model.addAttribute("blogs",blogs);
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("blogList",blogList);
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = true,defaultValue = "1") Integer page,
                         Model model,@RequestParam(required = false) String query){
        PageHelper.startPage(page,6);
        List<Blog> blogs = blogService.listBlogsByQuery(query);
        model.addAttribute("blogs",blogs);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        blogService.addViews(id);
        model.addAttribute("blog",blogService.getBlog(id));
        List<Long> longs = blogTagService.selectByBlogId(id);
        List<Tag> tags = tagService.selectTagInIds(longs);
        List<Comment> comments = commentService.listCommentById(id);
        model.addAttribute("comments",comments);
        model.addAttribute("tags",tags);
        return "blog";
    }

    @GetMapping("/footer/newblog")
    @ResponseBody
    public List<Blog> newblogs(Model model){
        PageHelper.startPage(1,3);
        List<Blog> blogList = blogService.listnewBlogs();
        System.out.println("_______________________________________________");
        return blogList;
    }

}
