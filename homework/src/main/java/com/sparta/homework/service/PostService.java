package com.sparta.homework.service;

import com.sparta.homework.dto.*;
import com.sparta.homework.entity.Post;
import com.sparta.homework.entity.User;
import com.sparta.homework.jwtutil.JwtUtil;
import com.sparta.homework.repository.PostRepository;
import com.sparta.homework.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    //게시글 작성
    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            //token 검증
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Post post = new Post(requestDto, user.getUsername(), user.getPassword());
            postRepository.save(post);
            System.out.println(post.getId());
            PostResponseDto postResponseDto = new PostResponseDto(post);
            return postResponseDto;
        } else {
            return null;
        }
    }

    //전체 목록 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> getPostDto = new ArrayList<>();
        for (Post post : postList) {
            getPostDto.add(new PostResponseDto(post));
        }
        return getPostDto;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostsByTitle(String title) {
        List<Post> titlePostList = postRepository.findAllByTitle(title);
        List<PostResponseDto> getTitlePostDto = new ArrayList<>();
        for (Post post : titlePostList) {
            getTitlePostDto.add(new PostResponseDto(post));
        }
        return getTitlePostDto;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostsByName(String username) {
        List<Post> namePostList = postRepository.findAllByUsername(username);
        List<PostResponseDto> getNamePostDto = new ArrayList<>();
        for (Post post : namePostList) {
            getNamePostDto.add(new PostResponseDto(post));
        }
        return getNamePostDto;
    }

    @Transactional
    public PostResponseDto getPostsById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        //token 검증
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Post post = postRepository.findByIdAndUsername(id, user.getUsername()).orElseThrow(
                    () -> new NullPointerException("해당 게시글은 존재하지 않습니다.")
            );
            post.update(requestDto);
            PostResponseDto postResponseDto = new PostResponseDto(post);
            return postResponseDto;
        } else {
            return null;
        }
    }

    @Transactional
    public DeleteDto delete(Long id,HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        //token 검증
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Post post = postRepository.findByIdAndUsername(id, user.getUsername()).orElseThrow(
                    ()-> new NullPointerException("해당 게시글은 존재하지 않습니다.")
            );
            postRepository.delete(post);
            DeleteDto deleteDto = new DeleteDto();
            deleteDto.setMsg("게시글 삭제 성공");
            deleteDto.setStatusCode(200);
            return deleteDto;
        } else {
            return null;
        }
    }
}
