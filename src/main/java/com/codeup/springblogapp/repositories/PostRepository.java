package com.codeup.springblogapp.repositories;

import com.codeup.springblogapp.models.Post;
import com.codeup.springblogapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findByBodyContainingOrTitleContaining(String body, String title);
    public List<Post> findByUser(User user);
}
