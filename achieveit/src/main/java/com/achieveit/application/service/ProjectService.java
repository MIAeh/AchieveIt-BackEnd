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
        this.featureService = featureService;
        this.workHourMapper = workHourMapper;
        this.riskMapper = riskMapper;
        this.emailUtil = emailUtil;
    }

    /**
     * Logger
     */
    private final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    /**
     * 获取项目ID列表
     *
     * @return 项目ID列表
     */
    @Logged
    public List<String> getProjectIDList() {
        return projectMapper.getProjectIDList();
    }

    /**
     * 获取项目列表
     *
     * @param searchCondition 搜索条件（项目名字、客户名字、项目经理名字、项目上级名字）
     * @param projectStatus   项目状态
     * @return 项目列表
     */
    @Logged({"searchCondition", "projectStatus"})
    public List<ProjectListItem> getProjectList(String searchCondition, Integer projectStatus) {
        ArrayList<ProjectEntity> projectEntities = projectMapper.getProjectList();
        List<ProjectListItem> projectListItemList = new ArrayList<>();
        // load project list using search condition and project status
        for (ProjectEntity entity : projectEntities) {
            if (entity.isMatch(searchCondition, projectStatus)) {
                ProjectListItem projectListItem = new ProjectListItem(entity);
                projectListItemList.add(projectListItem);
                logger.info("ProjectListItem: " + projectListItem);
            }
        }
        // sort by project start date
        Collections.sort(projectListItemList);
        logger.info("ProjectInfoList: " + projectListItemList);
        return projectListItemList;
    }

    /**
     * 根据项目ID创建项目
     *
     * @param projectID         项目ID
     * @param projectName       项目名称
     * @param projectManagerID  项目经理ID
     * @param projectMonitorID  项目上级ID
     * @param projectClientID   项目客户ID
     * @param projectStartDate  项目开始日期
     * @param projectEndDate    项目结束日期
     * @param projectFrameworks 项目框架
     * @param projectLanguages  项目语言
     * @param projectMilestones 项目里程碑
     * @param domain            业务领域
     * @param featureUpLoad     功能列表
     * @param session           会话
     * @throws AchieveitException QUERY_ERROR
     */
    @Transactional
    @Logged({"projectID", "projectName", "projectManagerID", "projectMonitorID", "projectClientID", "projectStartDate", "projectEndDate", "projectFrameworks", "projectLanguages", "projectMilestones", "domain", "featureUpLoad", "session"})
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
        MemberEntity projectManager = new MemberEntity(projectID, projectManagerID, projectManagerID, "[0]");
        projectMapper.addMemberByID(projectManager);
        projectMapper.updateMemberRoleByID(projectManager);
        projectMapper.addGitRepoByID(projectID, "");
        projectMapper.deleteProjectIDFromProjectIDList(projectID);
        projectMapper.initArchive(projectID);
        projectMapper.initSubStatus(projectID);
        // add feature list
        if (featureUpLoad != null) {
            featureService.uploadFeatureList(featureUpLoad, session);
        }
