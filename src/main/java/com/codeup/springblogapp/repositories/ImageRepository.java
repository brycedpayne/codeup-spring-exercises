package com.codeup.springblogapp.repositories;

import com.codeup.springblogapp.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
