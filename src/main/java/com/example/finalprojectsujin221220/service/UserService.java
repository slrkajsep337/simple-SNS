package com.example.finalprojectsujin221220.service;

import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.dto.UserDto;
import com.example.finalprojectsujin221220.dto.UserJoinRequest;
import com.example.finalprojectsujin221220.dto.UserLoginRequest;
import com.example.finalprojectsujin221220.exception.ApplicationException;
import com.example.finalprojectsujin221220.exception.ErrorCode;
import com.example.finalprojectsujin221220.repository.UserRepository;
import com.example.finalprojectsujin221220.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository ur;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private  String secretKey;
    private long expireTime = 1000 * 60 * 60; //1시간


    public UserDto join(UserJoinRequest dto) {

        //userName 중복 -> 예외처리
        ur.findByUserName(dto.getUserName())
                .ifPresent(user -> {
                    throw new ApplicationException(ErrorCode.DUPLICATED_USER_NAME,
                            ErrorCode.DUPLICATED_USER_NAME.getMessage());
                });

        User savedUser = ur.save(dto.toEntity(encoder.encode(dto.getPassword())));

        return UserDto.builder()
                .id(savedUser.getUserId())
                .userName(savedUser.getUserName())
                .build();
    }

    public String login(String userName, String password) {

        //username 존재 확인
        User user = ur.findByUserName(userName)
                .orElseThrow(() -> {
                    throw new ApplicationException(ErrorCode.NOT_FOUND_USER_NAME,
                ErrorCode.NOT_FOUND_USER_NAME.getMessage());
    });

        //password 일치 확인
        if(!encoder.matches(password, user.getPassword())) {
            throw new ApplicationException(ErrorCode.INVALID_PASSWORD,
                    ErrorCode.INVALID_PASSWORD.getMessage());
        }

        //token 발행
        return JwtUtil.createToken(userName, secretKey, expireTime);


    }

}
