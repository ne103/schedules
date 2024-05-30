package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 선택한 일정 조회
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        ScheduleResponseDto responseDto = ScheduleResponseDto.entityToDto(schedule);

        return responseDto;
    }

    // 일정 등록
    public ScheduleResponseDto create(ScheduleRequestDto dto) {
        Schedule entity = ScheduleRequestDto.DtoToEntity(dto);

        Schedule schedule = scheduleRepository.save(entity);

        return ScheduleResponseDto.entityToDto(schedule);
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDto::entityToDto)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto update(Long id, ScheduleRequestDto requestDto) throws Exception {
        // DB에서 일정 조회
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        // 비밀번호 검증
        if (!schedule.getPw().equals(requestDto.getPw())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        // 입력한 값으로 해당 일정 수정
        schedule = Schedule.builder()
                .scd_id(id)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .manager(requestDto.getManager())
                .pw(schedule.getPw())
                .regDate(schedule.getRegDate())
                .build();

        scheduleRepository.save(schedule);

        return ScheduleResponseDto.entityToDto(schedule);
    }

    public void delete(Long id, String pw) throws Exception {
        // DB에서 일정 조회
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        // 비밀번호 검증
        if (!schedule.getPw().equals(pw)) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.deleteById(id);
    }
}
