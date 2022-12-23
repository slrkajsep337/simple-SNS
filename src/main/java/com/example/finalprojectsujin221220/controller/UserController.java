package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.domain.Response;
import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.dto.*;
import com.example.finalprojectsujin221220.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService us;

    @PostMapping("/join")
    @ResponseBody
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest dto) {
        UserDto ud = us.join(dto);
        return Response.success(new UserJoinResponse(ud.getUserName()));
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest dto) {
        String token =  us.login(dto.getUserName(), dto.getPassword());
        return Response.success(new UserLoginResponse(token));
    }


}
