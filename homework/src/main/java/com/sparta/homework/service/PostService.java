package com.sparta.homework.service;

import com.sparta.homework.controller.UpdatePostDto;
import com.sparta.homework.dto.GetPostDto;
import com.sparta.homework.dto.PostRequestDto;
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


    public Post createPost(PostRequestDto requestDto) {     //게시글 작성
        Post post = new Post(requestDto);
        postRepository.save(post);
        return post;
    }

    public List<GetPostDto> getPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<GetPostDto> getPostDto = new ArrayList<>();
        for(Post post : postList) {
            getPostDto.add(new GetPostDto(post));
        }
        return getPostDto;
    }

    public List<GetPostDto> getPostsByTitle(String title) {
       List<Post> titlePostList = postRepository.findAllByTitle(title);
       List<GetPostDto> getTitlePostDto = new ArrayList<>();
       for(Post post: titlePostList) {
           getTitlePostDto.add(new GetPostDto(post));
       }
       return getTitlePostDto;
    }

    public List<GetPostDto> getPostsByName(String name) {
        List<Post> namePostList = postRepository.findAllByName(name);
        List<GetPostDto> getNamePostDto = new ArrayList<>();
        for(Post post: namePostList) {
            getNamePostDto.add(new GetPostDto(post));
        }
        return getNamePostDto;
    }
    public UpdatePostDto update(Long id, PostRequestDto requestDto) {
        UpdatePostDto updatePostDto = null;
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존해하지 않습니다.")
        );
        if(post.getPassword().equals(requestDto.getPassword())) {
            post.update(requestDto);
            updatePostDto = new UpdatePostDto(post);
        }
        return updatePostDto;
    }

    public String delete(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(post.getPassword().equals(requestDto.getPassword())) {
            postRepository.deleteById(id);
            return "삭제되었습니다.";
        }else {
            return "입력하신 비밀번호가 다릅니다.";
        }
    }
}
