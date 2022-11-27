package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.log.UserLogin;
import org.springframework.web.bind.annotation.*;
import scala.Tuple2;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.ArrayList;

/**
 * @Author Log
 * @Date 2022/10/11 22:26
 * TODO
 */
@RestController
@RequestMapping("/user")
public class UserController {

    //用户登录
    @PostMapping ("/login")
    public Result<?> login(@RequestBody User user
    ){
        boolean login = UserLogin.login(user.getUsername(), user.getPassword());
        if(login==false){
          return Result.error("-1","用户名或密码错误");
        } else {
            return Result.success();
        }
    }


}








