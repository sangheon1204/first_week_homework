package com.sparta.homework.dto;

import com.sparta.homework.entity.Post;
import lombok.Getter;

@Getter
public class GetPostDto {
    private String title;
    private String name;
    private String contents;



    public GetPostDto(Post post) {
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.name = post.getName();
    }
}

