package com.achieveit.application.service;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.ProjectEntity;
import com.achieveit.application.entity.UserEntity;
import com.achieveit.application.enums.ErrorCode;
import com.achieveit.application.enums.ProjectStatus;
import com.achieveit.application.exception.AchieveitException;
import com.achieveit.application.mapper.ProjectMapper;
import com.achieveit.application.mapper.StatusMapper;
import com.achieveit.application.mapper.UserMapper;
import com.achieveit.application.utils.EmailUtil;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    private final StatusMapper statusMapper;

    private final ProjectMapper projectMapper;

    private final UserMapper userMapper;

    private final EmailUtil emailUtil;

    public StatusService(StatusMapper statusMapper, ProjectMapper projectMapper, UserMapper userMapper, EmailUtil emailUtil) {
        this.statusMapper = statusMapper;
        this.projectMapper = projectMapper;
        this.userMapper = userMapper;
        this.emailUtil = emailUtil;
    }

    @Logged({"projectID"})
    public void approveApplication(String projectID) throws AchieveitException {
        ProjectEntity project = projectMapper.getProjectByID(projectID);
        if (project == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        project.setProjectStatus(ProjectStatus.APPROVED.getStatus());
        statusMapper.updateProjectStatusByID(project);
        UserEntity projectManager = userMapper.getUserInfoById(project.getProjectManagerID());
        if (projectManager == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        // send mail
        emailUtil.sendTextEmail(projectManager.getUserMail(),
                project.getProjectID() + " " + project.getProjectName() + " 已立项",
                "申请立项已通过，项目已立项。");
    }

    @Logged({"projectID"})
    public void rejectApplication(String projectID) throws AchieveitException {
        ProjectEntity project = projectMapper.getProjectByID(projectID);
        if (project == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        project.setProjectStatus(ProjectStatus.APPROVED.getStatus());
        statusMapper.updateProjectStatusByID(project);
        UserEntity projectManager = userMapper.getUserInfoById(project.getProjectManagerID());
        if (projectManager == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        // send mail
        emailUtil.sendTextEmail(projectManager.getUserMail(),
                project.getProjectID() + " " + project.getProjectName() + " 已立项",
                "申请立项已通过，项目已立项。");
    }
}
