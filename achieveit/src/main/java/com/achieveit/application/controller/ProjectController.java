package com.achieveit.application.controller;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.*;
import com.achieveit.application.service.ProjectService;
import com.achieveit.application.wrapper.ResponseResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @CrossOrigin
    @Logged
    @GetMapping("/getProjectIDList")
    public ResponseResult<ArrayList<String>> getProjectIDList() {
        return projectService.getProjectIDList();
    }

    /**
     * 获取项目列表
     * @param searchCondition 搜索条件（项目名字、客户名字、项目经理名字、项目上级名字）
     * @param projectStatus 项目状态
     * @return 项目列表
     */
    @CrossOrigin
    @Logged({"searchCondition", "projectStatus"})
    @GetMapping("/getProjectList")
    public ResponseResult<List<ProjectListItem>>  getProjectList(@RequestParam("searchCondition") String searchCondition, @RequestParam("projectStatus") Integer projectStatus) {
            return projectService.getProjectList(searchCondition, projectStatus);
    }

    /**
     * 根据项目ID创建项目
     * @param jsonObject 通过Body获取的JsonObject
     * @return Result
     */
    @CrossOrigin
    @Logged({"jsonObject"})
    @PostMapping("/createProjectByID")
    public ResponseResult createProjectByID(@RequestBody JSONObject jsonObject) {

        String projectID = jsonObject.getString("projectID");
        String projectName = jsonObject.getString("projectName");
        String projectManagerID = jsonObject.getString("projectManagerID");
        String projectMonitorID = jsonObject.getString("projectMonitorID");
        String projectClientID = jsonObject.getString("projectClientID");
        Integer projectStatus = jsonObject.getInteger("projectStatus");
        Date projectStartDate = jsonObject.getDate("projectStartDate");
        Date projectEndDate = jsonObject.getDate("projectEndDate");
        String projectFrameworks = jsonObject.getString("projectFrameworks");
        List<String> projectLanguages = JSONObject.parseArray(jsonObject.getJSONArray("projectLanguages").toJSONString(), String.class);
        List<Milestone> projectMilestones = new ArrayList<>();
        JSONArray projectMilestonesJsonArray = jsonObject.getJSONArray("projectMilestones");
        for(int i = 0; i < projectMilestonesJsonArray.size(); i++) {
            JSONObject milestoneJson = projectMilestonesJsonArray.getJSONObject(i);
            Milestone milestone = new Milestone(milestoneJson.getDate("milestoneDate"), milestoneJson.getString("milestoneContent"));
            projectMilestones.add(milestone);
        }
        Integer domain = jsonObject.getInteger("domain");

        return projectService.createProjectByID(projectID, projectName, projectManagerID, projectMonitorID, projectClientID,
                projectStatus, projectStartDate, projectEndDate, projectFrameworks, projectLanguages, projectMilestones, domain);
    }

    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getProjectByID")
    public ResponseResult<ProjectInfo> getProjectByID(@RequestParam("projectID") String projectID) {
        return projectService.getProjectByID(projectID);
    }

    @CrossOrigin
    @Logged({"jsonObject"})
    @PostMapping("/updateProjectByID")
    public ResponseResult updateProjectByID(@RequestBody JSONObject jsonObject) {

        String projectID = jsonObject.getString("projectID");
        String projectName = jsonObject.getString("projectName");
        Date projectStartDate = jsonObject.getDate("projectStartDate");
        Date projectEndDate = jsonObject.getDate("projectEndDate");
        String projectFrameworks = jsonObject.getString("projectFrameworks");
        List<String> projectLanguages = JSONObject.parseArray(jsonObject.getJSONArray("projectLanguages").toJSONString(), String.class);
        List<Milestone> projectMilestones = new ArrayList<>();
        JSONArray projectMilestonesJsonArray = jsonObject.getJSONArray("projectMilestones");
        for(int i = 0; i < projectMilestonesJsonArray.size(); i++) {
            JSONObject milestoneJson = projectMilestonesJsonArray.getJSONObject(i);
            Milestone milestone = new Milestone(milestoneJson.getDate("milestoneDate"), milestoneJson.getString("milestoneContent"));
            projectMilestones.add(milestone);
        }
        Integer projectStatus = jsonObject.getInteger("projectStatus");
        Integer domain = jsonObject.getInteger("domain");

        return projectService.updateProjectByID(projectID, projectName, projectStartDate, projectEndDate, projectFrameworks, projectLanguages, projectMilestones, projectStatus, domain);
    }

    @CrossOrigin
    @Logged({"projectID", "memberRole"})
    @GetMapping("/getMembersByID")
    public ResponseResult<List<MemberInfo>> getMembersByID(@RequestParam("projectID") String projectID, @RequestParam("memberRole") Integer memberRole) {
        return projectService.getMembersByID(projectID, memberRole);
    }

    @CrossOrigin
    @Logged({"projectID", "memberID"})
    @PostMapping("/addMemberByID")
    public ResponseResult addMemberByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID) {
        return projectService.addMemberByID(projectID, memberID);
    }

    @CrossOrigin
    @Logged({"projectID", "memberID", "memberRole"})
    @PostMapping("/addMemberRoleByID")
        public ResponseResult addMemberRoleByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID, @RequestParam("memberRole") Integer memberRole) {
        return projectService.addMemberRoleByID(projectID, memberID, memberRole);
    }

    @CrossOrigin
    @Logged({"projectID", "memberID", "memberRole"})
    @PostMapping("/removeMemberRoleByID")
    public ResponseResult removeMemberRoleByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID, @RequestParam("memberRole") Integer memberRole) {
        return projectService.removeMemberRoleByID(projectID, memberID, memberRole);
    }


    @CrossOrigin
    @Logged({"projectID", "memberID", "superiorID"})
    @PostMapping("/updateMemberSuperiorByID")
    public ResponseResult updateMemberSuperiorByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID, @RequestParam("superiorID") String superiorID) {
        return projectService.updateMemberSuperiorByID(projectID, memberID, superiorID);
    }

    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getGitRepoByID")
    public ResponseResult<String> getGitRepoByID(@RequestParam("projectID") String projectID) {
        return projectService.getGitRepoByID(projectID);
    }

    @CrossOrigin
    @Logged({"projectID", "gitRepo"})
    @PostMapping("/updateGitRepoByID")
    public ResponseResult updateGitRepoByID(@RequestParam("projectID") String projectID, @RequestParam("gitRepo") String gitRepo) {
        return projectService.updateGitRepoByID(projectID, gitRepo);
    }
}
