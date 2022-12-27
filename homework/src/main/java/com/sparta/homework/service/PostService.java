package com.sparta.homework.service;

import com.sparta.homework.dto.PostRequestDto;
import com.sparta.homework.entity.Post;
import com.sparta.homework.mappinginterface.MappingInterface;
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


    @Transactional
    public Post createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return post;
    }

    public List<MappingInterface> getPosts() {
        return  postRepository.findAllByOrderByModifiedAtDesc();
    }

    public List<MappingInterface> getPostsBytitle(String title) {
        return postRepository.findAllByTitle(title);
    }

    public List<MappingInterface> getPostsByname(String name) {
        return postRepository.findAllByName(name);
    }
    public Post update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존해하지 않습니다.")
        );
        if(post.getPassword().equals(requestDto.getPassword())) {
            post.update(requestDto);
        }
        return post;
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
