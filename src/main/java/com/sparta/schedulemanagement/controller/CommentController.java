package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.dto.CommentResponseDto;
import com.sparta.schedulemanagement.entity.Comment;
import com.sparta.schedulemanagement.service.CommentService;
import com.sparta.schedulemanagement.service.ScheduleServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    private final ScheduleServiceImpl scheduleService;


    // 댓글 등록
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody @Valid CommentRequestDto dto, BindingResult br) {
        CommentResponseDto responseDto;
        if (br.getErrorCount() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Objects.requireNonNull(br.getFieldError()).getDefaultMessage()); // 내용이 비어있을 경우
        }
        try {
            Comment comment = commentService.create(dto);
            // Dto로 변환(comment_id 존재)
            responseDto = CommentResponseDto.entityToDto(comment);

            ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // 일정이 DB에 저장되지 않은 경우
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody CommentRequestDto dto, BindingResult br) {
        CommentResponseDto responseDto;
        if (br.getErrorCount() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Objects.requireNonNull(br.getFieldError()).getDefaultMessage()); // 내용이 비어있을 경우
        }
        try {
            Comment comment = commentService.update(id, dto);

            responseDto = CommentResponseDto.entityToDto(comment);

            ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // 일정이 DB에 저장되지 않은 경우
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id, @RequestBody CommentRequestDto dto) {
        try {
            commentService.delete(id, dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // 일정이 DB에 저장되지 않은 경우
        }

        return ResponseEntity.status(HttpStatus.OK).body(id + "번 댓글 삭제 완료");
    }
}
