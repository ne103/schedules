package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.entity.Comment;
import com.sparta.schedulemanagement.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 댓글 등록
    Comment save(Comment comment);

    Optional<Comment> findById(Long id);
}
