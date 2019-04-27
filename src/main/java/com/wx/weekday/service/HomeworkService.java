package com.wx.weekday.service;

import com.wx.weekday.entity.Homework;
import com.wx.weekday.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeworkService {
    @Autowired
    private HomeworkRepository repository;

    //添加作业
    public Homework add(Homework homework){
        return repository.save(homework);
    }
}
