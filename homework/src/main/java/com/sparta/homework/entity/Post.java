package com.sparta.homework.entity;

import com.sparta.homework.dto.PostRequestDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;


    public Post(PostRequestDto requestDto, String username, String password) {
        this.title = requestDto.getTitle();
        this.username = username;
        this.content = requestDto.getContent();
        this.password = password;
    }

    public void update(PostRequestDto requestDto) {//내용 수정
        if(requestDto.getTitle() != null) {
            this.title = requestDto.getTitle();
        }
        if(requestDto.getContent() != null) {
            this.content = requestDto.getContent();
        }

    }
}
