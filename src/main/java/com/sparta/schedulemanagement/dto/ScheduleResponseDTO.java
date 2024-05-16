package com.sparta.schedulemanagement.dto;

import com.sparta.schedulemanagement.entity.Schedule;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ScheduleResponseDTO {

    private Long scd_id;

    private String title;

    private String content;

    private String manager;

    private LocalDateTime regDate;

    public static ScheduleResponseDTO entityToDto(Schedule schedule) {
        return ScheduleResponseDTO.builder()
                .scd_id(schedule.getScd_id())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .manager(schedule.getManager())
                .regDate(schedule.getRegDate())
                .build();
    }


}
