package com.example.finalprojectsujin221220.controller;


import com.example.finalprojectsujin221220.domain.Response;
import com.example.finalprojectsujin221220.dto.AlarmListResponse;
import com.example.finalprojectsujin221220.service.AlarmService;
import com.example.finalprojectsujin221220.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class LikeAlarmController {

    private final LikeService ls;
    private final AlarmService as;

    //좋아요 누르기
    @PostMapping("/posts/{postId}/likes")
    public Response pushLike(@PathVariable Long postId, Authentication authentication) {
        return Response.success(ls.pushLike(postId, authentication));
    }

    //좋아요 갯수 조회
    @GetMapping("/posts/{postId}/likes")
    public Response countLike(@PathVariable Long postId) {
        return Response.success(ls.countLike(postId));
    }

    //알람기능
    @GetMapping("/alarms")
    public Response<List<AlarmListResponse>> showAlarms(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, Authentication authentication) {
        return Response.success(as.showAlarms(pageable, authentication));
    }


}
