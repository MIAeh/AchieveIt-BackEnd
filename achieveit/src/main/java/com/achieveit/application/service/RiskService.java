package com.achieveit.application.service;

import com.achieveit.application.entity.RiskEntity;
import com.achieveit.application.mapper.RiskMapper;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class RiskService {
    private final RiskMapper riskMapper;

    @Autowired
    public RiskService(RiskMapper riskMapper){
        this.riskMapper=riskMapper;
    }

    private final Logger logger = LoggerFactory.getLogger(RiskService.class);

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> addRisk(String riskDescription,int riskType,String riskCharger,
                                           int riskLevel,int riskInfluence,int riskStatus,HttpSession session){
        RiskEntity riskEntity=new RiskEntity(riskDescription,riskType,riskCharger,riskLevel,riskInfluence,riskStatus);
        int res=riskMapper.insertRisk(riskEntity);
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> deleteRisk(int riskId){
        int res=riskMapper.deleteRisk(riskId);
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArrayList<RiskEntity>> getAllRisks(HttpSession session){
        ArrayList<RiskEntity> riskEntities= riskMapper.getAllRisks();
        return ResultGenerator.success(riskEntities);
    }

}
