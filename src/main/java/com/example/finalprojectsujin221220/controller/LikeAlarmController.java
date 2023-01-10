package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.domain.Response;
import com.example.finalprojectsujin221220.dto.AlarmListResponse;
import com.example.finalprojectsujin221220.dto.AlarmResponse;
import com.example.finalprojectsujin221220.service.AlarmService;
import com.example.finalprojectsujin221220.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Like and Alarm Api"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class LikeAlarmController {

    private final LikeService ls;
    private final AlarmService as;

    //좋아요 누르기
    @Operation(summary = "like/like 취소", description = "인증된 user만 like와 like 취소를 할 수 있습니다.")
    @PostMapping("/posts/{postId}/likes")
    public Response pushLike(@PathVariable Long postId, Authentication authentication) {
        return Response.success(ls.pushLike(postId, authentication));
    }

    //좋아요 갯수 조회
    @Operation(summary = "like 갯수 조회", description = "인증 없이도 특정 post의 like 갯수를 조회할 수 있습니다.")
    @GetMapping("/posts/{postId}/likes")
    public Response countLike(@PathVariable Long postId) {
        return Response.success(ls.countLike(postId));
    }

    //알람기능
    @Operation(summary = "alarm 조회", description = "인증된 user만 자신의 알람(like/comment)을 조회할 수 있습니다.")
    @GetMapping("/alarms")
    public Response<AlarmResponse> showAlarms(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, Authentication authentication) {
        List<AlarmListResponse> response = as.showAlarms(pageable, authentication);
        return Response.success(new AlarmResponse(response));
    }


}
