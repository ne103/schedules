package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.entity.Comment;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.repository.CommentRepository;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    // 댓글 등록
    @Override
    @Transactional
    public Comment create(CommentRequestDto dto) {
        Long scdId = dto.getScd_id();
        Schedule schedule = scheduleRepository.findById(scdId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        Comment comment = CommentRequestDto.DtoToEntity(dto, schedule);
        schedule.addComment(comment);
        return comment;
    }

    // 댓글 수정
    @Override
    @Transactional
    public Comment update(Long id, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        comment.changeComment(dto.getComment());
        return comment;
    }

    // 댓글 삭제
    @Override
    @Transactional
    public void delete(Long id, CommentRequestDto dto) {
        Long scdId = dto.getScd_id();
        Schedule schedule = scheduleRepository.findById(scdId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        schedule.removeComment(comment);
    }
}
