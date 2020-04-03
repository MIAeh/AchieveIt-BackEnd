package com.achieveit.application.service;

import com.achieveit.application.entity.WorkHourEntity;
import com.achieveit.application.mapper.WorkHourMapper;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class WorkHourService {
    private  final WorkHourMapper workHourMapper;

    @Autowired
    public WorkHourService(WorkHourMapper workHourMapper){
        this.workHourMapper=workHourMapper;
    }

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<WorkHourEntity> applyWordHour(String applyerId, String featureName, String activityName, Date startTime, Date endTime){
        WorkHourEntity entity=new WorkHourEntity(applyerId,featureName,activityName,startTime,endTime);
        int res=workHourMapper.insertWorkHour(entity);
        return ResultGenerator.success(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArrayList<WorkHourEntity>> getWorkHourByStatus(int status){
        ArrayList<WorkHourEntity> entities=workHourMapper.getWorkHoursByStatus(status);
        return ResultGenerator.success(entities);
    }

    @Transactional(rollbackFor =Exception.class)
    public ResponseResult<Integer> approveWordHour(String wordHourId,String approverId){
        workHourMapper.setWorkHourApproverId(wordHourId,approverId);
        workHourMapper.changeWordHourStatus(wordHourId,1);
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor =Exception.class)
    public ResponseResult<ArrayList<WorkHourEntity>> getAllWorkHours(){
        ArrayList<WorkHourEntity> entities=workHourMapper.getAllWorkHours();
        return ResultGenerator.success(entities);
    }

    @Transactional(rollbackFor =Exception.class)
    public ResponseResult<ArrayList<WorkHourEntity>> getMyWorkHourById(String applyerId) {
        ArrayList<WorkHourEntity> entities=workHourMapper.getWorkHoursById(applyerId);
        return ResultGenerator.success(entities);
    }

    //To Be Done
    @Transactional(rollbackFor =Exception.class)
    public ResponseResult<Boolean> deleteWorkHour(String workHourId){
        return ResultGenerator.success();
    }
}
