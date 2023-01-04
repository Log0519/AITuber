package com.example.demo.controller;

import com.example.demo.common.Result;
import com.log.DanmuKafka;
import com.log.getDanmu;
import org.springframework.web.bind.annotation.*;
import com.utils.GetRoomID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

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

    @GetMapping ("/write")
    public Result<?> write(@RequestParam(defaultValue = "false") Boolean flag){
        DanmuKafka.MyRichSinkToMySQL sink = new DanmuKafka.MyRichSinkToMySQL();
        System.out.println("flag值为"+flag);
        if(flag){
            System.out.println("flag值为"+flag);
            DanmuKafka.GetDanmu(sink);
        }
          else {
            System.out.println("flag值为"+flag);
              sink.close();
        }
        return Result.success();
    }

    @GetMapping ("/get")
    public Result<?> get(
    ){
        ArrayList<String> list = getDanmu.Bill();
        return Result.success(list);
    }

    @GetMapping ("/getRoomID")
    public Result<?> getRoomID(@RequestParam(defaultValue = "") String id
    ){
        String roomID = GetRoomID.GetBillRoomID(id);
        return Result.success(roomID);
    }



}








