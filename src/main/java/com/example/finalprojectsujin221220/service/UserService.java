package com.example.finalprojectsujin221220.service;

import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.dto.UserDto;
import com.example.finalprojectsujin221220.dto.UserJoinRequest;
import com.example.finalprojectsujin221220.exception.ApplicationException;
import com.example.finalprojectsujin221220.exception.ErrorCode;
import com.example.finalprojectsujin221220.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository ur;
    private final BCryptPasswordEncoder encoder;


    public UserDto join(UserJoinRequest dto) {

        //userName 중복 -> 예외처리
        ur.findByUserName(dto.getUserName())
                .ifPresent(user -> {
                    throw new ApplicationException(ErrorCode.DUPLICATED_USER_NAME,
                            ErrorCode.DUPLICATED_USER_NAME.getMessage());
                });

        User savedUser = ur.save(dto.toEntity(encoder.encode(dto.getPassword())));

        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .build();
    }

}
