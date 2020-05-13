package com.codeup.springblogapp.controllers;

import com.codeup.springblogapp.models.Post;
import com.codeup.springblogapp.repositories.PostRepository;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    // Dependency Injection
    private PostRepository postRepository;

    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }


    @GetMapping("/posts/index")
    public String posts(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postById(@PathVariable long id, Model model) {
//        Post post = new Post("Post 3","This is the body of post 3.");
        model.addAttribute("post",postRepository.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPost() {
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String submitCreatePost(@RequestParam(name = "title") String titleParam, @RequestParam(name = "body") String bodyParam, Model model) {
        Post post = new Post();
        post.setTitle(titleParam);
        post.setBody(bodyParam);
        this.postRepository.save(post);
        model.addAttribute("posts", postRepository.findAll());
        return "/posts/index";
    }
}
