package com.achieveit.application.service;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.MemberEntity;
import com.achieveit.application.entity.ProjectEntity;
import com.achieveit.application.entity.UserEntity;
import com.achieveit.application.enums.ErrorCode;
import com.achieveit.application.enums.ProjectStatus;
import com.achieveit.application.exception.AchieveitException;
import com.achieveit.application.mapper.AuthorityMapper;
import com.achieveit.application.mapper.ProjectMapper;
import com.achieveit.application.mapper.StatusMapper;
import com.achieveit.application.mapper.UserMapper;
import com.achieveit.application.utils.EmailUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    private final StatusMapper statusMapper;

    private final ProjectMapper projectMapper;

    private final UserMapper userMapper;

    private final AuthorityMapper authorityMapper;

    private final EmailUtil emailUtil;

    public StatusService(StatusMapper statusMapper, ProjectMapper projectMapper, UserMapper userMapper, AuthorityMapper authorityMapper, EmailUtil emailUtil) {
        this.statusMapper = statusMapper;
        this.projectMapper = projectMapper;
        this.userMapper = userMapper;
        this.authorityMapper = authorityMapper;
        this.emailUtil = emailUtil;
    }

    @Logged({"projectID"})
    public void approveApplication(String projectID) throws AchieveitException {
        ProjectEntity project = projectMapper.getProjectByID(projectID);
        if (project == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        else if (!project.getProjectStatus().equals(ProjectStatus.APPLY_FOR_APPROVAL.getStatus())) {
            throw new AchieveitException(ErrorCode.STATUS_ERROR);
        }

        // update status
        project.setProjectStatus(ProjectStatus.APPROVED.getStatus());
        statusMapper.updateProjectStatusByID(project);

        // send mail
        UserEntity projectManager = userMapper.getUserInfoById(project.getProjectManagerID());
        if (projectManager == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
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
        else if (!project.getProjectStatus().equals(ProjectStatus.APPLY_FOR_APPROVAL.getStatus())) {
            throw new AchieveitException(ErrorCode.STATUS_ERROR);
        }

        // update status
        project.setProjectStatus(ProjectStatus.REJECTED.getStatus());
        statusMapper.updateProjectStatusByID(project);

        // send mail
        UserEntity projectManager = userMapper.getUserInfoById(project.getProjectManagerID());
        if (projectManager == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        emailUtil.sendTextEmail(projectManager.getUserMail(),
                project.getProjectID() + " " + project.getProjectName() + " 立项驳回",
                "申请立项被驳回，项目立项驳回。");
    }

    @Logged({"projectID"})
    public void launchProject(String projectID) throws AchieveitException {
        ProjectEntity project = projectMapper.getProjectByID(projectID);
        if (project == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        else if (!project.getProjectStatus().equals(ProjectStatus.APPROVED.getStatus())) {
            throw new AchieveitException(ErrorCode.STATUS_ERROR);
        }

        // update status
        project.setProjectStatus(ProjectStatus.IN_PROGRESS.getStatus());
        statusMapper.updateProjectStatusByID(project);

        // send mail
        List<MemberEntity> projectMembers = authorityMapper.getMailMembersByID(projectID);
        if (projectMembers != null) {
            for (MemberEntity member: projectMembers) {
                emailUtil.sendTextEmail(member.getMemberMail(),
                        project.getProjectID() + " " + project.getProjectName() + " 进行中",
                        "配置完成已启动，项目进行中。");
            }
        }
    }

    @Logged({"projectID"})
    public void deliverProject(String projectID) throws AchieveitException {
        ProjectEntity project = projectMapper.getProjectByID(projectID);
        if (project == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        else if (!project.getProjectStatus().equals(ProjectStatus.IN_PROGRESS.getStatus())) {
            throw new AchieveitException(ErrorCode.STATUS_ERROR);
        }

        // update status
        project.setProjectStatus(ProjectStatus.DELIVERED.getStatus());
        statusMapper.updateProjectStatusByID(project);

        // send mail
        List<MemberEntity> projectMembers = authorityMapper.getMailMembersByID(projectID);
        if (projectMembers != null) {
            for (MemberEntity member: projectMembers) {
                emailUtil.sendTextEmail(member.getMemberMail(),
                        project.getProjectID() + " " + project.getProjectName() + " 已交付",
                        "确认交付，项目已交付。");
            }
        }
    }

    @Logged({"projectID"})
    public void endProject(String projectID) throws AchieveitException {
        ProjectEntity project = projectMapper.getProjectByID(projectID);
        if (project == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        else if (!project.getProjectStatus().equals(ProjectStatus.DELIVERED.getStatus())) {
            throw new AchieveitException(ErrorCode.STATUS_ERROR);
        }

        // update status
        project.setProjectStatus(ProjectStatus.ENDED.getStatus());
        statusMapper.updateProjectStatusByID(project);

        // send mail
        List<MemberEntity> projectMembers = authorityMapper.getMailMembersByID(projectID);
        if (projectMembers != null) {
            for (MemberEntity member: projectMembers) {
                emailUtil.sendTextEmail(member.getMemberMail(),
                        project.getProjectID() + " " + project.getProjectName() + " 已结束",
                        "确认结束，项目已结束。");
            }
        }
    }

    @Logged({"projectID"})
    public void approveArchive(String projectID) throws AchieveitException {
        ProjectEntity project = projectMapper.getProjectByID(projectID);
        if (project == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        else if (!project.getProjectStatus().equals(ProjectStatus.ENDED.getStatus())) {
            throw new AchieveitException(ErrorCode.STATUS_ERROR);
        }

        // update status
        project.setProjectStatus(ProjectStatus.ARCHIVED.getStatus());
        statusMapper.updateProjectStatusByID(project);

        // send mail
        List<MemberEntity> projectMembers = authorityMapper.getMailMembersByID(projectID);
        if (projectMembers != null) {
            for (MemberEntity member: projectMembers) {
                emailUtil.sendTextEmail(member.getMemberMail(),
                        project.getProjectID() + " " + project.getProjectName() + " 已归档",
                        "归档审批通过，项目已归档。");
            }
        }
    }
}