//        // send mail
//        emailUtil.sendTextEmail(projectMonitor.getUserMail(), projectID + " " + projectName + " 申请立项",
//                "请进行立项审批。");
    }

    /**
     * 通过项目ID获取项目信息
     *
     * @param projectID 项目ID
     * @return 项目信息
     */
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

    /**
     * 通过项目ID更新项目信息
     *
     * @param projectID         项目ID
     * @param projectName       项目名称
     * @param projectStartDate  项目开始日期
     * @param projectEndDate    项目结束日期
     * @param projectFrameworks 项目框架
     * @param projectLanguages  项目语言
     * @param projectMilestones 项目里程碑
     * @param domain            业务领域
     * @throws AchieveitException QUERY_ERROR
     */
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
                                  Integer domain) throws AchieveitException {

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
//            // send mail
//            emailUtil.sendTextEmail(projectMonitor.getUserMail(), projectID + " " + projectName + " 申请立项",
//                    "请进行立项审批。");
        }
        ProjectEntity projectEntity = new ProjectEntity(projectID, projectName, projectStatus, projectStartDate, projectEndDate,
                projectFrameworks, JSONObject.toJSONString(projectLanguages), JSONObject.toJSONString(projectMilestones));
        logger.info("ProjectEntity: " + projectEntity.toString());
        projectMapper.updateProjectByID(projectEntity);
        projectMapper.updateDomainByProjectID(projectID, domain);
    }

    /**
     * 通过项目ID获取项目成员信息
     *
     * @param projectID  项目ID
     * @param memberRole 成员角色，用于搜索
     * @return 成员信息的List
     */
    @Logged({"projectID", "memberRole"})
    public List<MemberInfo> getMembersByID(String projectID, Integer memberRole) {
        List<MemberEntity> memberEntityList = projectMapper.getMembersByID(projectID);
        List<MemberInfo> memberInfoList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            if (memberRole == -1) {
                memberInfoList.add(new MemberInfo(memberEntity));
            } else {
                if (memberEntity.getMemberRole().contains(memberRole.toString())) {
                    memberInfoList.add(new MemberInfo(memberEntity));
                }
            }
        }
        return memberInfoList;
    }

    /**
     * 通过项目ID和用户ID添加成员
     *
     * @param projectID 项目ID
     * @param memberID  要添加的成员的ID
     */
    @Transactional
    @Logged({"projectID", "memberID"})
    public void addMemberByID(String projectID, String memberID) {
        String superiorID = projectMapper.getProjectByID(projectID).getProjectManagerID();
        projectMapper.addMemberByID(new MemberEntity(projectID, memberID, superiorID, "[]"));
    }

    /**
     * 系统级角色QA Leader通过项目ID和用户ID(s)添加成员
     *
     * @param projectID 项目ID
     * @param memberIDs 成员ID(s)
     */
    @Transactional
    @Logged({"projectID", "memberIDs"})
    public void addQAMembersByID(String projectID, List<String> memberIDs) {
        String superiorID = projectMapper.getProjectByID(projectID).getProjectManagerID();
        for (String memberID : memberIDs) {
            MemberEntity member = new MemberEntity(projectID, memberID, superiorID, "[" + MemberRoles.QA.getRole() + "]");
            projectMapper.addMemberByID(member);
            projectMapper.updateMemberRoleByID(member);
            logger.info("Add QA member: " + member.toString());
        }
        statusMapper.confirmProjectSubStatusAllocatedQAByID(projectID);
    }

    /**
     * 系统级角色EPG Leader通过项目ID和用户ID(s)添加成员
     *
     * @param projectID 项目ID
     * @param memberIDs 成员ID(s)
     */
    @Transactional
    @Logged({"projectID", "memberIDs"})
    public void addEPGMembersByID(String projectID, List<String> memberIDs) {
        String superiorID = projectMapper.getProjectByID(projectID).getProjectManagerID();
        for (String memberID : memberIDs) {
            MemberEntity member = new MemberEntity(projectID, memberID, superiorID, "[" + MemberRoles.EPG.getRole() + "]");
            projectMapper.addMemberByID(member);
            projectMapper.updateMemberRoleByID(member);
            logger.info("Add QA member: " + member.toString());
        }
        statusMapper.confirmProjectSubStatusAllocatedEPGByID(projectID);
    }

    /**
     * 添加成员角色
     *
     * @param projectID  项目ID
     * @param memberID   成员ID
     * @param memberRole 要添加的成员角色
     */
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

    /**
     * 删除成员角色
     *
     * @param projectID  项目ID
     * @param memberID   成员ID
     * @param memberRole 要删除的成员角色
     */
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

    /**
     * 更新成员上级
     *
     * @param projectID  项目ID
     * @param memberID   成员ID
     * @param superiorID 要更新的成员上级ID
     */
    @Transactional
    @Logged({"projectID", "memberID", "superiorID"})
    public void updateMemberSuperiorByID(String projectID, String memberID, String superiorID) {
        ArrayList<WorkHourEntity> workHourEntities = workHourMapper.getWorkHoursByApplyerIdAndProjectID(memberID, projectID);
        if (workHourEntities != null) {
            for (WorkHourEntity workHourEntity : workHourEntities) {
                Integer status = workHourEntity.getStatus();
                if (!status.equals(WorkHourStatus.WORK_HOUR_APPROVED.getStatus())) {
                    // reject superior update
                    throw new AchieveitException(ErrorCode.UPDATE_ERROR, "Fail to update because there is related task under processing");
                }
            }
        }
        projectMapper.updateMemberSuperiorByID(new MemberEntity(projectID, memberID, superiorID, null));
    }

    /**
     * 删除成员
     * 需要对已归档的项目进行Post控制
     *
     * @param projectID 项目ID
     * @param memberID  成员ID
     */
    @Transactional
    @Logged({"projectID", "memberID"})
    public void deleteMemberByID(String projectID, String memberID) throws AchieveitException {
        List<MemberEntity> memberEntities = projectMapper.getMembersByID(projectID);
        ProjectEntity projectEntity = projectMapper.getProjectByID(projectID);
        if (memberEntities == null || projectEntity == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        } else if (memberID.equals(projectEntity.getProjectManagerID())) {
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

        ArrayList<RiskEntity> riskEntities = riskMapper.getRisksByProjectID(projectID);
        for (RiskEntity riskEntity : riskEntities) {
            //if member is riskCharger
            if (riskEntity.getRiskCharger().equals(memberID)) {
                riskEntity.setRiskCharger(projectEntity.getProjectManagerID());
                riskEntity.setRiskChargerName(projectEntity.getProjectMonitorName());
                riskMapper.updateRiskByRiskId(riskEntity, riskEntity.getRiskID());
            }
            //if member is riskHolders
            ArrayList<String> holders = riskMapper.getAllRiskHolderByRiskId(riskEntity.getRiskID());
            for (String holder : holders) {
                if (holder.equals(memberID)) {
                    riskMapper.deleteFromRiskHolderByRiskIdAndHolderId(riskEntity.getRiskID(), holder);
                }
            }
        }
        // delete from member table
        projectMapper.deleteMemberByID(projectID, memberID);
    }

    /**
     * 通过项目ID获得Git仓库
     *
     * @param projectID 项目ID
     * @return Git仓库
     */
    @Logged({"projectID"})
    public String getGitRepoByID(String projectID) {
        return projectMapper.getGitRepoByID(projectID);
    }

    /**
     * 通过项目ID获得Git仓库
     *
     * @param projectID 项目ID
     * @param gitRepo   Git仓库
     */
    @Logged({"projectID", "gitRepo"})
    public void updateGitRepoByID(String projectID, String gitRepo) {
        projectMapper.updateGitRepoByID(projectID, gitRepo);
    }

    /**
     * 获取业务领域列表
     *
     * @return 业务领域列表
     */
    @Logged
    public List<DomainEntity> getDomainList() {
        return projectMapper.getDomainList();
    }
}
