package com.achieveit.application.controller;

import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/debug/")
public class VersionController {
    @GetMapping("getSystemTime")
    public ResponseResult<Date> getSystemTime(){
        return ResultGenerator.success("system",new Date(System.currentTimeMillis()));
    }

    @GetMapping("getUpdateTime")
    public ResponseResult<String> getUpdateTime(){
        return ResultGenerator.success("last_update","4-11-15:11");
    }

}
