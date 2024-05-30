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
        // 댓글이 등록된 일정 조회
        Long scdId = dto.getScd_id();
        Schedule schedule = scheduleRepository.findById(scdId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        // 엔티티로 변환 후
        Comment comment = CommentRequestDto.DtoToEntity(dto, schedule);

        // 댓글 리스트에 추가해주면 DB에 저장된다.
        schedule.addComment(comment);

        // 엔티티 반환 -> 해당 메서드가 종료되면 insert 쿼리 수행 및 comment_id 생성
        return comment;
    }

    // 댓글 수정
    @Override
    @Transactional
    public Comment update(Long id, CommentRequestDto dto) {

        // 수정할 댓글 엔티티 조회
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        // 댓글 내용 수정
        comment.changeComment(dto.getComment());

        return comment;
    }
}
