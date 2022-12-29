package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.common.Result;
import com.example.demo.entity.AreaHome;
import com.example.demo.mapper.AreaHomeMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author Log
 * @Date 2022/10/11 22:26
 * TODO
 */
@RestController
@RequestMapping("/areaHome")
public class AreaHomeController {
    @Resource
    AreaHomeMapper areaHomeMapper;

    @GetMapping("/init")
    public Result<?> init(){
        List<AreaHome> list = areaHomeMapper.selectList(Wrappers.<AreaHome>lambdaQuery()
                .select(AreaHome::getName,AreaHome::getCreatetime,AreaHome::getState,AreaHome::getHost
                ,AreaHome::getHomeurl)
        );
        return Result.success(list);
    }

    @GetMapping("/add")
    public Result<?> add(@RequestParam(defaultValue = "") String name,
                         @RequestParam(defaultValue = "") String createtime,
                         @RequestParam(defaultValue = "") String host,
                         @RequestParam(defaultValue = "") String state,
                         @RequestParam(defaultValue = "") String homeurl){
        AreaHome areaHome = new AreaHome();
        areaHome.setName(name);
        areaHome.setCreatetime(createtime);
        areaHome.setHost(host);
        areaHome.setState(state);
        areaHome.setHomeurl(homeurl);
        areaHomeMapper.insert(areaHome);
        return Result.success();
    }

    @GetMapping("/clear")
    public Result<?> clear(@RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String createtime){
        areaHomeMapper.delete(Wrappers.<AreaHome>lambdaQuery()
                .eq(AreaHome::getName,name)
                .eq(AreaHome::getCreatetime,createtime));
        return Result.success();
    }

}











