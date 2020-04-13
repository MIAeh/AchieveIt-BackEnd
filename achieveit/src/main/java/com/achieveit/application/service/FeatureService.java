package com.achieveit.application.service;

import com.achieveit.application.entity.*;
import com.achieveit.application.enums.ErrorCode;
import com.achieveit.application.enums.ProjectStatus;
import com.achieveit.application.exception.AchieveitException;
import com.achieveit.application.mapper.*;
import com.achieveit.application.utils.EmailUtil;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
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
    private final UserMapper userMapper;
    private final EmailUtil emailUtil;
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
    public FeatureService(FeatureMapper featureMapper, ProjectMapper projectMapper, UserMapper userMapper, EmailUtil emailUtil) {
        this.featureMapper=featureMapper;
        this.projectMapper=projectMapper;
        this.userMapper = userMapper;
        this.emailUtil = emailUtil;
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

        ProjectEntity project = projectMapper.getProjectByID(projectId);
        if (project == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        // status update
        Integer projectStatus = project.getProjectStatus();
        if (projectStatus.equals(ProjectStatus.REJECTED.getStatus())) {
            projectStatus = ProjectStatus.APPLY_FOR_APPROVAL.getStatus();
            UserEntity projectMonitor = userMapper.getUserInfoById(project.getProjectMonitorID());
            if (projectMonitor == null) {
                throw new AchieveitException(ErrorCode.QUERY_ERROR);
            }
            // send mail
            emailUtil.sendTextEmail(projectMonitor.getUserMail(), project.getProjectID() + " " + project.getProjectName() + " 申请立项",
                    "请进行立项审批。");
        }

        FeatureEntity entity=new FeatureEntity(0,projectId,featureName,featureDescription);
        entity.setFeatureId(getFeatureId(entity));
        featureMapper.insertFeatures(entity);
        return ResultGenerator.success(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<FeatureEntity> insertSubFeature(String featureName, String projectId, String fatherId,String featureDescription,HttpSession session){

        ProjectEntity project = projectMapper.getProjectByID(projectId);
        if (project == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        // status update
        Integer projectStatus = project.getProjectStatus();
        if (projectStatus.equals(ProjectStatus.REJECTED.getStatus())) {
            projectStatus = ProjectStatus.APPLY_FOR_APPROVAL.getStatus();
            UserEntity projectMonitor = userMapper.getUserInfoById(project.getProjectMonitorID());
            if (projectMonitor == null) {
                throw new AchieveitException(ErrorCode.QUERY_ERROR);
            }
            // send mail
            emailUtil.sendTextEmail(projectMonitor.getUserMail(), project.getProjectID() + " " + project.getProjectName() + " 申请立项",
                    "请进行立项审批。");
        }

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
        ArrayList<FeatureEntity> allEntities=featureMapper.getFeatureByProjectId(projectID);
        addFatherNameFromFeatures(allEntities);

        if(allEntities.size()==0){
            return ResultGenerator.success("no feature by this project id!");
        }

        ArrayList<FeatureEntity> topEntities=new ArrayList<>();
        for(FeatureEntity entity:allEntities){
            if(entity.getFeatureLevel()==0)
                topEntities.add(entity);
        }

        for(FeatureEntity topEntity:topEntities){
            ArrayList<FeatureEntity> subEntities=new ArrayList<>();
            for(FeatureEntity entity:allEntities){
                if(entity.getFeatureLevel()==1&&entity.getFatherId().equals(topEntity.getFeatureId())) {
                    ArrayList<FeatureEntity> subSubEntities=new ArrayList<>();
                    for(FeatureEntity entity1:allEntities){
                        if(entity1.getFeatureLevel()==2&&entity1.getFatherId().equals(entity.getFeatureId())){
                            subSubEntities.add(entity1);
                        }
                    }
                    entity.setAllChildren(subSubEntities);
                    subEntities.add(entity);
                }
            }
            topEntity.setAllChildren(subEntities);
        }

        return ResultGenerator.success(topEntities);
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
    public ResponseResult<Boolean> deleteFeatureByFeatureID(String featureId, String projectId){

        ProjectEntity project = projectMapper.getProjectByID(projectId);
        if (project == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        // status update
        Integer projectStatus = project.getProjectStatus();
        if (projectStatus.equals(ProjectStatus.REJECTED.getStatus())) {
            projectStatus = ProjectStatus.APPLY_FOR_APPROVAL.getStatus();
            UserEntity projectMonitor = userMapper.getUserInfoById(project.getProjectMonitorID());
            if (projectMonitor == null) {
                throw new AchieveitException(ErrorCode.QUERY_ERROR);
            }
            // send mail
            emailUtil.sendTextEmail(projectMonitor.getUserMail(), project.getProjectID() + " " + project.getProjectName() + " 申请立项",
                    "请进行立项审批。");
        }

        FeatureEntity thisFeature=featureMapper.getFeatureById(featureId);
        if(thisFeature==null)
            return ResultGenerator.error("invalid feature!");

        if(thisFeature.getFeatureLevel()==2){
            featureMapper.deleteFeatureByFeatureId(featureId);
            return ResultGenerator.success();
        }

        ArrayList<String> allSubFeatures=new ArrayList<>();
        allSubFeatures.add(featureId);
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

    public ResponseResult<FeatureEntity> updateFeatureByFeatureID(String featureId,String featureName,String featureDescription, String projectId){

        ProjectEntity project = projectMapper.getProjectByID(projectId);
        if (project == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        // status update
        Integer projectStatus = project.getProjectStatus();
        if (projectStatus.equals(ProjectStatus.REJECTED.getStatus())) {
            projectStatus = ProjectStatus.APPLY_FOR_APPROVAL.getStatus();
            UserEntity projectMonitor = userMapper.getUserInfoById(project.getProjectMonitorID());
            if (projectMonitor == null) {
                throw new AchieveitException(ErrorCode.QUERY_ERROR);
            }
            // send mail
            emailUtil.sendTextEmail(projectMonitor.getUserMail(), project.getProjectID() + " " + project.getProjectName() + " 申请立项",
                    "请进行立项审批。");
        }

        FeatureEntity featureEntity=featureMapper.getFeatureById(featureId);
        if(featureEntity==null)
            return ResultGenerator.error("invalid featureId!");
        //logger.info("featureid:"+featureEntity.getFeatureId());
        if(featureName!=null&&!featureName.equals("")){
            featureEntity.setFeatureName(featureName);
        }
        if(featureDescription!=null&&!featureDescription.equals("")){
            featureEntity.setFeatureDescription(featureDescription);
        }
        featureMapper.updateFeature(featureEntity);
        return ResultGenerator.success(featureEntity);
    }

}
