package com.example.finalprojectsujin221220.service;

import com.example.finalprojectsujin221220.domain.entity.Comment;
import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.dto.*;
import com.example.finalprojectsujin221220.exception.ApplicationException;
import com.example.finalprojectsujin221220.exception.ErrorCode;
import com.example.finalprojectsujin221220.repository.CommentRepository;
import com.example.finalprojectsujin221220.repository.PostRepository;
import com.example.finalprojectsujin221220.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserRepository ur;
    private final PostRepository pr;
    private final CommentRepository cr;
    private final AlarmService as;


    //코멘트 등록
    public CommentCreateResponse newComment(Long postId, CommentCreateRequest dto, Authentication authentication) {

        User user = ur.findByUserName(authentication.getName())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USERNAME_NOT_FOUND, ErrorCode.USERNAME_NOT_FOUND.getMessage()));
        Post post = pr.findById(postId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.POST_NOT_FOUND, ErrorCode.DUPLICATED_USER_NAME.getMessage()));

        Comment comment = Comment.builder()
                .comment(dto.getComment())
                .createdAt(LocalDateTime.now())
                .lastModifiedAt(LocalDateTime.now())
                .user(user)
                .post(post)
                .build();

        Comment savedComment = cr.save(comment);

        as.newAlarm(post.getUser(),user.getUserId(), postId, comment.getCreatedAt(), "NEW_COMMENT_ON_POST", "new comment!");

        return CommentCreateResponse.builder()
                .id(savedComment.getCommentId())
                .comment(savedComment.getComment())
                .userName(authentication.getName())
                .postId(postId)
                .createdAt(savedComment.getCreatedAt())
                .build();

    }

    //코멘트 수정
    public CommentModifyResponse modifyComment(Long postId, Long id, CommentModifyRequest dto, Authentication authentication) {
        User user = ur.findByUserName(authentication.getName())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USERNAME_NOT_FOUND, ErrorCode.USERNAME_NOT_FOUND.getMessage()));
        Post post = pr.findById(postId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.POST_NOT_FOUND, ErrorCode.DUPLICATED_USER_NAME.getMessage()));
        Comment comment = cr.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.COMMENT_NOT_FOUND, ErrorCode.COMMENT_NOT_FOUND.getMessage()));
        if( !post.getUser().equals(user)) throw new ApplicationException(ErrorCode.INVALID_PERMISSION, ErrorCode.INVALID_PERMISSION.getMessage());

        comment.setComment(dto.getComment());
        comment.setLastModifiedAt(LocalDateTime.now());
        Comment savedComment = cr.save(comment);

        return CommentModifyResponse.builder()
                .id(savedComment.getCommentId())
                .comment(savedComment.getComment())
                .userName(authentication.getName())
                .postId(postId)
                .createdAt(savedComment.getCreatedAt())
                .lastModifiedAt(savedComment.getLastModifiedAt())
                .build();

    }

    //코멘트 삭제
    public CommentDeleteResponse deleteComment(Long postId, Long id, Authentication authentication) {
        User user = ur.findByUserName(authentication.getName())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USERNAME_NOT_FOUND, ErrorCode.USERNAME_NOT_FOUND.getMessage()));
        Post post = pr.findById(postId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.POST_NOT_FOUND, ErrorCode.POST_NOT_FOUND.getMessage()));
        Comment comment = cr.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.COMMENT_NOT_FOUND, ErrorCode.COMMENT_NOT_FOUND.getMessage()));

        if(!comment.getPost().equals(post)) throw new ApplicationException(ErrorCode.INVALID_PERMISSION, ErrorCode.INVALID_PERMISSION.getMessage());
        if(!comment.getUser().equals(user)) throw new ApplicationException(ErrorCode.INVALID_PERMISSION, ErrorCode.INVALID_PERMISSION.getMessage());

        cr.delete(comment);

        return CommentDeleteResponse.builder()
                .message("댓글 삭제 완료")
                .id(id)
                .build();

    }

    //포스트 해당 코멘트 전체 조회
    public Page<CommentListResponse> showComments(Long postId, Pageable pageable) {
        Post post = pr.findById(postId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.POST_NOT_FOUND, ErrorCode.POST_NOT_FOUND.getMessage()));

        return cr.findByPost(post, pageable).map(CommentListResponse::toCommentListResponse);
    }



}
