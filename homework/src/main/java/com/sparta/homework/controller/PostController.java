package com.sparta.homework.controller;

import com.sparta.homework.dto.*;
import com.sparta.homework.entity.Post;
import com.sparta.homework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    // 게시물 작성하기
    @PostMapping ("/posts")
    public PostResponseDto creatPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.createPost(requestDto, request);
    }

    @GetMapping ("/posts")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

    @GetMapping ("/posts/title/{title}")  // 제목으로 조회
    public List<PostResponseDto> getPostsByTitle(@PathVariable String title) {
        return postService.getPostsByTitle(title);
    }

    @GetMapping("/posts/name/{name}")  //이름으로 조회
    public List<PostResponseDto> getPostsByName(@PathVariable String name) {
        return postService.getPostsByName(name);
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto getPostsById(@PathVariable Long id) {
        return postService.getPostsById(id);
    }
    @PutMapping("/posts/{id}") //id값을 받아서 수정 -> 입력한 비밀번호 확인 후 수정
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
       return postService.update(id,requestDto,request);
    }

    @DeleteMapping("/posts/{id}")
    public DeleteDto deletePost(@PathVariable Long id,HttpServletRequest request) {
        return postService.delete(id,request);
    }
}
