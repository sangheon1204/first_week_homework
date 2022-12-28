package com.sparta.homework.controller;

import com.sparta.homework.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdatePostDto {
    private String name;
    private String contents;
    private String title;
    private LocalDateTime modifiedAt;

    @Builder
    public UpdatePostDto(Post post) {
        this.name = post.getName();
        this.contents = post.getContents();
        this.title = post.getTitle();
        this.modifiedAt = post.getModifiedAt();
    }
}

