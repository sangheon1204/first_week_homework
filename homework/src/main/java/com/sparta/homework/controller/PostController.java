package com.sparta.homework.controller;

import com.sparta.homework.dto.PostRequestDto;
import com.sparta.homework.entity.Post;
import com.sparta.homework.mappinginterface.MappingInterface;
import com.sparta.homework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @Transactional //생성 기능
    @PostMapping ("/api/posts")
    public Post creatPost( @RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @Transactional(readOnly = true)     //전체 목록 조회 기능
    @GetMapping ("/api/posts")
    public List<MappingInterface> getPosts(){
        return postService.getPosts();
    }

    @Transactional
    @GetMapping ("/api/posts/title/{title}")  // 제목으로 조회
    public List<MappingInterface> getPostsByTitle(@PathVariable String title) {
        return postService.getPostsBytitle(title);
    }
    @Transactional
    @GetMapping("/api/posts/name/{name}")  //이름으로 조회
    public List<MappingInterface> getPostsByName(@PathVariable String name) {
        return postService.getPostsByname(name);
    }

    @Transactional             //id값을 받아서 수정 -> 입력한 비밀번호 확인 후 수정
    @PutMapping("/api/posts/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
       return postService.update(id,requestDto);
    }

    @Transactional
    @DeleteMapping("/api/posts/{id}")
    public String deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.delete(id,requestDto);
    }





}
