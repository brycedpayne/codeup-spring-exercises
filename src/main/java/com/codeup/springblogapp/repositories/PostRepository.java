package com.codeup.springblogapp.repositories;

import com.codeup.springblogapp.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
