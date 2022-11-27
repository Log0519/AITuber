package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author Log
 * @Date 2022/10/11 22:28
 * TODO User对象
 */
@TableName("weather_demo_user")
@Data
public class User {
    private String username;
    private String password;

}
