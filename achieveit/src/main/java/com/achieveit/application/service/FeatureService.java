package com.achieveit.application.service;

import com.achieveit.application.entity.FeatureEntity;
import com.achieveit.application.entity.FeatureUpLoad;
import com.achieveit.application.entity.FeatureUpLoadEntity;
import com.achieveit.application.exception.AchieveitException;
import com.achieveit.application.mapper.FeatureMapper;
import com.achieveit.application.mapper.ProjectMapper;
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
    private final ProjectMapper projectMapper;

    /**
     * Logger
     */
    private final Logger logger = LoggerFactory.getLogger(FeatureService.class);

    private String getFeatureId(FeatureEntity entity){
        if(entity.getProjectId()==null||entity.getProjectId().equals("")){
            throw new AchieveitException("projectId is null!");
        }
        if(projectMapper.getProjectByID(entity.getProjectId())==null){
            throw new AchieveitException("projectId is inValid!");
        }

        int level=entity.getFeatureLevel();
        if(level==0){
            String featureId=entity.getProjectId();
            int index=featureMapper.getFeatureSizeByLevelAndProjectId(entity.getProjectId(),0);
            featureId+="-";
            featureId+= String.format("%04d",index+1);
            return featureId;
        }else if(level==1||level==2){
            FeatureEntity fatherEntity=featureMapper.getFeatureById(entity.getFatherId());
            if(fatherEntity==null) return null;
            String fatherFeatureId=fatherEntity.getFeatureId();
            ArrayList<FeatureEntity> entities=featureMapper.getChildrenByFatherId(fatherFeatureId);
            int thisIndex=entities.size()+1;
            String featureId=fatherFeatureId;
            featureId+="-";
            featureId+=String.format("%03d",thisIndex);
            return featureId;
        }
        return null;
    }

    @Autowired
    public FeatureService(FeatureMapper featureMapper,ProjectMapper projectMapper) {
        this.featureMapper=featureMapper;
        this.projectMapper=projectMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArrayList<FeatureEntity>> getAllTopFeatures(HttpSession session){
        ArrayList<FeatureEntity> entities=featureMapper.getAllTopFeatures();
        addFatherNameFromFeatures(entities);
        return ResultGenerator.success(entities);
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

        return ResultGenerator.success(allTopFeatures);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<FeatureEntity> insertTopFeature(String featureName, String projectId,String featureDescription, HttpSession session){
        FeatureEntity entity=new FeatureEntity(0,projectId,featureName,featureDescription);
        entity.setFeatureId(getFeatureId(entity));
        featureMapper.insertFeatures(entity);
        return ResultGenerator.success(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<FeatureEntity> insertSubFeature(String featureName, String projectId, String fatherId,String featureDescription,HttpSession session){
        FeatureEntity fatherEntity=featureMapper.getFeatureById(fatherId);
        if(fatherEntity==null) return ResultGenerator.error("no father");
        if(fatherEntity.getFeatureLevel()==2){
            return ResultGenerator.error("can't has more child");
        }
        int myLevel=fatherEntity.getFeatureLevel()+1;
        FeatureEntity myEntity=new FeatureEntity(myLevel,fatherId,projectId,featureName,featureDescription);
        myEntity.setFeatureId(getFeatureId(myEntity));

        int res=featureMapper.insertFeatures(myEntity);
        if(res==0)
            return ResultGenerator.error("insert failed!");
        else
            return ResultGenerator.success(myEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<ArrayList<FeatureEntity>> getFeaturesByProjectID(String projectID){
        ArrayList<FeatureEntity> entities=featureMapper.getFeatureByProjectId(projectID);
        addFatherNameFromFeatures(entities);

        if(entities.size()==0){
            return ResultGenerator.success("no feature by this project id!");
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
            if(topEntity!=null)
                topEntity.setAllChildren(subEntities);
            return ResultGenerator.success(entities);
        }
    }

    private void addFatherNameFromFeatures(ArrayList<FeatureEntity> entities) {
        for(FeatureEntity entity:entities){
            if(entity.getFeatureLevel()>0) {
                if(entity.getFatherId()!=null&&!entity.getFatherId().equals("")){
                    FeatureEntity fatherEntity=featureMapper.getFeatureById(entity.getFatherId());
                    if(fatherEntity==null) continue;
                    entity.setFatherName(fatherEntity.getFatherName());
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> deleteFeatureByFeatureID(String featureId){
        FeatureEntity thisFeature=featureMapper.getFeatureById(featureId);
        if(thisFeature==null)
            return ResultGenerator.error("invalid feature!");

        if(thisFeature.getFeatureLevel()==2){
            featureMapper.deleteFeatureByFeatureId(featureId);
            return ResultGenerator.success();
        }

        ArrayList<String> allSubFeatures=new ArrayList<>();
        ArrayList<FeatureEntity> subFeatures=featureMapper.getChildrenByFatherId(featureId);
        for(FeatureEntity entity:subFeatures){
            allSubFeatures.add(entity.getFeatureId());
            if(entity.getFeatureLevel()==1) {
                ArrayList<FeatureEntity> subSubFeatures = featureMapper.getChildrenByFatherId(entity.getFeatureId());
                for (FeatureEntity subEntity : subSubFeatures) {
                    allSubFeatures.add(subEntity.getFeatureId());
                }
            }
        }
        for(String id:allSubFeatures){
            featureMapper.deleteFeatureByFeatureId(id);
        }
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
            //如果已经有了就不做处理
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
