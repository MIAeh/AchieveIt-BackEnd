package com.achieveit.application.service;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.*;
import com.achieveit.application.enums.ErrorCode;
import com.achieveit.application.enums.MemberRoles;
import com.achieveit.application.enums.ProjectStatus;
import com.achieveit.application.enums.WorkHourStatus;
import com.achieveit.application.exception.AchieveitException;
import com.achieveit.application.mapper.*;
import com.achieveit.application.utils.EmailUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Client Service
 */
@Service
public class ProjectService {

    private final ProjectMapper projectMapper;

    private final UserMapper userMapper;

    private final AuthorityMapper authorityMapper;

    private final WorkHourMapper workHourMapper;

    private final RiskMapper riskMapper;

    private final StatusMapper statusMapper;

    private final FeatureService featureService;

    private final EmailUtil emailUtil;

    public ProjectService(ProjectMapper projectMapper, UserMapper userMapper,
                          AuthorityMapper authorityMapper, StatusMapper statusMapper, FeatureService featureService,
                          WorkHourMapper workHourMapper, RiskMapper riskMapper, EmailUtil emailUtil) {
        this.projectMapper = projectMapper;
        this.userMapper = userMapper;
        this.authorityMapper = authorityMapper;
        this.statusMapper = statusMapper;
        this.featureService=featureService;
        this.workHourMapper=workHourMapper;
        this.riskMapper=riskMapper;
        this.emailUtil = emailUtil;
    }

    /**
     * Logger
     */
    private final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    /**
     * 获取项目ID列表
     * @return Result
     */
    @Logged
    public List<String> getProjectIDList() {
        return projectMapper.getProjectIDList();
    }

    /**
     * 获取项目列表
     * @return Result
     */
    @Logged({"searchCondition", "projectStatus"})
    public List<ProjectListItem> getProjectList(String searchCondition, Integer projectStatus) {
        ArrayList<ProjectEntity> projectEntities = projectMapper.getProjectList();
        List<ProjectListItem> projectListItemList = new ArrayList<>();
        for (ProjectEntity entity : projectEntities) {
            if(entity.isMatch(searchCondition, projectStatus)) {
                ProjectListItem projectListItem = new ProjectListItem(entity);
                projectListItemList.add(projectListItem);
                logger.info("ProjectListItem: " + projectListItem);
            }
        }
        logger.info("ProjectInfoList: " + projectListItemList);
        return projectListItemList;
    }

    @Transactional
    @Logged({"projectID", "projectName", "projectManagerID", "projectMonitorID", "projectClientID", "projectStartDate", "projectEndDate", "projectFrameworks", "projectLanguages", "projectMilestones", "domain"})
    public void createProjectByID(String projectID,
                                  String projectName,
                                  String projectManagerID,
                                  String projectMonitorID,
                                  String projectClientID,
                                  Date projectStartDate,
                                  Date projectEndDate,
                                  String projectFrameworks,
                                  List<String> projectLanguages,
                                  List<Milestone> projectMilestones,
                                  Integer domain,
                                  FeatureUpLoad featureUpLoad,
                                  HttpSession session) throws AchieveitException {

        UserEntity projectMonitor = userMapper.getUserInfoById(projectMonitorID);
        if (projectMonitor == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }

        ProjectEntity projectEntity = new ProjectEntity(projectID, projectName, projectManagerID, projectMonitorID, projectClientID,
                ProjectStatus.APPLY_FOR_APPROVAL.getStatus(), projectStartDate, projectEndDate, projectFrameworks,
                JSONObject.toJSONString(projectLanguages), JSONObject.toJSONString(projectMilestones));
        logger.info("ProjectEntity: " + projectEntity.toString());
        projectMapper.createProjectByID(projectEntity);
        projectMapper.createDomainByProjectID(projectID, domain);
        // add project manager
        projectMapper.addMemberByID(new MemberEntity(projectID, projectManagerID, projectManagerID, "[0]"));
        projectMapper.addGitRepoByID(projectID, "");
        projectMapper.deleteProjectIDFromProjectIDList(projectID);
        projectMapper.initArchive(projectID);
        projectMapper.initSubStatus(projectID);
        // add feature list
        if (featureUpLoad != null) {
            featureService.uploadFeatureList(featureUpLoad,session);
        }
        // send mail
//        emailUtil.sendTextEmail(projectMonitor.getUserMail(), projectID + " " + projectName + " 申请立项",
//                "请进行立项审批。");
    }

