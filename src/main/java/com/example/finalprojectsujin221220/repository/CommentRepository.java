package com.example.finalprojectsujin221220.repository;

import com.example.finalprojectsujin221220.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {


}
