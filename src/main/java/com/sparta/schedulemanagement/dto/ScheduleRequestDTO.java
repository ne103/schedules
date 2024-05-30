package com.sparta.schedulemanagement.dto;

import com.sparta.schedulemanagement.entity.Comment;
import com.sparta.schedulemanagement.entity.Schedule;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ScheduleRequestDTO {

    @NotNull(message = "제목을 입력해주세요")
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String manager;

    @NotNull
    private String pw;

    public static Schedule DtoToEntity(ScheduleRequestDTO dto) {
        return Schedule.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .manager(dto.getManager())
                .pw(dto.getPw())
                .build();
    }

}
