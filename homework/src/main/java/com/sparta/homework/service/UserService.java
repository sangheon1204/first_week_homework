package com.sparta.homework.service;

import com.sparta.homework.dto.UserRequestDto;
import com.sparta.homework.dto.SignupResponseDto;
import com.sparta.homework.dto.LoginResponseDto;
import com.sparta.homework.entity.User;
import com.sparta.homework.jwtutil.JwtUtil;
import com.sparta.homework.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@Getter
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public SignupResponseDto signup(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();
        Optional<User> found = userRepository.findByUsername(username);
        //아이디 중복 확인
        if(found.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디입니다.");
        }
        User user = new User(username, password);
        userRepository.save(user);
        SignupResponseDto signupResponseDto = new SignupResponseDto("회원 가입 성공", 200);
        return signupResponseDto;
    }

    @Transactional(readOnly = true)
    public LoginResponseDto login(UserRequestDto userRequestDto, HttpServletResponse response) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        //사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
        LoginResponseDto loginResponseDto = new LoginResponseDto("로그인 성공",200);
        return loginResponseDto;
    }
}
