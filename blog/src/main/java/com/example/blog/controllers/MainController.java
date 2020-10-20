package com.example.blog.controllers;

import com.example.blog.bredcrumb.BreadCrumb;
import com.example.blog.bredcrumb.Crumb;
import com.example.blog.models.TitleFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final TitleFactory titleFactory;
    private final BreadCrumb breadCrumb;

    public MainController(TitleFactory titleFactory, BreadCrumb breadCrumb) {
        this.titleFactory = titleFactory;
        this.breadCrumb = breadCrumb;
    }

    @GetMapping("/")
    public String insert(Model model) {
        model.addAttribute("title", "Вход");
        return "insert";
    }

    @GetMapping("/main")
    public String greeting(Model model) {
        Crumb[] crumbs = breadCrumb.getCrumbList();
        model.addAttribute("crumbs", crumbs);
        model.addAttribute("title", titleFactory.getTitle());
        return "main";
    }

}
