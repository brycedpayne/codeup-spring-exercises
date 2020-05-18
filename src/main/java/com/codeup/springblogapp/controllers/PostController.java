package com.codeup.springblogapp.controllers;

import com.codeup.springblogapp.models.Post;
import com.codeup.springblogapp.models.User;
import com.codeup.springblogapp.repositories.ImageRepository;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import com.codeup.springblogapp.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    // Dependency Injection

    private PostRepository postRepository;
    private UserRepository userRepository;
    private ImageRepository imageRepository;
    private EmailService emailService;

    public PostController(PostRepository postRepository, UserRepository userRepository, ImageRepository imageRepository, EmailService emailService){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.emailService = emailService;
    }





    //mapping for displaying all the posts

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "posts/index";
    }

//    mapping for displaying a single post

    @GetMapping("/posts/{id}")
    public String postById(@ModelAttribute Post post, Model model) {
//        Post post = new Post("Post 3","This is the body of post 3.");
        model.addAttribute("post",postRepository.getOne(post.getId()));
//        model.addAttribute("user", post.getUser());
        return "posts/show";
    }

    //mapping for creating a post

    @GetMapping("/posts/create")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

//    @PostMapping("/posts/create")
//    public String submitCreatePost(@RequestParam(name = "title") String titleParam, @RequestParam(name = "body") String bodyParam, Model model) {
//        User user = userRepository.getOne((long) 1); //will be removed later
//        Post post = new Post();
//        post.setTitle(titleParam);
//        post.setBody(bodyParam);
//        post.setUser(user);
////        post.setImages(imageRepository.findAll());
//        post = this.postRepository.save(post);
//        model.addAttribute("post", post);
//        return "/posts/show";
//    }

    @PostMapping("/posts/create")
    public String submitCreatePost(@ModelAttribute Post post, Model model) {
        User user = userRepository.getOne((long) 1); //will be removed later
        post.setUser(user);
//        post.setImages(imageRepository.findAll());
        post = this.postRepository.save(post);
        emailService.prepareAndSend(post,"You created a new post",
                "Title: " + post.getTitle() + "\n" + "Body: " + post.getBody());
        model.addAttribute("post", post);
        return "/posts/show";
    }

    //Mapping for updating a post

    @GetMapping("/posts/{id}/edit")
    public String submitPostUpdate(@PathVariable(name = "id") long id, Model model) {
        Post post = postRepository.getOne(id);
        model.addAttribute("post", post);
        return "/posts/update";
    }

    @PostMapping("/posts/{id}/edit")
    public String submitUpdatedPost(@ModelAttribute Post post, Model model) {
//        PathVariable(name = "id")long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body
//        Post post = postRepository.getOne(id);
//        post.setTitle(title);
//        post.setBody(body);
        postRepository.save(post);
        model.addAttribute("post", post);
//        model.addAttribute("user", post.getUser());
        return "redirect:/posts/" + post.getId();
    }

    //mapping for deleting a post

    @PostMapping("posts/delete/{id}")
    public String deletePost(@PathVariable long id){
        Post post = postRepository.getOne(id);
        postRepository.delete(post);
        return "redirect:/posts";
    }
}
