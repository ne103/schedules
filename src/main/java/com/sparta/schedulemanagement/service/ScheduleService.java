package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Comment;

import java.util.List;

public interface ScheduleService {
    public ScheduleResponseDto getSchedule(Long id);

    public ScheduleResponseDto create(ScheduleRequestDto dto);

    public List<ScheduleResponseDto> findAll();

    public ScheduleResponseDto update(Long id, ScheduleRequestDto requestDto) throws Exception;

    public void delete(Long id, String pw) throws Exception;
}
