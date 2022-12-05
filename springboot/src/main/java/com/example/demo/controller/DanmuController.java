package com.example.demo.controller;

import com.example.demo.common.Result;
import com.log.DanmuKafka;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Log
 * @Date 2022/10/11 22:26
 * TODO
 */
@RestController
@RequestMapping("/danmuSource")
public class DanmuController {

    @GetMapping ("/send")
    public Result<?> login(@RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String neirong,
                           @RequestParam(defaultValue = "") String time
    ){

        DanmuKafka.SendDanmu(name+","+neirong+","+time);
        return Result.success();
    }



}








