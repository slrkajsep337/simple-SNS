package com.example.finalprojectsujin221220.domain.entity;

import com.example.finalprojectsujin221220.dto.AlarmListResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alarmType;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    private Long fromUserId;
    private Long targetId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String text;

    public static AlarmListResponse of(Alarm alarm) {
        return AlarmListResponse.builder()
                .id(alarm.getId())
                .alarmType(alarm.getAlarmType())
                .fromUserId(alarm.getFromUserId())
                .targetId(alarm.getTargetId())
                .text(alarm.getText())
                .createdAt(alarm.getCreatedAt())
                .build();
    }
}
