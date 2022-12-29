package com.sparta.homework.controller;

import com.sparta.homework.dto.*;
import com.sparta.homework.entity.Post;
import com.sparta.homework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping ("/api/posts")
    public PostResponseDto creatPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping ("/api/posts")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

    @GetMapping ("/api/posts/title/{title}")  // 제목으로 조회
    public List<PostResponseDto> getPostsByTitle(@PathVariable String title) {
        return postService.getPostsByTitle(title);
    }

    @GetMapping("/api/posts/name/{name}")  //이름으로 조회
    public List<PostResponseDto> getPostsByName(@PathVariable String name) {
        return postService.getPostsByName(name);
    }


    @PutMapping("/api/posts/{id}") //id값을 받아서 수정 -> 입력한 비밀번호 확인 후 수정
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
       return postService.update(id,requestDto);
    }

    @DeleteMapping("/api/posts/{id}")
    public DeleteDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.delete(id,requestDto);
    }





}
