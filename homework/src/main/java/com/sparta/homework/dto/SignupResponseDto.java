package com.sparta.homework.dto;

import lombok.Getter;

@Getter
public class SignupResponseDto {
    private String msg;
    private Integer statusCode;

    public SignupResponseDto(String msg, Integer statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}

