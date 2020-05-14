package com.codeup.springblogapp.controllers;

import com.codeup.springblogapp.models.Image;
import com.codeup.springblogapp.models.Post;
import com.codeup.springblogapp.models.User;
import com.codeup.springblogapp.repositories.ImageRepository;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
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
    private UserRepository userRepository;
    private ImageRepository imageRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository, ImageRepository imageRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }


    @GetMapping("/posts")
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
        User user = userRepository.getOne((long) 1); //will be removed later
        Post post = new Post();
        post.setTitle(titleParam);
        post.setBody(bodyParam);
        post.setUser(user);
        post = this.postRepository.save(post);
        model.addAttribute("post", post);
        return "/posts/show";
    }

    @GetMapping("/posts/update/{id}")
    public String submitPostUpdate(@PathVariable(name = "id") long id, Model model) {
        Post post = postRepository.getOne(id);
        model.addAttribute("post", post);
        return "/posts/update";
    }
    @PostMapping("/posts/update/{id}")
    public String submitUpdatedPost(@PathVariable(name = "id")long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body, Model model) {
        Post post = postRepository.getOne(id);
        post.setTitle(title);
        post.setBody(body);
        postRepository.save(post);
        model.addAttribute("post", post);
        return "redirect:/posts/"+id;
    }

    @PostMapping("posts/delete/{id}")
    public String deletePost(@PathVariable long id){
        Post post = postRepository.getOne(id);
        postRepository.delete(post);
        return "redirect:/posts";
    }
}
