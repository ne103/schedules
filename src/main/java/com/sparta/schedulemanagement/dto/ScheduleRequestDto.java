package com.sparta.schedulemanagement.dto;

import com.sparta.schedulemanagement.entity.Schedule;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ScheduleRequestDto {

    @NotNull(message = "제목을 입력해주세요")
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String manager;

    @NotNull
    private String pw;

    public static Schedule DtoToEntity(ScheduleRequestDto dto) {
        return Schedule.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .manager(dto.getManager())
                .pw(dto.getPw())
                .build();
    }

}
