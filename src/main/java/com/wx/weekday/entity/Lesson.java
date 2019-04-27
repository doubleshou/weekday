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
    private long id;
    private String number;//学号
    private String name;//课程名称
    private String teacher;//授课教师
    private Integer beginweek;//开始周数
    private Integer endweek;//结束周数
    private Integer day;//星期几
    private Integer begin;//开始节数
    private Integer end;//结束节数
    private String address;//上课地点
}
