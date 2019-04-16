package com.wx.weekday.service;

import com.wx.weekday.dao.LessonDao;
import com.wx.weekday.entity.Lesson;

import java.util.List;

public class LessonService {
    private LessonDao lessonDao=new LessonDao();

    //添加
    public int add(Lesson lesson){
        return lessonDao.add(lesson);
    }
    //修改
    public int chg(String number,Lesson lesson){
        return lessonDao.chg(number,lesson);
    }
    //查询
    public List<Lesson> findByNumber(String number){
        return lessonDao.findByNumber(number);
    }
}
