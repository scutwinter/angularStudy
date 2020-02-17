package com.java.website.myblog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @GetMapping("/blog/edit")
    public String edit(HttpServletRequest request){
        request.setAttribute("path","edit");
        return "admin/edit";
    }
}
