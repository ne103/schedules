package com.sparta.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@Table(name = "Schedule")
public class Schedule extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scd_id;

    private String title;

    private String content;

    private String manager;

    private String pw;
}
