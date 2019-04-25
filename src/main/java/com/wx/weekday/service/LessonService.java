package com.wx.weekday.service;

import com.wx.weekday.entity.Lesson;
import com.wx.weekday.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    @Autowired
    private LessonRepository repository;

    //添加
    public Lesson add(Lesson lesson){
        return repository.save(lesson);
    }
    //查询
    public List<Lesson> findByNumber(String number){
        return repository.findAllByNumber(number);
    }
}
