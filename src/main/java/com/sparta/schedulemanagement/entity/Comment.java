package com.sparta.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@Table(name = "Comment")
public class Comment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "scd_id", nullable = false)
    private Schedule schedule;

    // 내용 수정하는 메서드(Setter와 동일한 기능)
    public void changeComment(String comment) {
        this.comment = comment;
    }

}
