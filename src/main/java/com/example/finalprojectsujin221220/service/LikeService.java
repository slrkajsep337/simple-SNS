package com.example.finalprojectsujin221220.service;


import com.example.finalprojectsujin221220.domain.entity.LikeEntity;
import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.exception.ApplicationException;
import com.example.finalprojectsujin221220.exception.ErrorCode;
import com.example.finalprojectsujin221220.repository.LikeRepository;
import com.example.finalprojectsujin221220.repository.PostRepository;
import com.example.finalprojectsujin221220.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final UserRepository ur;
    private final PostRepository pr;
    private final LikeRepository lr;

    public String pushLike(Long postId, Authentication authentication) {

        User user = ur.findByUserName(authentication.getName())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USERNAME_NOT_FOUND, ErrorCode.USERNAME_NOT_FOUND.getMessage()));
        Post post = pr.findById(postId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.POST_NOT_FOUND, ErrorCode.POST_NOT_FOUND.getMessage()));
        LikeEntity likeEntity = lr.findByUserAndPost(user, post);
        String message;
        if(likeEntity==null) {
            likeEntity = LikeEntity.builder()
                    .createdAt(LocalDateTime.now())
                    .user(user)
                    .post(post)
                    .build();
            message = "좋아요를 눌렀습니다.";
        } else if(likeEntity.getDeletedAt()!=null) {
            likeEntity.setCreatedAt(LocalDateTime.now());
            likeEntity.setDeletedAt(null);
            message = "좋아요를 눌렀습니다.";
        } else {
            likeEntity.setDeletedAt(LocalDateTime.now());
            message = "좋아요를 취소했습니다.";
        }

        lr.save(likeEntity);
        return message;
    }
}
