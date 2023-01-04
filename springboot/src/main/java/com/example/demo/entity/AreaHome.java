package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author Log
 * @Date 2022/12/14 14:26
 * TODO
 */

@TableName("area")
@Data
public class AreaHome {
    private String name;
    private String createtime;
    private String state;
    private String host;
    private String homeurl;
}
