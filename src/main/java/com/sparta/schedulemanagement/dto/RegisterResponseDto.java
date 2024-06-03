package com.sparta.schedulemanagement.dto;

import lombok.Getter;

@Getter
public class RegisterResponseDto {
    private String message;

    public RegisterResponseDto(String message) {
        this.message = message;
    }
}