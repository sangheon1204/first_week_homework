package com.sparta.homework.repository;
import com.sparta.homework.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByOrderByModifiedAtDesc();

    List<Post> findAllByTitle(String title);

    List<Post> findAllByName(String name);
}
