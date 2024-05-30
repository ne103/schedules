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
public class ScheduleResponseDto {

    private Long scd_id;

    private String title;

    private String content;

    private String manager;

    private LocalDateTime regDate;

    private List<Comment> commentList = new ArrayList<>();

    public static ScheduleResponseDto entityToDto(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .scd_id(schedule.getScd_id())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .manager(schedule.getManager())
                .regDate(schedule.getRegDate())
                .commentList(schedule.getCommentList() != null ? schedule.getCommentList() : new ArrayList<>())
                .build();
    }
}
