package com.jishixin.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
  User: 晨梦意志
  Date: 2019/9/5
  Time: 18:05
  Notes:
*/
@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
