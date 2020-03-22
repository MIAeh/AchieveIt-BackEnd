package com.achieveit.application.service;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.*;
import com.achieveit.application.mapper.ProjectMapper;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public ResponseResult<ArrayList<String>> getProjectIDList() {
        ArrayList<String> projectIDs = projectMapper.getProjectIDList();
        return ResultGenerator.success(projectIDs);
    }

    /**
     * 获取项目列表
     * @return Result
     */
    @Logged({"searchCondition", "projectStatus"})
    public ResponseResult<List<ProjectListItem>> getProjectList(String searchCondition, Integer projectStatus) {
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
        return ResultGenerator.success(projectListItemList);
    }

    @Transactional
    @Logged({"projectID", "projectName", "projectManagerID", "projectMonitorID", "projectClientID", "projectStatus",
            "projectStartDate", "projectEndDate", "projectFrameworks", "projectLanguages", "projectMilestones"})
    public ResponseResult createProjectByID(String projectID,
                                            String projectName,
                                            String projectManagerID,
                                            String projectMonitorID,
                                            String projectClientID,
                                            Integer projectStatus,
                                            Date projectStartDate,
                                            Date projectEndDate,
                                            String projectFrameworks,
                                            List<String> projectLanguages,
                                            List<Milestone> projectMilestones) {

        ProjectEntity projectEntity = new ProjectEntity(projectID, projectName, projectManagerID, projectMonitorID, projectClientID,
                projectStatus, projectStartDate, projectEndDate, projectFrameworks,
                JSONObject.toJSONString(projectLanguages), JSONObject.toJSONString(projectMilestones));
        logger.info("ProjectEntity: " + projectEntity.toString());
        projectMapper.createProjectByID(projectEntity);
        Integer defaultDomain = 0;
        projectMapper.createDomainByProjectID(projectID, defaultDomain);
        projectMapper.addMemberByID(new MemberEntity(projectID, projectManagerID, projectManagerID, "[0]"));
        projectMapper.addGitRepoByID(projectID, "null");
        return ResultGenerator.success();
    }

    @Transactional
    @Logged({"projectID"})
    public ResponseResult<ProjectInfo> getProjectByID(String projectID) {
        ProjectEntity projectEntity = projectMapper.getProjectByID(projectID);
        Integer domain = projectMapper.getDomainByProjectID(projectID);
        projectEntity.setDomain(domain);
        logger.info("ProjectEntity: " + projectEntity);
        ProjectInfo projectInfo = new ProjectInfo(projectEntity);
        logger.info("Cast to ProjectInfo: " + projectInfo);
        return ResultGenerator.success(projectInfo);
    }

    @Transactional
    @Logged({"projectID", "projectName", "projectStartDate", "projectEndDate", "projectFrameworks",
            "projectLanguages", "projectMilestones", "projectStatus"})
    public ResponseResult updateProjectByID(String projectID,
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
        return ResultGenerator.success();
    }

    @Logged({"projectID"})
    public ResponseResult<List<MemberInfo>> getMembersByID(String projectID, Integer memberRole) {
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
        return ResultGenerator.success(memberInfoList);
    }

    @Logged({"projectID", "memberID", "superiorID", "memberRole"})
    public ResponseResult addMemberByID(String projectID, String memberID, String superiorID, List<Integer> memberRoles) {
        projectMapper.addMemberByID(new MemberEntity(projectID, memberID, superiorID, JSONObject.toJSONString(memberRoles)));
        return ResultGenerator.success();
    }

    @Logged({"projectID", "memberID", "superiorID", "memberRole"})
    public ResponseResult updateMemberRoleByID(String projectID, String memberID, List<Integer> memberRoles) {
        projectMapper.updateMemberRoleByID(new MemberEntity(projectID, memberID, "", JSONObject.toJSONString(memberRoles)));
        return ResultGenerator.success();
    }

    @Logged({"projectID", "memberID", "superiorID", "memberRole"})
    public ResponseResult updateMemberSuperiorByID(String projectID, String memberID, String superiorID) {
        projectMapper.updateMemberSuperiorByID(new MemberEntity(projectID, memberID, superiorID, ""));
        return ResultGenerator.success();
    }

    @Logged({"projectID"})
    public ResponseResult<String> getGitRepoByID(String projectID) {
        String gitRepo = projectMapper.getGitRepoByID(projectID);
        return ResultGenerator.success(gitRepo);
    }

    @Logged({"projectID", "gitRepo"})
    public ResponseResult updateGitRepoByID(String projectID, String gitRepo) {
        projectMapper.updateGitRepoByID(projectID, gitRepo);
        return ResultGenerator.success();
    }
}
