package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.domain.Response;
import com.example.finalprojectsujin221220.dto.*;
import com.example.finalprojectsujin221220.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService us;

    //회원가입 기능
    @PostMapping("/join")
    @ResponseBody
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest dto) {
        return Response.success(us.join(dto));
    }

    //로그인 기능
    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest dto) {
        String token =  us.login(dto.getUserName(), dto.getPassword());
        return Response.success(new UserLoginResponse(token));
    }


}
