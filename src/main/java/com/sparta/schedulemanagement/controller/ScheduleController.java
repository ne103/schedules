package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ScheduleRequestDTO;
import com.sparta.schedulemanagement.dto.ScheduleResponseDTO;
import com.sparta.schedulemanagement.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDTO> getSchedule(@PathVariable Long id) {
        ScheduleResponseDTO responseDTO = scheduleService.getSchedule(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ScheduleResponseDTO> create(@RequestBody @Valid ScheduleRequestDTO dto) {
        ScheduleResponseDTO responseDTO = scheduleService.create(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
