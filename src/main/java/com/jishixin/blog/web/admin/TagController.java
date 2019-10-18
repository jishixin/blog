package com.jishixin.blog.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jishixin.blog.po.Tag;
import com.jishixin.blog.service.BlogTagService;
import com.jishixin.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/*
  User: 晨梦意志
  Date: 2019/9/3
  Time: 10:41
  Notes:
*/
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("/tags")
    public String tags(@RequestParam(required = true,defaultValue = "1") Integer page, Model model){
        PageHelper.startPage(page,3);
        List<Tag> tags = tagService.listTag();
        PageInfo<Tag> pageInfo=new PageInfo<>(tags);
        model.addAttribute("tags",tags);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(){
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        Tag tags=tagService.getTag(id);
        model.addAttribute("tag",tags);
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String save(Tag tag, RedirectAttributes attributes){
        if (tagService.getTagByName(tag.getName())!=null){
            attributes.addFlashAttribute("message","标签名称重复!");
            return "redirect:/admin/tags";
        }
        tagService.saveTag(tag);
        if (tag==null){
            attributes.addFlashAttribute("message","操作失败!");
        }else {
            attributes.addFlashAttribute("message","操作成功!");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String update(@PathVariable Long id,Tag tag, RedirectAttributes attributes){
        if (tagService.getTagByName(tag.getName())!=null){
            attributes.addFlashAttribute("message","标签名称重复!");
            return "redirect:/admin/tags";
        }
        tagService.updateTag(id, tag);
        if (tag==null){
            attributes.addFlashAttribute("message","更新失败!");
        }else {
            attributes.addFlashAttribute("message","更新成功!");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        if (tagService.getTag(id)==null){
            attributes.addFlashAttribute("message","删除失败!");
            return "redirect:/admin/tags";
        }
        tagService.deleteTag(id);
        blogTagService.deleteByTag(id);
        attributes.addFlashAttribute("message","删除成功!");
        return "redirect:/admin/tags";
    }

}
