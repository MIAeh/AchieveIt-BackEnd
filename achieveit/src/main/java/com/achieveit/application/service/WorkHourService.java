package com.achieveit.application.service;

import com.achieveit.application.entity.UserEntity;
import com.achieveit.application.entity.WorkHourEntity;
import com.achieveit.application.mapper.UserMapper;
import com.achieveit.application.mapper.WorkHourMapper;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class WorkHourService {
    private  final WorkHourMapper workHourMapper;
    private  final UserMapper userMapper;

    @Autowired
    public WorkHourService(WorkHourMapper workHourMapper,UserMapper userMapper){
        this.workHourMapper=workHourMapper;
        this.userMapper=userMapper;
    }

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<WorkHourEntity> applyWordHour(String applyerId, String featureName, String activityName, String projectId, Timestamp startTime, Timestamp endTime){
        if(startTime.getTime()>endTime.getTime()){
            return ResultGenerator.error("end_time must bigger than start_time");
        }
        UserEntity applyerEntity=userMapper.getUserInfoById(applyerId);
        if(applyerEntity==null) return ResultGenerator.error("applyer doesn't exist!");
        WorkHourEntity entity=new WorkHourEntity(applyerId,applyerEntity.getUserName(),featureName,activityName,projectId,startTime,endTime);
        int res=workHourMapper.insertWorkHour(entity);
        return ResultGenerator.success(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArrayList<WorkHourEntity>> getWorkHoursByStatusAndProjectId(int status,String projectId){
        ArrayList<WorkHourEntity> entities=workHourMapper.getWorkHoursByStatusAndProjectId(status,projectId);
        return ResultGenerator.success(entities);
    }

    @Transactional(rollbackFor =Exception.class)
    public ResponseResult<Integer> approveWordHour(String wordHourId,String approverId){
        UserEntity approverEntity=userMapper.getUserInfoById(approverId);
        if(approverEntity==null) return ResultGenerator.error("approver doesn't exist!");
        workHourMapper.setWorkHourApproverIdAndName(wordHourId,approverId,approverEntity.getUserName());
        workHourMapper.changeWordHourStatus(wordHourId,1);
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor =Exception.class)
    public ResponseResult<ArrayList<WorkHourEntity>> getAllWorkHours(){
        ArrayList<WorkHourEntity> entities=workHourMapper.getAllWorkHours();
        return ResultGenerator.success(entities);
    }

    @Transactional(rollbackFor =Exception.class)
    public ResponseResult<ArrayList<WorkHourEntity>> getMyWorkHourByProjectID(String applyerId,String projectId) {
        ArrayList<WorkHourEntity> entities=workHourMapper.getWorkHoursByIdAndProjectID(applyerId,projectId);
        return ResultGenerator.success(entities);
    }

    @Transactional(rollbackFor =Exception.class)
    public ResponseResult<ArrayList<WorkHourEntity>> getWorkHoursByProjectID(String projectId) {
        ArrayList<WorkHourEntity> entities=workHourMapper.getWorkHoursByProjectID(projectId);
        if(entities.size()>0)
            return ResultGenerator.success(entities);
        else
            return ResultGenerator.success("no records find!");
    }

    //To Be Done
    @Transactional(rollbackFor =Exception.class)
    public ResponseResult<Boolean> deleteWorkHour(String workHourId){
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor =Exception.class)
    public ResponseResult<Boolean> rejectWorkHour(String workHourID, String approverID, HttpSession session){
        WorkHourEntity workHourEntity=workHourMapper.getWorkHourByID(workHourID);
        if(workHourEntity==null) return ResultGenerator.error("invalid workHourID!");

        if(approverID.equals("")){
            if(session.getAttribute("userId")!=null&&session.getAttribute("userId").equals(workHourEntity.getApproverId())){
                approverID=workHourEntity.getApproverId();
            }
        }
        if(!approverID.equals(workHourEntity.getApproverId())){
            return ResultGenerator.error("approver may not valid!");
        }

        workHourMapper.changeWordHourStatus(workHourID,0);
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor =Exception.class)
    public ResponseResult<Boolean> updateWorkHour(String workHourID,String featureName,String activityName,
                                                  String startTime,String endTime){
        WorkHourEntity workHourEntity=workHourMapper.getWorkHourByID(workHourID);
        if(workHourEntity==null)
            return ResultGenerator.error("invalid workHour id!");
        if(featureName!=null&&!featureName.equals("")){
            workHourEntity.setFeatureName(featureName);
        }
        if(activityName!=null&&!activityName.equals("")){
            workHourEntity.setActivityName(activityName);
        }
        if(startTime!=null&&!startTime.equals("")){
            workHourEntity.setStartTime(startTime);
        }
        if(endTime!=null&&!endTime.equals("")){
            workHourEntity.setEndTime(endTime);
        }
        workHourMapper.deleteWorkHourById(workHourEntity.getWorkHourId());
        workHourMapper.insertWorkHour(workHourEntity);
        return ResultGenerator.success();
    }
}
