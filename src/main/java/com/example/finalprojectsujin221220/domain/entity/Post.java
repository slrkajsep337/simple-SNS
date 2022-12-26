package com.example.finalprojectsujin221220.domain.entity;


import com.example.finalprojectsujin221220.dto.PostDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    private String body;
    private String userName;

    //유저가 작성한 모든 게시글을 한번에 볼 수 있도록 매핑
    @ManyToOne
    @JoinColumn(name = "userId") //User 의 id
    private User user;

    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public static PostDetailsResponse of(Post post) {
        return PostDetailsResponse.builder()
                .id(post.getPostId())
                .title(post.getTitle())
                .body(post.getBody())
                .userName(post.getUserName())
                .createdAt(post.getCreatedAt())
                .LastModifiedAt(post.getLastModifiedAt())
                .build();
    }


}
