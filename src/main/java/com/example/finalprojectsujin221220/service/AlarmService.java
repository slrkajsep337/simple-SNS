package com.example.finalprojectsujin221220.service;

import com.example.finalprojectsujin221220.domain.entity.Alarm;
import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.dto.AlarmListResponse;
import com.example.finalprojectsujin221220.exception.ApplicationException;
import com.example.finalprojectsujin221220.exception.ErrorCode;
import com.example.finalprojectsujin221220.repository.AlarmRepository;
import com.example.finalprojectsujin221220.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AlarmService {

    private final UserRepository ur;
    private final AlarmRepository ar;

    //알람 조회
    public List<AlarmListResponse> showAlarms(Pageable pageable, Authentication authentication) {

        User user = ur.findByUserName(authentication.getName())
                .orElseThrow(() -> new ApplicationException((ErrorCode.USERNAME_NOT_FOUND), ErrorCode.USERNAME_NOT_FOUND.getMessage()));

        Page<Alarm> alarmList = ar.findByUser(user, pageable);
        List<AlarmListResponse> response = alarmList.stream()
                .map(list -> Alarm.of(list)).collect(Collectors.toList());
        return response;

    }

    //알람 저장
    public void newAlarm(User user, Long fromId, Long postId, LocalDateTime time, String alarmType, String text) {

        Alarm alarm = Alarm.builder()
                .alarmType(alarmType)
                .fromUserId(fromId)
                .targetId(postId)
                .user(user)
                .text(text)
                .createdAt(time)
                .build();

        ar.save(alarm);

    }

}
