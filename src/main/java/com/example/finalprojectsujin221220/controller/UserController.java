package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.domain.Response;
import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.dto.UserDto;
import com.example.finalprojectsujin221220.dto.UserJoinRequest;
import com.example.finalprojectsujin221220.dto.UserJoinResponse;
import com.example.finalprojectsujin221220.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService us;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest dto) {
        UserDto ud = us.join(dto);
        return Response.success(new UserJoinResponse(ud.getUserName()));
    }


}
