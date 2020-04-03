package com.achieveit.application.service;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.*;
import com.achieveit.application.mapper.ProjectMapper;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ProjectService(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
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
    @Logged({"projectID", "projectName", "projectManagerID", "projectMonitorID", "projectClientID", "projectStatus",
            "projectStartDate", "projectEndDate", "projectFrameworks", "projectLanguages", "projectMilestones", "domain"})
    public void createProjectByID(String projectID,
                                            String projectName,
                                            String projectManagerID,
                                            String projectMonitorID,
                                            String projectClientID,
                                            Integer projectStatus,
                                            Date projectStartDate,
                                            Date projectEndDate,
                                            String projectFrameworks,
                                            List<String> projectLanguages,
                                            List<Milestone> projectMilestones,
                                            Integer domain) {

        ProjectEntity projectEntity = new ProjectEntity(projectID, projectName, projectManagerID, projectMonitorID, projectClientID,
                projectStatus, projectStartDate, projectEndDate, projectFrameworks,
                JSONObject.toJSONString(projectLanguages), JSONObject.toJSONString(projectMilestones));
        logger.info("ProjectEntity: " + projectEntity.toString());
        projectMapper.createProjectByID(projectEntity);
        projectMapper.createDomainByProjectID(projectID, domain);
        projectMapper.addMemberByID(new MemberEntity(projectID, projectManagerID, projectManagerID, "[0]"));
        projectMapper.addGitRepoByID(projectID, "null");
        projectMapper.deleteProjectIDFromProjectIDList(projectID);
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
            "projectLanguages", "projectMilestones", "projectStatus"})
    public void updateProjectByID(String projectID,
                                            String projectName,
                                            Date projectStartDate,
                                            Date projectEndDate,
                                            String projectFrameworks,
                                            List<String> projectLanguages,
                                            List<Milestone> projectMilestones,
                                            Integer projectStatus,
                                            Integer domain) {

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
            projectMapper.updateMemberRoleByID(new MemberEntity(projectID, memberID, "", JSONObject.toJSONString(memberRoles)));
        }
    }

    @Logged({"projectID", "memberID", "superiorID"})
    public void updateMemberSuperiorByID(String projectID, String memberID, String superiorID) {
        projectMapper.updateMemberSuperiorByID(new MemberEntity(projectID, memberID, superiorID, ""));
    }

    @Logged({"projectID"})
    public String getGitRepoByID(String projectID) {
        return projectMapper.getGitRepoByID(projectID);
    }

    @Logged({"projectID", "gitRepo"})
    public void updateGitRepoByID(String projectID, String gitRepo) {
        projectMapper.updateGitRepoByID(projectID, gitRepo);
    }
}
