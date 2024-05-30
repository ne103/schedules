package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.ScheduleRequestDTO;
import com.sparta.schedulemanagement.dto.ScheduleResponseDTO;
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
    public ScheduleResponseDTO getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        ScheduleResponseDTO responseDTO = ScheduleResponseDTO.entityToDto(schedule);

        return responseDTO;
    }

    // 일정 등록
    public ScheduleResponseDTO create(ScheduleRequestDTO dto) {
        Schedule entity = ScheduleRequestDTO.DtoToEntity(dto);

        Schedule schedule = scheduleRepository.save(entity);

        return ScheduleResponseDTO.entityToDto(schedule);
    }

    public List<ScheduleResponseDTO> findAll() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDTO::entityToDto)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDTO update(Long id, ScheduleRequestDTO requestDTO) throws Exception {
        // DB에서 일정 조회
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        // 비밀번호 검증
        if (!schedule.getPw().equals(requestDTO.getPw())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        // 입력한 값으로 해당 일정 수정
        schedule = Schedule.builder()
                .scd_id(id)
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .manager(requestDTO.getManager())
                .pw(schedule.getPw())
                .regDate(schedule.getRegDate())
                .build();

        scheduleRepository.save(schedule);

        return ScheduleResponseDTO.entityToDto(schedule);
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
