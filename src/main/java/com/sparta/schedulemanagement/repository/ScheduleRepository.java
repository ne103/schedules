package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    // 전체 일정 조회(등록일 기준 내림차순)
    @Query("select s from Schedule s order by s.regDate desc")
    List<Schedule> findAll();

    // 선택한 일정 조회
    Optional<Schedule> findById(Long id);

    Schedule save(Schedule schedule);

    // 선택한 일정 삭제
    void deleteById(Long id);
}