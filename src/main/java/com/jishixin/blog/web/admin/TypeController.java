package com.jishixin.blog.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jishixin.blog.po.Type;
import com.jishixin.blog.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@RequestParam(required = true,defaultValue = "1") Integer page, Model model){
        PageHelper.startPage(page,3);
        List<Type> types = typeService.listType();
        PageInfo<Type> pageInfo=new PageInfo<>(types);
        model.addAttribute("types",types);
        System.out.println(types);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(){
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String save(Type type, RedirectAttributes attributes){
        if (typeService.getTypeByName(type.getName())!=null){
            attributes.addFlashAttribute("message","分类名称重复!");
            return "redirect:/admin/types";
        }
        typeService.saveType(type);
        if (type==null){
            attributes.addFlashAttribute("message","操作失败!");
        }else {
            attributes.addFlashAttribute("message","操作成功!");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String update(@PathVariable Long id,Type type, RedirectAttributes attributes){
        if (typeService.getTypeByName(type.getName())!=null){
            attributes.addFlashAttribute("message","分类名称重复!");
            return "redirect:/admin/types";
        }
        typeService.updateType(id, type);
        if (type==null){
            attributes.addFlashAttribute("message","更新失败!");
        }else {
            attributes.addFlashAttribute("message","更新成功!");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        if (typeService.getType(id)==null){
            attributes.addFlashAttribute("message","删除失败!");
            return "redirect:/admin/types";
        }
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功!");
        return "redirect:/admin/types";
    }

}
