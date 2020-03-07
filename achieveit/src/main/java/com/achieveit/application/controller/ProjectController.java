package com.achieveit.application.controller;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.ClientInfo;
import com.achieveit.application.entity.Milestone;
import com.achieveit.application.entity.ProjectInfo;
import com.achieveit.application.service.ProjectService;
import com.achieveit.application.wrapper.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * 获取项目ID列表
     * @return 项目ID列表
     */
    @Logged
    @GetMapping("/getProjectIDList")
    public ResponseResult<ArrayList<String>> getProjectIDList() {
        return projectService.getProjectIDList();
    }

    @Logged({"searchCondition", "projectStatus"})
    @GetMapping("/getProjectList")
    public ResponseResult<ArrayList<ProjectInfo>>  getProjectList(@RequestParam("searchCondition") String searchCondition, @RequestParam("projectStatus") Integer projectStatus) {
        return projectService.getProjectList(searchCondition, projectStatus);
    }

    @Logged({"projectID", "projectName", "projectManagerID", "projectMonitorID", "projectClientID", "projectStatus",
            "projectStartDate", "projectEndDate", "projectFrameworks", "projectLanguages", "projectMilestones"})
    @GetMapping("/createProjectByID")
    public ResponseResult createProjectByID(@RequestParam("projectID") String projectID,
                                            @RequestParam("projectName") String projectName,
                                            @RequestParam("projectManagerID") String projectManagerID,
                                            @RequestParam("projectMonitorID") String projectMonitorID,
                                            @RequestParam("projectClientID") String projectClientID,
                                            @RequestParam("projectStatus") Integer projectStatus,
                                            @RequestParam("projectStartDate") Date projectStartDate,
                                            @RequestParam("projectEndDate") Date projectEndDate,
                                            @RequestParam("projectFrameworks") String projectFrameworks,
                                            @RequestParam("projectLanguages") ArrayList<String> projectLanguages,
                                            @RequestParam("projectMilestones") ArrayList<Milestone> projectMilestones) {
        return projectService.createProjectByID(projectID, projectName, projectManagerID, projectMonitorID, projectClientID,
                projectStatus, projectStartDate, projectEndDate, projectFrameworks, projectLanguages, projectMilestones);
    }


}
