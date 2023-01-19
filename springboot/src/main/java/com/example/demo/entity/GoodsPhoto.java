package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author Log
 * @Date 2022/12/14 14:26
 * TODO
 */

@TableName("goodsphoto")
@Data
public class GoodsPhoto {
    private String name;
    private String photourl;
}
