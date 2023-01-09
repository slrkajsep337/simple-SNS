package com.example.finalprojectsujin221220.service;


import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.dto.*;
import com.example.finalprojectsujin221220.exception.ApplicationException;
import com.example.finalprojectsujin221220.exception.ErrorCode;
import com.example.finalprojectsujin221220.repository.PostRepository;
import com.example.finalprojectsujin221220.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostService {

    private final UserRepository ur;
    private final PostRepository pr;

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
    //[중복 로직] 권한 확인 (post를 작성한 유저와 접근 하려는 유저가 동일한지 확인)
    public void validateAuthority(User postUser, User user) {
        if( !postUser.equals(user)) throw new ApplicationException(ErrorCode.INVALID_PERMISSION, ErrorCode.INVALID_PERMISSION.getMessage());
    }




    //포스트 등록
    public PostCreateResponse newPost(PostCreateRequest dto, Authentication authentication) {
        User user = validateUser(authentication);
        Post post = dto.toEntity(user);
        Post savedPost = pr.save(post);
        return PostCreateResponse.toResponse(savedPost);
    }

    //포스트 한개 조회
    public PostDetailsResponse showOnePost(Long id) {
        Post post = validatePost(id);
        return PostDetailsResponse.toResponse(post);
    }

    //포스트 전체 조회
    public Page<PostListResponse> showPosts(Pageable pageable) {
        Page<Post> posts = pr.findAll(pageable);
        return posts.map(PostListResponse::toResponse);
    }

    //포스트 수정
    public PostModifyResponse modifyPost(Long postId, PostModifyRequest dto, Authentication authentication) {
        User user = validateUser(authentication);
        Post post = validatePost(postId);
        validateAuthority(post.getUser(), user); //권한 확인 (post를 작성한 유저와 접근 하려는 유저가 동일한지 확인)

        post.update(dto.getTitle(), dto.getBody());
        Post savedPost = pr.save(post);
        pr.flush(); //시간 맞춰주기

        return PostModifyResponse.toResponse(savedPost);
    }

    //포스트 삭제
    public PostDeleteResponse deletePost(Long postId, Authentication authentication) {
        User user = validateUser(authentication);
        Post post = validatePost(postId);
        validateAuthority(post.getUser(), user); //권한 확인 (post를 작성한 유저와 접근 하려는 유저가 동일한지 확인)
        pr.delete(post);
        return PostDeleteResponse.toResponse(postId);
    }

    //마이피드 조회
    public Page<MyPostListResponse> showMyPosts(Pageable pageable, Authentication authentication) {
        User user = validateUser(authentication);
        Page<Post> byUser = pr.findByUser(user, pageable);
        return byUser.map(MyPostListResponse::toResponse);
    }
}
