package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ScheduleRequestDTO;
import com.sparta.schedulemanagement.dto.ScheduleResponseDTO;
import com.sparta.schedulemanagement.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{id}")
    public ResponseEntity getSchedule(@PathVariable Long id) {
        ScheduleResponseDTO responseDTO = new ScheduleResponseDTO();
        try {
            responseDTO = scheduleService.getSchedule(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());  // 해당하는 id의 일정이 없을 경우 반환
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    public List<ScheduleResponseDTO> getSchedule() {
        return scheduleService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody @Valid ScheduleRequestDTO dto) {
        ScheduleResponseDTO responseDTO = scheduleService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ScheduleRequestDTO requestDTO) {
        ScheduleResponseDTO responseDTO = new ScheduleResponseDTO();
        try {
             responseDTO = scheduleService.update(id, requestDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }

}
