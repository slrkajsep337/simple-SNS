package com.example.finalprojectsujin221220.repository;

import com.example.finalprojectsujin221220.domain.entity.Likepost;
import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likepost, Long> {

//    Optional<LikeEntity> findByUserAndPost(User user, Post post);

    Likepost findByUserAndPost(User user, Post post);
    List<Likepost> findByPost(Post post);
}
