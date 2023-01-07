package com.example.finalprojectsujin221220.domain.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@SQLDelete(sql = "UPDATE comment SET deleted_at = current_timestamp WHERE comment_id = ?")
@Where(clause = "deleted_at is NULL")
public class Comment extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String comment;

    //유저가 작성한 모든 댓글을 한번에 볼 수 있도록 매핑
    @ManyToOne
    @JoinColumn(name = "userId") //User 의 id
    private User user;

    //한 포스트에 작성된 모든 댓글을 한번에 볼 수 있도록 매핑
    @ManyToOne
    @JoinColumn(name = "postId") //User 의 id
    private Post post;


}