    @Transactional
    @Logged({"projectID"})
    public ProjectInfo getProjectByID(String projectID) {
        ProjectEntity projectEntity = projectMapper.getProjectByID(projectID);
        Integer domain = projectMapper.getDomainByProjectID(projectID);
        projectEntity.setDomain(domain);
        logger.info("ProjectEntity: " + projectEntity);
        ProjectInfo projectInfo = new ProjectInfo(projectEntity);
        logger.info("Cast to ProjectInfo: " + projectInfo);
        return projectInfo;
    }

    @Transactional
    @Logged({"projectID", "projectName", "projectStartDate", "projectEndDate", "projectFrameworks",
            "projectLanguages", "projectMilestones"})
    public void updateProjectByID(String projectID,
                                            String projectName,
                                            Date projectStartDate,
                                            Date projectEndDate,
                                            String projectFrameworks,
                                            List<String> projectLanguages,
                                            List<Milestone> projectMilestones,
                                            Integer domain) {

        ProjectEntity project = projectMapper.getProjectByID(projectID);
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
            emailUtil.sendTextEmail(projectMonitor.getUserMail(), projectID + " " + projectName + " 申请立项",
                    "请进行立项审批。");
        }
        ProjectEntity projectEntity = new ProjectEntity(projectID, projectName, projectStatus, projectStartDate, projectEndDate,
                projectFrameworks, JSONObject.toJSONString(projectLanguages), JSONObject.toJSONString(projectMilestones));
        logger.info("ProjectEntity: " + projectEntity.toString());
        projectMapper.updateProjectByID(projectEntity);
        projectMapper.updateDomainByProjectID(projectID, domain);
    }

    @Logged({"projectID"})
    public List<MemberInfo> getMembersByID(String projectID, Integer memberRole) {
        List<MemberEntity> memberEntityList = projectMapper.getMembersByID(projectID);
        List<MemberInfo> memberInfoList = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList) {
            if (memberRole == -1) {
                memberInfoList.add(new MemberInfo(memberEntity));
            }
            else {
                if (memberEntity.getMemberRole().contains(memberRole.toString())) {
                    memberInfoList.add(new MemberInfo(memberEntity));
                }
            }
        }
        return memberInfoList;
    }

    @Transactional
    @Logged({"projectID", "memberID"})
    public void addMemberByID(String projectID, String memberID) {
        String superiorID = projectMapper.getProjectByID(projectID).getProjectManagerID();
        projectMapper.addMemberByID(new MemberEntity(projectID, memberID, superiorID, "[]"));
    }

    @Transactional
    @Logged({"projectID", "memberIDs"})
    public void addQAMembersByID(String projectID, List<String> memberIDs) {
        String superiorID = projectMapper.getProjectByID(projectID).getProjectManagerID();
        for (String memberID : memberIDs) {
            projectMapper.addMemberByID(new MemberEntity(projectID, memberID, superiorID, "[" + MemberRoles.QA.getRole().toString() + "]"));
        }
        statusMapper.confirmProjectSubStatusAllocatedQAByID(projectID);
    }

    @Transactional
    @Logged({"projectID", "memberIDs"})
    public void addEPGMembersByID(String projectID, List<String> memberIDs) {
        String superiorID = projectMapper.getProjectByID(projectID).getProjectManagerID();
        for (String memberID : memberIDs) {
            projectMapper.addMemberByID(new MemberEntity(projectID, memberID, superiorID, "[" + MemberRoles.EPG.getRole().toString() + "]"));
        }
        statusMapper.confirmProjectSubStatusAllocatedEPGByID(projectID);
    }

    @Transactional
    @Logged({"projectID", "memberID", "memberRole"})
    public void addMemberRoleByID(String projectID, String memberID, Integer memberRole) {
        MemberEntity memberEntity = projectMapper.getMemberByID(projectID, memberID);
        List<Integer> memberRoles = JSONObject.parseArray(memberEntity.getMemberRole(), Integer.class);
        if (!memberRoles.contains(memberRole)) {
            memberRoles.add(memberRole);
            Collections.sort(memberRoles);
            projectMapper.updateMemberRoleByID(new MemberEntity(projectID, memberID, "", JSONObject.toJSONString(memberRoles)));
        }
    }

    @Transactional
    @Logged({"projectID", "memberID", "memberRole"})
    public void removeMemberRoleByID(String projectID, String memberID, Integer memberRole) {
        MemberEntity memberEntity = projectMapper.getMemberByID(projectID, memberID);
        List<Integer> memberRoles = JSONObject.parseArray(memberEntity.getMemberRole(), Integer.class);
        if (memberRoles.contains(memberRole)) {
            memberRoles.remove(memberRole);
            projectMapper.updateMemberRoleByID(new MemberEntity(projectID, memberID, null, JSONObject.toJSONString(memberRoles)));
        }
    }

    @Transactional
    @Logged({"projectID", "memberID", "superiorID"})
    public void updateMemberSuperiorByID(String projectID, String memberID, String superiorID) {
        ArrayList<WorkHourEntity> workHourEntities = workHourMapper.getWorkHoursByApplyerIdAndProjectID(memberID, projectID);
        if (workHourEntities != null) {
            for (WorkHourEntity workHourEntity : workHourEntities) {
                Integer status = workHourEntity.getStatus();
                if (!status.equals(WorkHourStatus.WORK_HOUR_APPROVED.getStatus())) {
                    // reject superior update
                    throw new AchieveitException(ErrorCode.UPDATE_ERROR);
                }
            }
        }
        projectMapper.updateMemberSuperiorByID(new MemberEntity(projectID, memberID, superiorID, null));
    }

    @Transactional
    @Logged({"projectID", "memberID"})
    public void deleteMemberByID(String projectID, String memberID) throws AchieveitException {
        List<MemberEntity> memberEntities = projectMapper.getMembersByID(projectID);
        ProjectEntity projectEntity = projectMapper.getProjectByID(projectID);
        if (memberEntities == null || projectEntity == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        else if (memberID.equals(projectEntity.getProjectManagerID())) {
            throw new AchieveitException(ErrorCode.DELETION_ERROR);
        }
        // update current superior (to be deleted) with project manager
        for (MemberEntity memberEntity : memberEntities) {
            if (memberID.equals(memberEntity.getSuperiorID())) {
                projectMapper.updateMemberSuperiorByID(new MemberEntity(projectID, memberID, projectEntity.getProjectManagerID(), null));
            }
        }
        // delete all authorities
        authorityMapper.deleteGitMemberByID(projectID, memberID);
        authorityMapper.deleteMailMemberByID(projectID, memberID);
        authorityMapper.deleteFileMemberByID(projectID, memberID);

        //delete From workHour and Risks
        workHourMapper.deleteWorkHoursByApplyerId(memberID);

        ArrayList<RiskEntity> riskEntities=riskMapper.getRisksByProjectID(projectID);
        for(RiskEntity riskEntity:riskEntities){
            //if member is riskCharger
            if(riskEntity.getRiskCharger().equals(memberID)){
                riskEntity.setRiskCharger(projectEntity.getProjectManagerID());
                riskEntity.setRiskChargerName(projectEntity.getProjectMonitorName());
                riskMapper.updateRiskByRiskId(riskEntity,riskEntity.getRiskID());
            }
            //if member is riskHolders
            ArrayList<String> holders=riskMapper.getAllRiskHolderByRiskId(riskEntity.getRiskID());
            for(String holder:holders){
                if(holder.equals(memberID)){
                    riskMapper.deleteFromRiskHolderByRiskIdAndHolderId(String.valueOf(riskEntity.getRiskID()),holder);
                }
            }
        }
        // delete from member table
        projectMapper.deleteMemberByID(projectID, memberID);
    }

    @Logged({"projectID"})
    public String getGitRepoByID(String projectID) {
        return projectMapper.getGitRepoByID(projectID);
    }

    @Logged({"projectID", "gitRepo"})
    public void updateGitRepoByID(String projectID, String gitRepo) {
        projectMapper.updateGitRepoByID(projectID, gitRepo);
    }

    @Logged
    public List<DomainEntity> getDomainList() {
        return projectMapper.getDomainList();
    }
}
