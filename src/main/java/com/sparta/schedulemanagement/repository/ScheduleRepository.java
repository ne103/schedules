package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // 선택한 일정 조회
    Optional<Schedule> findById(Long id);

    Schedule save(Schedule schedule);
}