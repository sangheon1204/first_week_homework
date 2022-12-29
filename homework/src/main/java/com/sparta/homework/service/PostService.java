package com.sparta.homework.service;

import com.sparta.homework.dto.*;
import com.sparta.homework.entity.Post;
import com.sparta.homework.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public PostResponseDto createPost(PostRequestDto requestDto) {     //게시글 작성
        Post post = new Post(requestDto);
        postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    public List<PostResponseDto> getPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> getPostDto = new ArrayList<>();
        for(Post post : postList) {
            getPostDto.add(new PostResponseDto(post));
        }
        return getPostDto;
    }

    public List<PostResponseDto> getPostsByTitle(String title) {
       List<Post> titlePostList = postRepository.findAllByTitle(title);
       List<PostResponseDto> getTitlePostDto = new ArrayList<>();
       for(Post post: titlePostList) {
           getTitlePostDto.add(new PostResponseDto(post));
       }
       return getTitlePostDto;
    }

    public List<PostResponseDto> getPostsByName(String name) {
        List<Post> namePostList = postRepository.findAllByName(name);
        List<PostResponseDto> getNamePostDto = new ArrayList<>();
        for(Post post: namePostList) {
            getNamePostDto.add(new PostResponseDto(post));
        }
        return getNamePostDto;
    }
    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto) {
        PostResponseDto updatePostDto = null;
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존해하지 않습니다.")
        );
        if(post.getPassword().equals(requestDto.getPassword())) {
            post.update(requestDto);
           updatePostDto = new PostResponseDto(post);
        }
        return updatePostDto;
    }

    public DeleteDto delete(Long id, PostRequestDto requestDto) {
        DeleteDto delete = new DeleteDto();
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(post.getPassword().equals(requestDto.getPassword())) {
            postRepository.deleteById(id);
            delete.setSuccess(true);
        }
        return delete;
    }
}
