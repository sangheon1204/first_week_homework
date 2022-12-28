package com.sparta.homework.entity;

import com.sparta.homework.controller.UpdatePostDto;
import com.sparta.homework.dto.PostRequestDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String password;

    public Post(PostRequestDto postRequestDto) {            //내용 생성
        this.title = postRequestDto.getTitle();
        this.name = postRequestDto.getName();
        this.contents = postRequestDto.getContents();
        this.password = postRequestDto.getPassword();

    }

    public void update(PostRequestDto requestDto) {         //내용 수정
        this.name = requestDto.getName();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
