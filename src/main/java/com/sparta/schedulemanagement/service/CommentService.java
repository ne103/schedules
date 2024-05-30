package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.entity.Comment;

public interface CommentService {

    public Comment create(CommentRequestDto dto);

    public Comment update(Long id, CommentRequestDto dto);

    public void delete(Long id, CommentRequestDto dto);
}
