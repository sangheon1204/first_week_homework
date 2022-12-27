package com.sparta.homework.repository;

import com.sparta.homework.entity.Post;
import com.sparta.homework.mappinginterface.MappingInterface;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<MappingInterface> findAllByOrderByModifiedAtDesc();

    List<MappingInterface> findAllByTitle(String title);

    List<MappingInterface> findAllByName(String name);
}
