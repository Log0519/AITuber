package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author Log
 * @Date 2022/10/14 8:20
 * TODO
 */
public class JavaRunPython {
    public void comeI(String pace, String timeBegin, String timeEnd){
        Process proc;
        try {
            String[] arg=new String[]{"python","D:\\Python_IDEA\\weaterAvgDemo\\weatherDemo.py",pace,timeBegin,timeEnd};

            proc = Runtime.getRuntime().exec(arg);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gb2312"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
