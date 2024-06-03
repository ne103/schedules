package com.sparta.schedulemanagement.controller;


import com.sparta.schedulemanagement.dto.AuthRequestDto;
import com.sparta.schedulemanagement.dto.AuthResponseDto;
import com.sparta.schedulemanagement.dto.RegisterRequestDto;
import com.sparta.schedulemanagement.dto.RegisterResponseDto;
import com.sparta.schedulemanagement.entity.User;
import com.sparta.schedulemanagement.service.UserService;
import com.sparta.schedulemanagement.util.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/authenticate")
    public String createToken(@RequestBody AuthRequestDto authRequestDto) throws Exception {
        if ("user".equals(authRequestDto.getUsername()) && "password".equals(authRequestDto.getPassword())) {
            return JwtTokenProvider.generateToken(authRequestDto.getUsername());
        } else {
            throw new Exception("유효하지 않은 자격증명");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestHeader("Authorization") String token) {
        String username = JwtTokenProvider.extractUsername(token.substring(7));
        if (JwtTokenProvider.validateToken(token.substring(7), username)) {
            return "유효한 토큰";
        } else {
            return "유효하지 않은 토큰";
        }
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        try {
            userService.register(registerRequestDto);
            RegisterResponseDto registerResponseDto = new RegisterResponseDto("유저 등록 성공");
            return new ResponseEntity<>(registerResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RegisterResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        try {
            User user = userService.login(authRequestDto.getUsername(), authRequestDto.getPassword());
            String token = JwtTokenProvider.generateToken(user.getUsername());
            AuthResponseDto authResponseDto = new AuthResponseDto("로그인 성공", token);
            return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthResponseDto("유효하지 않은 사용자 이름 혹은 비밀번호", null), HttpStatus.UNAUTHORIZED);
        }
    }

}