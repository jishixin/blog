package com.jishixin.blog.web;

import com.github.pagehelper.PageHelper;
import com.jishixin.blog.po.Blog;
import com.jishixin.blog.po.Type;
import com.jishixin.blog.service.BlogService;
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
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("types/{id}")
    public String types(@PathVariable Long id, @RequestParam(required = true,defaultValue = "1") Integer page, Model model){
        PageHelper.startPage(page,999);
        List<Type> types = typeService.listType();
        if (id == -1){
            id=types.get(0).getId();
        }
        PageHelper.startPage(page,6);
        List<Blog> blogs = blogService.listBlogsByTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);
        model.addAttribute("id",id);
        return "types";
    }

}
