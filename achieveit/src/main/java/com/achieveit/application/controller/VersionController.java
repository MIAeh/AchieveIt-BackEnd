package com.achieveit.application.controller;

import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/version/")
public class VersionController {
    @GetMapping("updateTime")
    public ResponseResult<Date> getUpdateTime(){
        return ResultGenerator.success("version",new Date(System.currentTimeMillis()));
    }
}
