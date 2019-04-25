package com.wx.weekday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class WeekdayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeekdayApplication.class, args);
    }

}
