package com.wx.weekday.repository;

import com.wx.weekday.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LessonRepository extends JpaRepository<Lesson,Integer> {
    List<Lesson> findAllByNumber(String number);
}
