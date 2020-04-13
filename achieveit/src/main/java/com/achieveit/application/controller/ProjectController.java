package com.achieveit.application.controller;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.annotation.PostControl;
import com.achieveit.application.entity.*;
import com.achieveit.application.enums.ErrorCode;
import com.achieveit.application.exception.AchieveitException;
import com.achieveit.application.service.FeatureService;
import com.achieveit.application.service.ProjectService;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService, FeatureService featureService) {
        this.projectService = projectService;
    }

    /**
     * 获取项目ID列表
     *
     * @return 项目ID列表
     */
    @CrossOrigin
    @Logged
    @GetMapping("/getProjectIDList")
    public ResponseResult<List<String>> getProjectIDList() {
        List<String> projectIDs = projectService.getProjectIDList();
        return ResultGenerator.success(projectIDs);
    }

    /**
     * 获取项目列表
     *
     * @param searchCondition 搜索条件（项目名字、客户名字、项目经理名字、项目上级名字）
     * @param projectStatus   项目状态
     * @return 项目列表
     */
    @CrossOrigin
    @Logged({"searchCondition", "projectStatus"})
    @GetMapping("/getProjectList")
    public ResponseResult<List<ProjectListItem>> getProjectList(@RequestParam("searchCondition") String searchCondition, @RequestParam("projectStatus") Integer projectStatus) {
        List<ProjectListItem> projectListItemList = projectService.getProjectList(searchCondition, projectStatus);
        return ResultGenerator.success(projectListItemList);
    }

    /**
     * 根据项目ID创建项目
     *
     * @param jsonObject 通过Body获取的JsonObject
     * @return success/error
     */
    @CrossOrigin
    @Logged({"jsonObject", "session"})
    @PostMapping("/createProjectByID")
    public ResponseResult createProjectByID(@RequestBody JSONObject jsonObject, HttpSession session) throws AchieveitException {

        String projectID = jsonObject.getString("projectID");
        String projectName = jsonObject.getString("projectName");
        String projectManagerID = (String) session.getAttribute("userId");
        if (projectManagerID == null || projectManagerID.isEmpty()) {
            throw new AchieveitException(ErrorCode.SESSION_ERROR);
        }
        String projectMonitorID = jsonObject.getString("projectMonitorID");
        String projectClientID = jsonObject.getString("projectClientID");
        Date projectStartDate = jsonObject.getDate("projectStartDate");
        Date projectEndDate = jsonObject.getDate("projectEndDate");
        String projectFrameworks = jsonObject.getString("projectFrameworks");

        List<String> projectLanguages = new ArrayList<>();
        JSONArray projectLanguagesJsonArray = jsonObject.getJSONArray("projectLanguages");
        if (projectLanguagesJsonArray != null) {
            projectLanguages = JSONObject.parseArray(jsonObject.getJSONArray("projectLanguages").toJSONString(), String.class);
        }

        List<Milestone> projectMilestones = new ArrayList<>();
        JSONArray projectMilestonesJsonArray = jsonObject.getJSONArray("projectMilestones");
        if (projectMilestonesJsonArray != null) {
            for (int i = 0; i < projectMilestonesJsonArray.size(); i++) {
                JSONObject milestoneJson = projectMilestonesJsonArray.getJSONObject(i);
                Milestone milestone = new Milestone(milestoneJson.getDate("milestoneDate"), milestoneJson.getString("milestoneContent"));
                projectMilestones.add(milestone);
            }
        }

        Integer domain = jsonObject.getInteger("domain");

        FeatureUpLoad featureUpLoad = null;
        ArrayList<FeatureUpLoadEntity> projectFunctions = new ArrayList<>();
        JSONArray projectFunctionsJsonArray = jsonObject.getJSONArray("projectFunctions");
        if (projectFunctionsJsonArray != null) {
            projectFunctions = (ArrayList<FeatureUpLoadEntity>) JSONObject.parseArray(projectFunctionsJsonArray.toJSONString(), FeatureUpLoadEntity.class);
            featureUpLoad = new FeatureUpLoad(projectFunctions);
        }

        projectService.createProjectByID(projectID, projectName, projectManagerID, projectMonitorID, projectClientID, projectStartDate, projectEndDate, projectFrameworks, projectLanguages, projectMilestones, domain, featureUpLoad, session);

        return ResultGenerator.success();
    }

    /**
     * 通过项目ID获取项目信息
     *
     * @param projectID 项目ID
     * @return 项目信息
     */
    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getProjectByID")
    public ResponseResult<ProjectInfo> getProjectByID(@RequestParam("projectID") String projectID) {
        ProjectInfo projectInfo = projectService.getProjectByID(projectID);
        return ResultGenerator.success(projectInfo);
    }

    /**
     * 通过项目ID更新项目信息
     * 需要对已归档的项目进行Post控制
     *
     * @param jsonObject 通过Body获取的JsonObject
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"jsonObject"})
    @PostMapping("/updateProjectByID")
    public ResponseResult updateProjectByID(@RequestBody JSONObject jsonObject) {

        String projectID = jsonObject.getString("projectID");
        String projectName = jsonObject.getString("projectName");
        Date projectStartDate = jsonObject.getDate("projectStartDate");
        Date projectEndDate = jsonObject.getDate("projectEndDate");
        String projectFrameworks = jsonObject.getString("projectFrameworks");

        List<String> projectLanguages = new ArrayList<>();
        JSONArray projectLanguagesJsonArray = jsonObject.getJSONArray("projectLanguages");
        if (projectLanguagesJsonArray != null) {
            projectLanguages = JSONObject.parseArray(jsonObject.getJSONArray("projectLanguages").toJSONString(), String.class);
        }

        List<Milestone> projectMilestones = new ArrayList<>();
        JSONArray projectMilestonesJsonArray = jsonObject.getJSONArray("projectMilestones");
        if (projectMilestonesJsonArray != null) {
            for (int i = 0; i < projectMilestonesJsonArray.size(); i++) {
                JSONObject milestoneJson = projectMilestonesJsonArray.getJSONObject(i);
                Milestone milestone = new Milestone(milestoneJson.getDate("milestoneDate"), milestoneJson.getString("milestoneContent"));
                projectMilestones.add(milestone);
            }
        }

        Integer domain = jsonObject.getInteger("domain");

        projectService.updateProjectByID(projectID, projectName, projectStartDate, projectEndDate, projectFrameworks, projectLanguages, projectMilestones, domain);
        return ResultGenerator.success();
    }

    /**
     * 通过项目ID获取项目成员信息
     *
     * @param projectID  项目ID
     * @param memberRole 成员角色，用于搜索
     * @return 成员信息的List
     */
    @CrossOrigin
    @Logged({"projectID", "memberRole"})
    @GetMapping("/getMembersByID")
    public ResponseResult<List<MemberInfo>> getMembersByID(@RequestParam("projectID") String projectID, @RequestParam("memberRole") Integer memberRole) {
        List<MemberInfo> memberInfos = projectService.getMembersByID(projectID, memberRole);
        return ResultGenerator.success(memberInfos);
    }

    /**
     * 通过项目ID和用户ID添加成员
     * 需要对已归档的项目进行Post控制
     *
     * @param projectID 项目ID
     * @param memberID  要添加的成员的ID
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"projectID", "memberID"})
    @PostMapping("/addMemberByID")
    public ResponseResult addMemberByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID) {
        projectService.addMemberByID(projectID, memberID);
        return ResultGenerator.success();
    }

    /**
     * 系统级角色QA Leader通过项目ID和用户ID(s)添加QA成员
     * 需要对已归档的项目进行Post控制
     *
     * @param jsonObject 包含项目ID和多个QA成员的JSON
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"jsonObject"})
    @PostMapping("/addQAMembersByID")
    public ResponseResult addQAMembersByID(@RequestBody JSONObject jsonObject) {
        String projectID = jsonObject.getString("projectID");
        List<String> memberIDs = JSONObject.parseArray(jsonObject.getJSONArray("memberIDs").toJSONString(), String.class);
        projectService.addQAMembersByID(projectID, memberIDs);
        return ResultGenerator.success();
    }

    /**
     * 系统级角色EPG Leader通过项目ID和用户ID(s)添加EPG成员
     * 需要对已归档的项目进行Post控制
     *
     * @param jsonObject 包含项目ID和多个EPG成员的JSON
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"jsonObject"})
    @PostMapping("/addEPGMembersByID")
    public ResponseResult addEPGMembersByID(@RequestBody JSONObject jsonObject) {
        String projectID = jsonObject.getString("projectID");
        List<String> memberIDs = JSONObject.parseArray(jsonObject.getJSONArray("memberIDs").toJSONString(), String.class);
        projectService.addEPGMembersByID(projectID, memberIDs);
        return ResultGenerator.success();
    }

    /**
     * 添加成员角色
     * 需要对已归档的项目进行Post控制
     *
     * @param projectID  项目ID
     * @param memberID   成员ID
     * @param memberRole 要添加的成员角色
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"projectID", "memberID", "memberRole"})
    @PostMapping("/addMemberRoleByID")
    public ResponseResult addMemberRoleByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID, @RequestParam("memberRole") Integer memberRole) {
        projectService.addMemberRoleByID(projectID, memberID, memberRole);
        return ResultGenerator.success();
    }

    /**
     * 删除成员角色
     * 需要对已归档的项目进行Post控制
     *
     * @param projectID  项目ID
     * @param memberID   成员ID
     * @param memberRole 要删除的成员角色
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"projectID", "memberID", "memberRole"})
    @PostMapping("/removeMemberRoleByID")
    public ResponseResult removeMemberRoleByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID, @RequestParam("memberRole") Integer memberRole) {
        projectService.removeMemberRoleByID(projectID, memberID, memberRole);
        return ResultGenerator.success();
    }

    /**
     * 更新成员上级
     * 需要对已归档的项目进行Post控制
     *
     * @param projectID  项目ID
     * @param memberID   成员ID
     * @param superiorID 要更新的成员上级ID
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"projectID", "memberID", "superiorID"})
    @PostMapping("/updateMemberSuperiorByID")
    public ResponseResult updateMemberSuperiorByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID, @RequestParam("superiorID") String superiorID) {
        projectService.updateMemberSuperiorByID(projectID, memberID, superiorID);
        return ResultGenerator.success();
    }

    /**
     * 删除成员
     * 需要对已归档的项目进行Post控制
     *
     * @param projectID 项目ID
     * @param memberID  成员ID
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"projectID", "memberID"})
    @PostMapping("/deleteMemberByID")
    public ResponseResult deleteMemberByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID) throws AchieveitException {
        projectService.deleteMemberByID(projectID, memberID);
        return ResultGenerator.success();
    }

    /**
     * 通过项目ID获得Git仓库
     *
     * @param projectID 项目ID
     * @return Git仓库
     */
    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getGitRepoByID")
    public ResponseResult<String> getGitRepoByID(@RequestParam("projectID") String projectID) {
        String gitRepo = projectService.getGitRepoByID(projectID);
        return ResultGenerator.success(gitRepo);
    }

    /**
     * 通过项目ID获得Git仓库
     * 需要对已归档的项目进行Post控制
     *
     * @param projectID 项目ID
     * @param gitRepo   Git仓库
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"projectID", "gitRepo"})
    @PostMapping("/updateGitRepoByID")
    public ResponseResult updateGitRepoByID(@RequestParam("projectID") String projectID, @RequestParam("gitRepo") String gitRepo) {
        projectService.updateGitRepoByID(projectID, gitRepo);
        return ResultGenerator.success();
    }

    /**
     * 获取业务领域列表
     *
     * @return 业务领域列表
     */
    @CrossOrigin
    @Logged
    @GetMapping("/getDomainList")
    public ResponseResult<List<DomainEntity>> getDomainList() {
        List<DomainEntity> domainEntities = projectService.getDomainList();
        return ResultGenerator.success(domainEntities);
    }
}
