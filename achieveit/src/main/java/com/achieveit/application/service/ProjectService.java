package com.achieveit.application.service;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.Milestone;
import com.achieveit.application.entity.ProjectEntity;
import com.achieveit.application.entity.ProjectInfo;
import com.achieveit.application.mapper.ProjectMapper;
import com.achieveit.application.utils.SerializeUtil;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
    public ResponseResult<ArrayList<ProjectInfo>> getProjectList(String searchCondition, Integer projectStatus) {
        ArrayList<ProjectEntity> projectEntities = projectMapper.getProjectList();
        ArrayList<ProjectInfo> projectInfos = new ArrayList<>();
        for (ProjectEntity entity : projectEntities) {
            if(entity.isMatch(searchCondition, projectStatus)) {
                projectInfos.add(new ProjectInfo(entity));
            }
        }
        return ResultGenerator.success(projectInfos);
    }

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
                                            ArrayList<String> projectLanguages,
                                            ArrayList<Milestone> projectMilestones) {

        try {
            ProjectEntity projectEntity = new ProjectEntity(projectID, projectName, projectManagerID, projectMonitorID, projectClientID,
                    projectStatus, projectStartDate, projectEndDate, projectFrameworks,
                    SerializeUtil.serialize(projectLanguages), SerializeUtil.serialize(projectMilestones));
            int affectedRows = projectMapper.createProjectByID(projectEntity);
            assert affectedRows == 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultGenerator.success();
    }

}
