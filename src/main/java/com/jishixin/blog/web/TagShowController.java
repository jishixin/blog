package com.jishixin.blog.web;

import com.github.pagehelper.PageHelper;
import com.jishixin.blog.po.Blog;
import com.jishixin.blog.po.Tag;
import com.jishixin.blog.po.Type;
import com.jishixin.blog.service.BlogService;
import com.jishixin.blog.service.BlogTagService;
import com.jishixin.blog.service.TagService;
import com.jishixin.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
  User: 晨梦意志
  Date: 2019/9/5
  Time: 16:03
  Notes:
*/
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("tags/{id}")
    public String tags(@PathVariable Long id, @RequestParam(required = true,defaultValue = "1") Integer page, Model model){
        PageHelper.startPage(page,999);
        List<Tag> tags = tagService.listTag();
        model.addAttribute("tags",tags);
        if (id == -1){
            id=tags.get(0).getId();
        }
        List<Long> longs = blogTagService.selectByTagId(id);
        PageHelper.startPage(page,6);
        List<Blog> blogs = blogService.listBlogsByTagId(longs);
        model.addAttribute("blogs",blogs);
        model.addAttribute("id",id);
        return "tags";
    }

}
