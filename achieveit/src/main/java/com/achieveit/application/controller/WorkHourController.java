package com.achieveit.application.controller;

import com.achieveit.application.entity.WorkHourEntity;
import com.achieveit.application.service.WorkHourService;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
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
        Timestamp startTimeL=new Timestamp(Long.parseLong(startTime));
        Timestamp endTimeL=new Timestamp(Long.parseLong(endTime));
       return workHourService.applyWordHour(applyerId,featureName,activityName,projectId,startTimeL,endTimeL);
    }

    @CrossOrigin
    @GetMapping("getWorkHoursByStatusAndProjectID")
    public ResponseResult<ArrayList<WorkHourEntity>> getWorkHoursByStatusAndProjectID(@RequestParam(name = "status",defaultValue = "0")int status,@RequestParam("projectID")String projectId){
        return workHourService.getWorkHoursByStatusAndProjectId(status,projectId);
    }

    @CrossOrigin
    @PostMapping("approveWorkHour")
    public ResponseResult<Integer> approveWorkHour(@RequestParam(name="workHourID",defaultValue = "",required = false)String workHourId,
                                            @RequestParam(name="approverID")String approverId,HttpSession session){
        if(approverId.equals("")){
            String userId=(String)session.getAttribute("userId");
            if(userId==null||userId.equals("")){
                return ResultGenerator.error("can't judge who you are");
            }
            approverId=userId;
        }
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
            String userId=(String)session.getAttribute("userId");
            if(userId==null||userId.equals("")){
                return ResultGenerator.error("can't get userId from session!");
            }
            myId=userId;
        }
        return workHourService.getMyWorkHoursByProjectID(myId,projectId);
    }

    @CrossOrigin
    @GetMapping("getMyWorkHoursToApproveByProjectID")
    public ResponseResult<ArrayList<WorkHourEntity>> getMyWorkHoursToApproveByProjectID(@RequestParam(name="myID",required = false,defaultValue = "")String myId,@RequestParam("projectID")String projectId,HttpSession session){
        if(myId.equals("")){
            String userId=(String)session.getAttribute("userId");
            if(userId==null||userId.equals("")){
                return ResultGenerator.error("can't get userId from session!");
            }
            myId=userId;
        }
        ResponseResult<ArrayList<WorkHourEntity>> entities=workHourService.getMyWorkHoursToApproveByProjectID(myId,projectId);
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

    @CrossOrigin
    @PostMapping("rejectWorkHour")
    public ResponseResult<Boolean> rejectWorkHour(@RequestParam("workHourID")String workHourID,@RequestParam(value = "approverID",defaultValue = "",required = false) String approverID,HttpSession session){
        return workHourService.rejectWorkHour(workHourID,approverID,session);
    }

    @CrossOrigin
    @PostMapping("updateWorkHour")
    public ResponseResult<WorkHourEntity> updateWorkHour(@RequestParam(value = "workHourID")String workHourID,
                                                  @RequestParam(value = "featureName",defaultValue = "",required = false)String featureName,
                                                  @RequestParam(value = "activityName",defaultValue = "",required = false)String activityName,
                                                  @RequestParam(value = "startTime",defaultValue = "",required = false)String startTime,
                                                  @RequestParam(value = "endTime",defaultValue = "",required = false)String endTime){
        return workHourService.updateWorkHour(workHourID,featureName,activityName,startTime,endTime);
    }
}
