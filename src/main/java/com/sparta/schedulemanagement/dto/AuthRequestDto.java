package com.sparta.schedulemanagement.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AuthRequestDto {
    private String username;
    private String password;
}
