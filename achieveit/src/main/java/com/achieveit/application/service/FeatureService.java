package com.achieveit.application.service;

import com.achieveit.application.entity.FeatureEntity;
import com.achieveit.application.mapper.FeatureMapper;
import com.achieveit.application.mapper.UserMapper;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FeatureService {
    /**
     * Mapper for feature
     */
    private final FeatureMapper featureMapper;

    /**
     * Logger
     */
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public FeatureService(FeatureMapper featureMapper) {
        this.featureMapper=featureMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    ResponseResult<ArrayList<FeatureEntity>> getAllTopFeatures(HttpSession session){
        return ResultGenerator.success(featureMapper.getAllTopFeatures());
    }

    @Transactional(rollbackFor = Exception.class)
    ResponseResult<ArrayList<FeatureEntity>> getAllChildren(String fatherId,HttpSession session){
        FeatureEntity fatherEntity=featureMapper.getFeatureById(fatherId);
        if(fatherEntity==null) return ResultGenerator.error("No This Feature");
        if(fatherEntity.getFeatureLevel()==2) return ResultGenerator.error("Children Node has not child!");

        ArrayList<FeatureEntity> entities=featureMapper.getChildrenByFatherId(fatherId);
        return ResultGenerator.success(entities);
    }

    @Transactional(rollbackFor = Exception.class)
    ResponseResult<ArrayList<FeatureEntity>> getFeaturesInfo(HttpSession session){
        ArrayList<FeatureEntity> allTopFeatures=featureMapper.getAllTopFeatures();
        for(FeatureEntity entity:allTopFeatures){
            ArrayList<FeatureEntity> children=featureMapper.getChildrenByFatherId(entity.getFeatureId());
            entity.setAllChildren(children);
            for(FeatureEntity entity1:children){
                ArrayList<FeatureEntity> children2=featureMapper.getChildrenByFatherId(entity1.getFeatureId());
                entity1.setAllChildren(children2);
            }
        }
        return ResultGenerator.success(allTopFeatures);
    }

    @Transactional(rollbackFor = Exception.class)
    ResponseResult<Boolean> insertTopFeature(String featureName, String projectId, HttpSession session){
        FeatureEntity entity=new FeatureEntity(0,projectId,featureName);
        featureMapper.insertFeatures(entity);
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor = Exception.class)
    ResponseResult<Boolean> insertSubFeature(String featureName, String projectId, String fatherId,HttpSession session){
        FeatureEntity fatherEntity=featureMapper.getFeatureById(fatherId);
        if(fatherEntity==null) return ResultGenerator.error("no father");
        int myLevel=fatherEntity.getFeatureLevel()-1;
        FeatureEntity myEntity=new FeatureEntity(myLevel,fatherId,projectId,featureName);
        int res=featureMapper.insertFeatures(myEntity);
        if(res==0)
            return ResultGenerator.error("insert failed!");
        else
            return ResultGenerator.success();
    }
}
