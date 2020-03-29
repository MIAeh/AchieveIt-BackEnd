package com.achieveit.application.controller;

import com.achieveit.application.entity.WorkHourEntity;
import com.achieveit.application.service.WorkHourService;
import com.achieveit.application.wrapper.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;

@Controller
@RequestMapping("/workHour/")
public class WorkHourController {

    private final WorkHourService workHourService;

    @Autowired
    public  WorkHourController(WorkHourService workHourService){
        this.workHourService=workHourService;
    }

    @CrossOrigin
    @PostMapping("applyWorkHour")
    public ResponseResult<WorkHourEntity> applyWorkHour(@RequestParam(name="applyerID") String applyerId,
                                                 @RequestParam(name="featureName")String featureName,
                                                 @RequestParam(name="activityName")String activityName,
                                                 @RequestParam(name="startTime") String startTime,
                                                 @RequestParam(name="endTime") String endTime){
        Date startTimeL=new Date(Long.parseLong(startTime));
        Date endTimeL=new Date(Long.parseLong(endTime));
       return workHourService.applyWordHour(applyerId,featureName,activityName,startTimeL,endTimeL);
    }

    @CrossOrigin
    @GetMapping("getWorkHoursByStatus")
    public ResponseResult<ArrayList<WorkHourEntity>> getWorkHoursByStatus(@RequestParam(name = "status")int status){
        return workHourService.getWorkHourByStatus(status);
    }

    @CrossOrigin
    @PostMapping("approveWorkHour")
    public ResponseResult<Integer> approveWorkHour(@RequestParam(name="workHourID")String workHourId,
                                            @RequestParam(name="approverID")String approverId){
        return workHourService.approveWordHour(workHourId,approverId);
    }
}
