package com.achieveit.application.controller;

import com.achieveit.application.entity.WorkHourEntity;
import com.achieveit.application.service.WorkHourService;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;

@RestController
@RequestMapping("/workHour/")
public class WorkHourController {

    private final WorkHourService workHourService;

    @Autowired
    public  WorkHourController(WorkHourService workHourService){
        this.workHourService=workHourService;
    }

    @CrossOrigin
    @PostMapping("applyWorkHour")
    public ResponseResult<WorkHourEntity> applyWorkHour(@RequestParam(name="applyerID",required = false,defaultValue = "") String applyerId,
                                                        @RequestParam(name="featureName")String featureName,
                                                        @RequestParam(name="activityName")String activityName,
                                                        @RequestParam(name="projectID")String projectId,
                                                        @RequestParam(name="startTime") String startTime,
                                                        @RequestParam(name="endTime") String endTime,
                                                        HttpSession session){
        if(applyerId.equals("")){
            String userId=(String)session.getAttribute("userId");
            if(userId==null||userId.equals("")){
                return ResultGenerator.error("unknown error!");
            }
            applyerId=userId;
        }
        Date startTimeL=new Date(Long.parseLong(startTime));
        Date endTimeL=new Date(Long.parseLong(endTime));
       return workHourService.applyWordHour(applyerId,featureName,activityName,projectId,startTimeL,endTimeL);
    }

    @CrossOrigin
    @GetMapping("getWorkHoursByStatusAndProjectID")
    public ResponseResult<ArrayList<WorkHourEntity>> getWorkHoursByStatusAndProjectID(@RequestParam(name = "status",defaultValue = "0")int status,@RequestParam("projectID")String projectId){
        return workHourService.getWorkHoursByStatusAndProjectId(status,projectId);
    }

    @CrossOrigin
    @PostMapping("approveWorkHour")
    public ResponseResult<Integer> approveWorkHour(@RequestParam(name="workHourID")String workHourId,
                                            @RequestParam(name="approverID")String approverId){
        return workHourService.approveWordHour(workHourId,approverId);
    }

    @CrossOrigin
    @GetMapping("getAllWorkHours")
    public ResponseResult<ArrayList<WorkHourEntity>> getAllWorkHours(){
        return workHourService.getAllWorkHours();
    }

    @CrossOrigin
    @GetMapping("getMyWorkHoursByProjectID")
    public ResponseResult<ArrayList<WorkHourEntity>> getMyWorkHoursByProjectID(@RequestParam(name="myID",defaultValue = "",required = false)String myId,@RequestParam("projectID")String projectId,HttpSession session){
        if(myId.equals("")){
            String userId=(String)session.getAttribute("myId");
            if(userId==null||userId.equals("")){
                return ResultGenerator.error("unknown error!");
            }
            myId=userId;
        }
        return workHourService.getMyWorkHourByProjectID(myId,projectId);
    }

    @CrossOrigin
    @GetMapping("getMyWorkHoursToApproveByProjectID")
    public ResponseResult<ArrayList<WorkHourEntity>> getMyWorkHoursToApproveByProjectID(@RequestParam(name="myID",required = false,defaultValue = "")String myId,@RequestParam("projectID")String projectId,HttpSession session){
        if(myId.equals("")){
            String userId=(String)session.getAttribute("myId");
            if(userId==null||userId.equals("")){
                return ResultGenerator.error("unknown error!");
            }
            myId=userId;
        }
        ResponseResult<ArrayList<WorkHourEntity>> entities=workHourService.getMyWorkHourByProjectID(myId,projectId);
        ArrayList<WorkHourEntity> res=new ArrayList<WorkHourEntity>();
        for(WorkHourEntity entity:entities.getData()) {
            if (entity.getStatus() == 0) {
                res.add(entity);
            }
        }
        return ResultGenerator.success(res);
    }

    @CrossOrigin
    @GetMapping("getWorkHoursByProjectID")
    public ResponseResult<ArrayList<WorkHourEntity>> getWorkHoursByProjectID(@RequestParam("projectID")String projectId){
        return workHourService.getWorkHoursByProjectID(projectId);
    }
}
