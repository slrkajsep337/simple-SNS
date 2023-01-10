package com.example.finalprojectsujin221220.repository;

import com.example.finalprojectsujin221220.domain.entity.Alarm;
import com.example.finalprojectsujin221220.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findByUser(User user, Pageable pageable);
//    Page<Alarm> findByUser(User user, Pageable pageable);
}
