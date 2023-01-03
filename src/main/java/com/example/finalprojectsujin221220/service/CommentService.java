package com.example.finalprojectsujin221220.service;

import com.example.finalprojectsujin221220.domain.entity.Comment;
import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.dto.CommentCreateRequest;
import com.example.finalprojectsujin221220.dto.CommentCreateResponse;
import com.example.finalprojectsujin221220.dto.CommentModifyRequest;
import com.example.finalprojectsujin221220.dto.CommentModifyResponse;
import com.example.finalprojectsujin221220.exception.ApplicationException;
import com.example.finalprojectsujin221220.exception.ErrorCode;
import com.example.finalprojectsujin221220.repository.CommentRepository;
import com.example.finalprojectsujin221220.repository.PostRepository;
import com.example.finalprojectsujin221220.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserRepository ur;
    private final PostRepository pr;
    private final CommentRepository cr;


    public CommentCreateResponse newComment(Long postId, CommentCreateRequest dto, Authentication authentication) {

        Optional<User> userOpt = ur.findByUserName(authentication.getName());
        Optional<Post> postOpt = pr.findById(postId);

        Comment comment = Comment.builder()
                .comment(dto.getComment())
                .createdAt(LocalDateTime.now())
                .lastModifiedAt(LocalDateTime.now())
                .user(userOpt.get())
                .post(postOpt.get())
                .build();

        Comment savedComment = cr.save(comment);

        return CommentCreateResponse.builder()
                .id(savedComment.getCommentId())
                .comment(savedComment.getComment())
                .userName(authentication.getName())
                .postId(postId)
                .createdAt(savedComment.getCreatedAt())
                .build();

    }

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



}
