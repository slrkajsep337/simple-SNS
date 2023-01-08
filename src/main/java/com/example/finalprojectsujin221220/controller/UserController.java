package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.domain.Response;
import com.example.finalprojectsujin221220.dto.UserJoinRequest;
import com.example.finalprojectsujin221220.dto.UserJoinResponse;
import com.example.finalprojectsujin221220.dto.UserLoginRequest;
import com.example.finalprojectsujin221220.dto.UserLoginResponse;
import com.example.finalprojectsujin221220.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"User Api"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService us;

    //회원가입 기능
    @Operation(summary = "회원가입", description = "id와 password 입력으로 회원가입을 할 수 있습니다.")
    @PostMapping("/join")
    @ResponseBody
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest dto) {
        return Response.success(us.join(dto));
    }

    //로그인 기능
    @Operation(summary = "로그인", description = "id와 password 입력으로 로그인하여 토큰을 발급받을 수 있습니다.")
    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest dto) {
        String token =  us.login(dto.getUserName(), dto.getPassword());
        return Response.success(new UserLoginResponse(token));
    }


}
