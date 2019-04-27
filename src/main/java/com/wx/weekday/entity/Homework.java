package com.wx.weekday.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Homework {
    @Id
    @GeneratedValue
    private long id;
    private String number;//学号
    private Integer beginweek;//布置作业的周数
    private Integer beginday;//布置作业在星期几
    private Integer beginlesson;//布置作业的节数
    private Integer endweek;//交作业的周数
    private Integer endday;//交作业在星期几
    private Integer endlesson;//交作业的节数
    private String content;//内容
}
