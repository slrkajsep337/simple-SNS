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

@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserRepository ur;
    private final PostRepository pr;
    private final CommentRepository cr;
    private final AlarmService as;


    //[중복 로직] user 존재 확인 + 가져오기
    public User validateUser(Authentication authentication) {
        return ur.findByUserName(authentication.getName())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USERNAME_NOT_FOUND, ErrorCode.USERNAME_NOT_FOUND.getMessage()));
    }
    //[중복 로직] post 존재 확인 + 가져오기
    public Post validatePost(Long id) {
        return pr.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.POST_NOT_FOUND, ErrorCode.POST_NOT_FOUND.getMessage()));
    }
    //[중복 로직] comment 존재 확인 + 가져오기
    public Comment validateComment(Long id) {
        return cr.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.COMMENT_NOT_FOUND, ErrorCode.COMMENT_NOT_FOUND.getMessage()));
    }
    //[중복 로직] 권한 확인
    public void validateAuthority(Object origin, Object compareTo) {
        if( !origin.equals(compareTo)) throw new ApplicationException(ErrorCode.INVALID_PERMISSION, ErrorCode.INVALID_PERMISSION.getMessage());
    }




    //코멘트 등록
    public CommentCreateResponse newComment(Long postId, CommentCreateRequest dto, Authentication authentication) {

        User user = validateUser(authentication);
        Post post = validatePost(postId);

        Comment comment = dto.toEntity(user, post);
        Comment savedComment = cr.save(comment);

        if(post.getUser() != user) { //post작성자가 작성한 댓글이 아니면 알람보내기
            as.newAlarm(post.getUser(),user.getUserId(), postId, comment.getCreatedAt(), "NEW_COMMENT_ON_POST", "new comment!");
        }

        return CommentCreateResponse.toResponse(savedComment);
    }

    //코멘트 수정
    public CommentModifyResponse modifyComment(Long postId, Long id, CommentModifyRequest dto, Authentication authentication) {
        User user = validateUser(authentication);
        Comment comment = validateComment(id);
        validateAuthority(comment.getUser(), user); //comment를 작성한 유저와 접근(수정)한 유저가 동일한지 확인

        comment.update(dto.getComment());
        Comment savedComment = cr.save(comment);

        return CommentModifyResponse.toResponse(savedComment);

    }

    //코멘트 삭제
    public CommentDeleteResponse deleteComment(Long postId, Long id, Authentication authentication) {
        User user = validateUser(authentication);
        Post post = validatePost(postId);
        Comment comment = validateComment(id);

        validateAuthority(comment.getPost(), post); //접근하려는 포스트와 코멘트가 작성된 포스트가 동일한지 확인
        validateAuthority(comment.getUser(), user); //comment를 작성한 유저와 접근(삭제)한 유저가 동일한지 확인

        cr.delete(comment);

        return CommentDeleteResponse.toResponse(id);

    }

    //포스트 해당 코멘트 전체 조회
    public Page<CommentListResponse> showComments(Long postId, Pageable pageable) {
        Post post = validatePost(postId);
        return cr.findByPost(post, pageable).map(CommentListResponse::toCommentListResponse);
    }



}
