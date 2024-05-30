package com.sparta.schedulemanagement.dto;

import com.sparta.schedulemanagement.entity.Comment;
import com.sparta.schedulemanagement.entity.Schedule;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommentResponseDto {

    private Long scd_id;

    private Long comment_id;

    private String comment;

    //    private Long user_id;

    //    private String userName;

    private LocalDateTime regDate;

    public static CommentResponseDto entityToDto(Comment comment) {
        return CommentResponseDto.builder()
                .scd_id(comment.getSchedule().getScd_id())
                .comment_id(comment.getComment_id())
                .comment(comment.getComment())
                .regDate(comment.getRegDate())
                .build();
    }
}
