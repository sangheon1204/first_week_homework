package com.sparta.homework.dto;

import com.sparta.homework.entity.Post;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private String title;
    private String name;
    private String contents;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        title = post.getTitle();
        name = post.getName();
        contents = post.getContents();
        password = post.getPassword();
        createdAt = post.getCreatedAt();
        modifiedAt = post.getModifiedAt();
    }
}

