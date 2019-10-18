package com.jishixin.blog.web;

import com.github.pagehelper.PageHelper;
import com.jishixin.blog.po.Blog;
import com.jishixin.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/*
  User: 晨梦意志
  Date: 2019/9/5
  Time: 17:27
  Notes:
*/
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        Map<String, List<Blog>> map = blogService.archiveBlog();
        Integer totle = blogService.blogTotle();
        model.addAttribute("map",map);
        model.addAttribute("totle",totle);
        return "archives";
    }

}
