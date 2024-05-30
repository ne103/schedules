package com.sparta.schedulemanagement.dto;

import com.sparta.schedulemanagement.entity.Comment;
import com.sparta.schedulemanagement.entity.Schedule;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommentRequestDto {

    @NotNull(message = "댓글을 입력해 주세요.")
    private String comment;

    @NotNull(message = "선택한 일정의 ID를 입력해 주세요.")
    private Long scd_id;

    public static Comment DtoToEntity(CommentRequestDto dto, Schedule schedule) {
        return Comment.builder()
                .comment(dto.getComment())
                .schedule(schedule)
                .build();
    }
}
