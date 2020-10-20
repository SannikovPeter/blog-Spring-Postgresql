package com.example.blog.controllers;

import com.example.blog.bredcrumb.BreadCrumb;
import com.example.blog.models.Post;
import com.example.blog.models.TitleFactory;
import com.example.blog.repo.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/main")
public class BlogController {

    private final PostRepository postRepository;
    private final BreadCrumb breadCrumb;
    private final TitleFactory titleFactory;

    public BlogController(PostRepository postRepository, BreadCrumb breadCrumb, TitleFactory titleFactory) {
        this.postRepository = postRepository;
        this.breadCrumb = breadCrumb;
        this.titleFactory = titleFactory;
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("crumbs", breadCrumb.getCrumbList());
        model.addAttribute("title", titleFactory.getTitle());
        return "about";
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("crumbs", breadCrumb.getCrumbList());

        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("title", titleFactory.getTitle());
        return "blog";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        model.addAttribute("crumbs", breadCrumb.getCrumbList());
        model.addAttribute("title", titleFactory.getTitle());
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text, Model model) {
        Post post = new Post(title, anons, full_text);
        postRepository.save(post);
        model.addAttribute("title", titleFactory.getTitle());
        return "redirect:/main/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/main/blog";
        }
        model.addAttribute("crumbs", breadCrumb.getCrumbList());
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        model.addAttribute("title", titleFactory.getTitle());
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/main/blog";
        }
        model.addAttribute("crumbs", breadCrumb.getCrumbList());
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        model.addAttribute("title", titleFactory.getTitle());
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id,
                                 @RequestParam String title,
                                 @RequestParam String anons,
                                 @RequestParam String full_text, Model model) {
        Post post = postRepository.findById(id).orElseThrow(RuntimeException::new);
        post.setTitle(title);
        post.setAnons(anons);
        post.setFullText(full_text);
        postRepository.save(post);
        return "redirect:/main/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow(RuntimeException::new);
        postRepository.delete(post);
        return "redirect:/main/blog";
    }
}
