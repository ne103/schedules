package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.dto.CommentResponseDto;
import com.sparta.schedulemanagement.entity.Comment;
import com.sparta.schedulemanagement.service.CommentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    private final CommentServiceImpl commentService;

    // 댓글 등록
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CommentRequestDto dto, BindingResult br) {
        if (br.getErrorCount() > 0) {
            return ResponseEntity.badRequest().body(br.getFieldError().getDefaultMessage());
        }
        Comment comment = commentService.create(dto);
        CommentResponseDto responseDto = CommentResponseDto.entityToDto(comment);
        return ResponseEntity.ok(responseDto);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CommentRequestDto dto, BindingResult br) {
        if (br.getErrorCount() > 0) {
            return ResponseEntity.badRequest().body(br.getFieldError().getDefaultMessage());
        }
        Comment comment = commentService.update(id, dto);
        CommentResponseDto responseDto = CommentResponseDto.entityToDto(comment);
        return ResponseEntity.ok(responseDto);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody CommentRequestDto dto) {
        commentService.delete(id, dto);
        return ResponseEntity.ok(id + "번 댓글 삭제 완료");
    }
}
