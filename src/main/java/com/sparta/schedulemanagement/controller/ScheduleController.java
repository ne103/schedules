package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{id}")
    public ResponseEntity getSchedule(@PathVariable Long id) {
        ScheduleResponseDto responseDto = new ScheduleResponseDto();
        try {
            responseDto = scheduleService.getSchedule(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());  // 해당하는 id의 일정이 없을 경우 반환
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping
    public List<ScheduleResponseDto> getSchedule() {
        return scheduleService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody @Valid ScheduleRequestDto dto) {
        ScheduleResponseDto responseDto = scheduleService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto responseDto = new ScheduleResponseDto();
        try {
             responseDto = scheduleService.update(id, requestDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        try {
            scheduleService.delete(id, requestDto.getPw());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(id + "번 일정 삭제 완료");
    }

}
