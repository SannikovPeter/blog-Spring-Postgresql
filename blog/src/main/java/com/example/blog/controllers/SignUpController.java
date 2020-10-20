package com.example.blog.controllers;

import com.example.blog.bredcrumb.BreadCrumb;
import com.example.blog.models.TitleFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/signUp")
public class SignUpController {

    private final TitleFactory titleFactory;
    private final BreadCrumb breadCrumb;

    public SignUpController(TitleFactory titleFactory, BreadCrumb breadCrumb) {
        this.titleFactory = titleFactory;
        this.breadCrumb = breadCrumb;
    }

    @GetMapping()
    public String signUp(Model model) {
        model.addAttribute("crumbs", breadCrumb.getCrumbList());
        model.addAttribute("title", titleFactory.getTitle());
        return "signUp";
    }
}
