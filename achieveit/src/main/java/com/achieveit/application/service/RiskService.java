package com.achieveit.application.service;

import com.achieveit.application.entity.RiskEntity;
import com.achieveit.application.entity.RiskTemplate;
import com.achieveit.application.entity.UserEntity;
import com.achieveit.application.mapper.RiskMapper;
import com.achieveit.application.mapper.UserMapper;
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
    private  final UserMapper userMapper;

    @Autowired
    public RiskService(RiskMapper riskMapper,UserMapper userMapper){
        this.riskMapper=riskMapper;
        this.userMapper=userMapper;
    }

    private final Logger logger = LoggerFactory.getLogger(RiskService.class);

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<RiskEntity> addRisk(String riskDescription,int riskType,String riskCharger,
                                           int riskLevel,int riskInfluence,int riskFrequency,String riskStrategy,
                                              int riskStatus,String projectID,String[] riskHolders,HttpSession session){
        UserEntity riskChargerEntity=userMapper.getUserInfoById(riskCharger);
        if(riskChargerEntity==null) return ResultGenerator.error("riskCharger doesn't exist!");
        RiskEntity riskEntity=new RiskEntity(riskDescription,riskType,riskCharger,riskChargerEntity.getUserName(),riskLevel,riskInfluence,riskFrequency,riskStrategy,riskStatus,projectID);
        int res=riskMapper.insertRisk(riskEntity);
        for(String holder:riskHolders){
            addRiskHoldersByRiskId(riskEntity.getRiskID(),holder);
        }
        return ResultGenerator.success(riskEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> deleteRisk(int riskId){
        int res=riskMapper.deleteRiskByRiskId(riskId);
        riskMapper.deleteAllRiskHoldersByRiskId(riskId);
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

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArrayList<RiskTemplate>> getRiskTemplatesByProjectID(String projectID){
        ArrayList<RiskEntity> riskEntities=riskMapper.getRisksByProjectID(projectID);
        ArrayList<RiskTemplate> riskTemplates=new ArrayList<RiskTemplate>();
        for(RiskEntity entity:riskEntities){
            RiskTemplate riskTemplate=new RiskTemplate(entity.getRiskDescription(),entity.getRiskType(),entity.getRiskLevel(),entity.getRiskInfluence(),entity.getRiskStrategy());
            riskTemplates.add(riskTemplate);
        }
        return ResultGenerator.success(riskTemplates);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArrayList<RiskEntity>> getRisksByProjectID(String projectID){
        ArrayList<RiskEntity> riskEntities=riskMapper.getRisksByProjectID(projectID);
        return ResultGenerator.success(riskEntities);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> solveRisk(String riskID){
        int riskId= Integer.parseInt(riskID);
        riskMapper.changeRiskStatus(riskId,1);
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<RiskEntity> updateRiskByRiskID(String riskId,
                                                      String riskDescription,
                                                      int riskInfluence,
                                                      int riskType,
                                                      int riskLevel,
                                                      int riskFrequency,
                                                      String riskCharger,
                                                      String[] riskHolders,
                                                      String riskStrategy){
        RiskEntity riskEntity=riskMapper.getRiskByRiskId(Integer.parseInt(riskId));
        if(riskEntity==null) return ResultGenerator.error("invalid risk");
        if(riskDescription!=null&&!riskDescription.equals("")){
            riskEntity.setRiskDescription(riskDescription);
        }
        if(riskInfluence>=0){
            riskEntity.setRiskInfluence(riskInfluence);
        }
        if(riskType>=0){
            riskEntity.setRiskType(riskType);
        }
        if(riskLevel>=0){
            riskEntity.setRiskLevel(riskLevel);
        }
        if(riskFrequency>=0){
            riskEntity.setRiskFrequency(riskFrequency);
        }
        if(riskCharger!=null&&!riskCharger.equals("")){
            riskEntity.setRiskCharger(riskCharger);
        }
        if(riskStrategy!=null&&!riskStrategy.equals("")){
            riskEntity.setRiskStrategy(riskStrategy);
        }
        if(riskHolders!=null&&riskHolders.length>0){
            riskMapper.deleteRiskByRiskId(Integer.parseInt(riskId));
            for(String holder:riskHolders){
                riskMapper.addRiskHolder(Integer.parseInt(riskId),holder);
            }
        }
        riskMapper.updateRiskByRiskId(riskEntity,Integer.parseInt(riskId));
        return ResultGenerator.success(riskEntity);
    }
}
