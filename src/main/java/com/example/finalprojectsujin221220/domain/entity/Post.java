package com.example.finalprojectsujin221220.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@SQLDelete(sql = "UPDATE post SET deleted_at = current_timestamp WHERE post_id = ?")
@Where(clause = "deleted_at is NULL")
public class Post extends BaseTime {

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

    public void update(String title, String body) {
        this.title = title;
        this.body = body;
    }

}
