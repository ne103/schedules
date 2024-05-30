package com.sparta.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    public void addComment(Comment comment) {
        commentList.add(comment);
    }
}
