package com.example.finalprojectsujin221220.repository;

import com.example.finalprojectsujin221220.domain.entity.LikeEntity;
import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

//    Optional<LikeEntity> findByUserAndPost(User user, Post post);

    LikeEntity findByUserAndPost(User user, Post post);
}
