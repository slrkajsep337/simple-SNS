package com.example.finalprojectsujin221220.service;

import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.dto.UserDto;
import com.example.finalprojectsujin221220.dto.UserJoinRequest;
import com.example.finalprojectsujin221220.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository ur;

    public UserDto join(UserJoinRequest dto) {

        //userName 중복 -> 예외처리
        ur.findByUserName(dto.getUserName())
                .ifPresent(user -> {
                    throw new RuntimeException();
                });

        User savedUser = ur.save(dto.toEntity(dto.getPassword()));

        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .build();
    }

}
