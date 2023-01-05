package com.sparta.homework.dto;

import com.sparta.homework.entity.Post;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        id = post.getId();
        title = post.getTitle();
        content = post.getContent();
        username = post.getUsername();
        createdAt = post.getCreatedAt();
        modifiedAt = post.getModifiedAt();
    }
}

