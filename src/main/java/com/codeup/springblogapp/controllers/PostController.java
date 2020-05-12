package com.codeup.springblogapp.controllers;

import com.codeup.springblogapp.models.Post;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.model.IModel;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts/index")
    public String posts(Model model) {
        List<Post> posts = new ArrayList<>();
        Post post = new Post();
        post.setId(1);
        post.setTitle("Post 1");
        post.setBody("This is the body of post 1.");
        posts.add(post);
        post = new Post();
        post.setId(2);
        post.setTitle("Post 2");
        post.setBody("This is the body of post 2.");
        posts.add(post);

        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postById(@PathVariable String id, Model model) {
        Post post = new Post("Post 3","This is the body of post 3.");
        model.addAttribute("post",post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "This method will display the page to create a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String submitCreatePost() {
        return "This method will save the new post.";
    }
}
