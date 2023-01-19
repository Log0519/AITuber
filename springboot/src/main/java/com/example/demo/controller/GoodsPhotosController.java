package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.common.Result;
import com.example.demo.entity.AreaHome;
import com.example.demo.entity.GoodsPhoto;
import com.example.demo.mapper.AreaHomeMapper;
import com.example.demo.mapper.GoodsPhotoMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author Log
 * @Date 2022/10/11 22:26
 * TODO
 */
@RestController
@RequestMapping("/goodsPhotos")
public class GoodsPhotosController {
    @Resource
    GoodsPhotoMapper goodsPhotoMapper;

    @GetMapping("/init")
    public Result<?> init(@RequestParam(defaultValue = "") String name){
        List<GoodsPhoto> list = goodsPhotoMapper.selectList(Wrappers.<GoodsPhoto>lambdaQuery()
                .select(GoodsPhoto::getPhotourl).eq(GoodsPhoto::getName,name)
        );
        System.out.println(name);
        for (GoodsPhoto goodsPhoto : list) {
            System.out.println(goodsPhoto+"结果");
        }
        System.out.println("执行方法");
        return Result.success(list);
    }


}











