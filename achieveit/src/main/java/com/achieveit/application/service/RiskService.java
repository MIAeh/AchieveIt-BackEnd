package com.achieveit.application.service;

import com.achieveit.application.entity.RiskEntity;
import com.achieveit.application.entity.RiskTemplate;
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
    public ResponseResult<RiskEntity> addRisk(String riskDescription,int riskType,String riskCharger,
                                           int riskLevel,int riskInfluence,int riskFrequency,String riskStrategy,int riskStatus,HttpSession session){
        RiskEntity riskEntity=new RiskEntity(riskDescription,riskType,riskCharger,riskLevel,riskInfluence,riskFrequency,riskStrategy,riskStatus);
        int res=riskMapper.insertRisk(riskEntity);
        return ResultGenerator.success(riskEntity);
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

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> changeRiskStatus(int riskId,String riskCharger){
        RiskEntity riskEntity=riskMapper.getRiskByRiskId(riskId);
        if(riskEntity==null||!riskEntity.getRiskCharger().equals(riskCharger)){
            return ResultGenerator.error("unknown error in getRisk");
        }
        if(riskEntity.getRiskStatus()==1){
            return ResultGenerator.success("Has changed already!");
        }
        riskMapper.changeRiskStatus(riskId,1);
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArrayList<String>> getRiskHoldersByRiskId(int riskId){
        ArrayList<String> riskHolders=riskMapper.getAllRiskHolderByRiskId(riskId);
        return ResultGenerator.success(riskHolders);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> addRiskHoldersByRiskId(int riskId,String riskHolder){
        int res=riskMapper.addRiskHolder(riskId,riskHolder);
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArrayList<RiskTemplate>> getRiskTemplates(){
        ArrayList<RiskTemplate> riskTemplates=riskMapper.getRiskTemplates();
        return ResultGenerator.success(riskTemplates);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> addRiskTemplate(String riskDescription,int riskType,
                                                   int riskLevel,int riskInfluence,String riskStrategy){
        RiskTemplate riskTemplate=new RiskTemplate(riskDescription,riskType,riskLevel,riskInfluence,riskStrategy);
        int res=riskMapper.addRiskTemplate(riskTemplate);
        return ResultGenerator.success();
    }
}
