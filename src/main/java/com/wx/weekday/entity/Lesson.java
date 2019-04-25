package com.wx.weekday.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Lesson {
    @Id
    @GeneratedValue
    private Integer id;
    private String number;//学号
    private String name;//课程名称
    private String teacher;//授课教师
    private String beginweek;//开始周数
    private String endweek;//结束周数
    private String begin;//开始节数
    private String end;//结束节数
    private String address;//上课地点
}
