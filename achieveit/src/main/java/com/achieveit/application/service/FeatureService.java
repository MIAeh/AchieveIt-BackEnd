package com.achieveit.application.service;

import com.achieveit.application.entity.FeatureEntity;
import com.achieveit.application.entity.FeatureUpLoad;
import com.achieveit.application.entity.FeatureUpLoadEntity;
import com.achieveit.application.mapper.FeatureMapper;
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
import java.util.Collections;
import java.util.Comparator;

@Service
public class FeatureService {
    /**
     * Mapper for feature
     */
    private final FeatureMapper featureMapper;

    /**
     * Logger
     */
    private final Logger logger = LoggerFactory.getLogger(FeatureService.class);

    @Autowired
    public FeatureService(FeatureMapper featureMapper) {
        this.featureMapper=featureMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArrayList<FeatureEntity>> getAllTopFeatures(HttpSession session){
        return ResultGenerator.success(featureMapper.getAllTopFeatures());
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArrayList<FeatureEntity>> getAllChildren(String fatherId,HttpSession session){
        FeatureEntity fatherEntity=featureMapper.getFeatureById(fatherId);
        if(fatherEntity==null) return ResultGenerator.error("No This Feature");
        if(fatherEntity.getFeatureLevel()==2) return ResultGenerator.error("Children Node has not child!");

        ArrayList<FeatureEntity> entities=featureMapper.getChildrenByFatherId(fatherId);
        return ResultGenerator.success(entities);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArrayList<FeatureEntity>> getFeaturesInfo(HttpSession session){
        ArrayList<FeatureEntity> allTopFeatures=featureMapper.getAllTopFeatures();
        for(FeatureEntity entity:allTopFeatures) {
            ArrayList<FeatureEntity> children = featureMapper.getChildrenByFatherId(entity.getFeatureId());
            entity.setAllChildren(children);
            for (FeatureEntity entity1 : children) {
                ArrayList<FeatureEntity> children2 = featureMapper.getChildrenByFatherId(entity1.getFeatureId());
                entity1.setAllChildren(children2);
            }
        }

        int index=0;
        for(FeatureEntity topFeature:allTopFeatures){
            topFeature.setFeatureDisplayId(topFeature.getProjectId()+"-"+String.format("%04d",index));
            ArrayList<FeatureEntity> data=topFeature.getAllChildren();
            for(int i=0;i<data.size();i++){
                FeatureEntity sub=data.get(i);
                sub.setFeatureDisplayId(sub.getProjectId()+"-"+String.format("%04d",index)+"-"+String.format("%03d",i));
                for(int j=0;j<sub.getAllChildren().size();j++){
                    FeatureEntity subSub=sub.getAllChildren().get(i);
                    subSub.setFeatureDisplayId(subSub.getProjectId()+"-"+String.format("%04d",index)+"-"+String.format("%03d",i)+"-"+String.format("%03d",j));
                }
            }
            index++;
        }

        return ResultGenerator.success(allTopFeatures);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<String> insertTopFeature(String featureName, String projectId,String featureDescription, HttpSession session){
        FeatureEntity entity=new FeatureEntity(0,projectId,featureName,featureDescription);
        featureMapper.insertFeatures(entity);
        return ResultGenerator.success(entity.getFatherId());
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<String> insertSubFeature(String featureName, String projectId, String fatherId,String featureDescription,HttpSession session){
        FeatureEntity fatherEntity=featureMapper.getFeatureById(fatherId);
        if(fatherEntity==null) return ResultGenerator.error("no father");
        if(fatherEntity.getFeatureLevel()==2){
            return ResultGenerator.error("can't has more child");
        }
        int myLevel=fatherEntity.getFeatureLevel()+1;
        FeatureEntity myEntity=new FeatureEntity(myLevel,fatherId,projectId,featureName,featureDescription);
        int res=featureMapper.insertFeatures(myEntity);
        if(res==0)
            return ResultGenerator.error("insert failed!");
        else
            return ResultGenerator.success(myEntity.getFeatureId());
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArrayList<FeatureEntity>> getFeaturesByProjectID(String projectID){
        ArrayList<FeatureEntity> entities=featureMapper.getFeatureByProjectId(projectID);
        if(entities==null){
            return ResultGenerator.error("no feature by this project id!");
        }else if(entities.size()==0){
            return ResultGenerator.error("no feature by this project id!");
        }else{
            FeatureEntity topEntity=new FeatureEntity();
            ArrayList<FeatureEntity> subEntities=new ArrayList<>();
            for(FeatureEntity entity:entities)
                if(entity.getFeatureLevel()==0)
                    topEntity=entity;
            for(FeatureEntity entity:entities) {
                if (entity.getFeatureLevel() == 1) {
                    subEntities.add(entity);
                    ArrayList<FeatureEntity> subSubEntities = new ArrayList<>();
                    for (FeatureEntity entity1 : entities) {
                        if (entity1.getFeatureLevel() == 2 && entity1.getFatherId().equals(entity.getFeatureId())) {
                            subSubEntities.add(entity1);
                        }
                    }
                    entity.setAllChildren(subSubEntities);
                }
            }
            topEntity.setAllChildren(subEntities);
            return ResultGenerator.success(entities);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> deleteFeatureByFeatureID(String featureId){
        featureMapper.deleteFeatureByFeatureId(featureId);
        return ResultGenerator.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> uploadFeatureList(FeatureUpLoad featureUpLoad,HttpSession session){
        Collections.sort(featureUpLoad.getData(), new Comparator<FeatureUpLoadEntity>() {
            @Override
            public int compare(FeatureUpLoadEntity o1, FeatureUpLoadEntity o2) {
                return o1.getFeatureLevel()-o2.getFeatureLevel();
            }
        });

        for(FeatureUpLoadEntity entity:featureUpLoad.getData()){
            ArrayList<FeatureEntity> temp=featureMapper.getFeatureByFeatureName(entity.getFeatureName());
            if(!(temp==null||temp.size()==0)) continue;

            if(entity.getFeatureLevel()==0){
                insertTopFeature(entity.getFeatureName(),entity.getProjectID(),entity.getFeatureDescription(),session);
            }else{
                String fatherName=entity.getFatherFeatureName();
                ArrayList<FeatureEntity> featureEntity=featureMapper.getFeatureByFeatureName(fatherName);
                if(featureEntity==null||featureEntity.size()==0) continue;
                String fatherId=featureEntity.get(0).getFeatureId();
                insertSubFeature(entity.getFeatureName(),entity.getProjectID(),fatherId,entity.getFeatureDescription(),session);
            }
        }

        return ResultGenerator.success();
    }
}
