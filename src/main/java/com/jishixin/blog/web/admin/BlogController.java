package com.jishixin.blog.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jishixin.blog.po.Blog;
import com.jishixin.blog.po.BlogTag;
import com.jishixin.blog.po.Type;
import com.jishixin.blog.po.User;
import com.jishixin.blog.service.BlogService;
import com.jishixin.blog.service.BlogTagService;
import com.jishixin.blog.service.TagService;
import com.jishixin.blog.service.TypeService;
import com.jishixin.blog.util.MarkdownUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

/*
  User: 晨梦意志
  Date: 2019/9/3
  Time: 9:38
  Notes:
*/
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = true,defaultValue = "1") Integer page,@RequestParam(required = false) Blog blog, Model model){
        List<Type> types = typeService.listType();
        PageHelper.startPage(page,3);
        List<Blog> blogs = blogService.listBlog(blog);
        model.addAttribute("blogs",blogs);
        model.addAttribute("types",types);
        System.out.println(blogs);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@RequestParam(required = true,defaultValue = "1") Integer page,Blog blog, Model model){
        PageHelper.startPage(page,3);
        List<Blog> blogs = blogService.listBlog(blog);
        System.out.println(blog);
        model.addAttribute("blogs",blogs);
        System.out.println(blog.getType());
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("types",typeService.listType());
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("types",typeService.listType());
        Blog blog = blogService.getBlog(id);
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        List<Long> longs = blogTagService.selectByBlogId(id);
        String ids="";
        for(int i =0;i<longs.size();i++){
            ids+=longs.get(i)+",";
        }
        ids.substring(0,ids.length()-1);
        blog.setTagsids(ids);
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        System.out.println(blog);
        if (blog.getCreateTime()==null){
            blog.setCreateTime(new Date());
        }
        blog.setUpdateTime(new Date());
        String ids = blog.getTagsids();
        ids=ids.substring(0,ids.length()-1);
        if (blog.getId()!=null){
            blogService.updateBlog(blog);
            blogTagService.deleteByBlog(blog.getId());
            blogTagService.save(blog.getId(),ids);
        }else{
            blog.setViews(0);
            blog.setUser((User) session.getAttribute("user"));
            blogService.saveBlog(blog);
            blogTagService.save(blog.getId(),blog.getTagsids());
        }
        if (blog.getId()!=null){
            attributes.addFlashAttribute("message","操作成功!");
        }else{
            attributes.addFlashAttribute("message","操作失败!");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        blogTagService.deleteByBlog(id);
        attributes.addFlashAttribute("message","删除成功!");
        return "redirect:/admin/blogs";
    }

}
