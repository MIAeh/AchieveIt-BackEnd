package com.achieveit.application.controller;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.annotation.PostControl;
import com.achieveit.application.entity.AuthorityEntity;
import com.achieveit.application.entity.AuthorityList;
import com.achieveit.application.exception.AchieveitException;
import com.achieveit.application.service.AuthorityService;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    /**
     * 通过项目ID获取所有成员的Git/Mail/File权限
     *
     * @param projectID 项目ID
     * @return 该项目的所有拥有Git/Mail/File权限的成员
     */
    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getAllMembersByID")
    public ResponseResult<AuthorityList> getAllMembersByID(@RequestParam("projectID") String projectID) {
        AuthorityList authorityList = authorityService.getAllMembersByID(projectID);
        return ResultGenerator.success(authorityList);
    }

    /**
     * 通过项目ID获取所有成员的Git权限
     *
     * @param projectID 项目ID
     * @return 该项目的所有拥有Git权限的成员
     */
    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getGitMembersByID")
    public ResponseResult<List<AuthorityEntity>> getGitMembersByID(@RequestParam("projectID") String projectID) {
        List<AuthorityEntity> gitMembers = authorityService.getGitMembersByID(projectID);
        return ResultGenerator.success(gitMembers);
    }

    /**
     * 添加（多个）拥有Git权限的项目成员
     * 需要对已归档的项目进行Post控制
     *
     * @param jsonObject 含有项目ID和要添加的成员ID(s)的JSON
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"jsonObject"})
    @PostMapping("/addGitMembersByID")
    public ResponseResult addGitMembersByID(@RequestBody JSONObject jsonObject) {
        String projectID = jsonObject.getString("projectID");
        List<String> memberIDs = JSONObject.parseArray(jsonObject.getJSONArray("memberIDs").toJSONString(), String.class);
        try {
            authorityService.addGitMembersByID(projectID, memberIDs);
        } catch (AchieveitException e) {
            e.printStackTrace();
            return ResultGenerator.error(e.getErrorCode());
        }
        return ResultGenerator.success();
    }

    /**
     * 删除项目成员的Git权限
     * 需要对已归档的项目进行Post控制
     *
     * @param projectID 项目ID
     * @param memberID  项目成员ID
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"projectID", "memberID"})
    @PostMapping("/deleteGitMemberByID")
    public ResponseResult deleteGitMemberByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID) {
        authorityService.deleteGitMemberByID(projectID, memberID);
        return ResultGenerator.success();
    }

    /**
     * 通过项目ID获取所有成员的Mail权限
     *
     * @param projectID 项目ID
     * @return 该项目的所有拥有Git权限的成员
     */
    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getMailMembersByID")
    public ResponseResult<List<AuthorityEntity>> getMailMembersByID(@RequestParam("projectID") String projectID) {
        List<AuthorityEntity> mailMembers = authorityService.getMailMembersByID(projectID);
        return ResultGenerator.success(mailMembers);
    }

    /**
     * 添加（多个）拥有Mail权限的项目成员
     * 需要对已归档的项目进行Post控制
     *
     * @param jsonObject 含有项目ID和要添加的成员ID(s)的JSON
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"jsonObject"})
    @PostMapping("/addMailMembersByID")
    public ResponseResult addMailMembersByID(@RequestBody JSONObject jsonObject) {
        String projectID = jsonObject.getString("projectID");
        List<String> memberIDs = JSONObject.parseArray(jsonObject.getJSONArray("memberIDs").toJSONString(), String.class);
        try {
            authorityService.addMailMembersByID(projectID, memberIDs);
        } catch (AchieveitException e) {
            e.printStackTrace();
            return ResultGenerator.error(e.getErrorCode());
        }
        return ResultGenerator.success();
    }

    /**
     * 删除项目成员的Mail权限
     * 需要对已归档的项目进行Post控制
     *
     * @param projectID 项目ID
     * @param memberID  项目成员ID
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"projectID", "memberID"})
    @PostMapping("/deleteMailMemberByID")
    public ResponseResult deleteMailMemberByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID) {
        authorityService.deleteMailMemberByID(projectID, memberID);
        return ResultGenerator.success();
    }

    /**
     * 通过项目ID获取所有成员的File权限
     *
     * @param projectID 项目ID
     * @return 该项目的所有拥有Git权限的成员
     */
    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getFileMembersByID")
    public ResponseResult<List<AuthorityEntity>> getFileMembersByID(@RequestParam("projectID") String projectID) {
        List<AuthorityEntity> fileMembers = authorityService.getFileMembersByID(projectID);
        return ResultGenerator.success(fileMembers);
    }

    /**
     * 添加（多个）拥有File权限的项目成员
     * 需要对已归档的项目进行Post控制
     *
     * @param jsonObject 含有项目ID和要添加的成员ID(s)的JSON
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"jsonObject"})
    @PostMapping("/addFileMembersByID")
    public ResponseResult addFileMembersByID(@RequestBody JSONObject jsonObject) {
        String projectID = jsonObject.getString("projectID");
        List<String> memberIDs = JSONObject.parseArray(jsonObject.getJSONArray("memberIDs").toJSONString(), String.class);
        try {
            authorityService.addFileMembersByID(projectID, memberIDs);
        } catch (AchieveitException e) {
            e.printStackTrace();
            return ResultGenerator.error(e.getErrorCode());
        }
        return ResultGenerator.success();
    }

    /**
     * 删除项目成员的File权限
     * 需要对已归档的项目进行Post控制
     *
     * @param projectID 项目ID
     * @param memberID  项目成员ID
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"projectID", "memberID"})
    @PostMapping("/deleteFileMemberByID")
    public ResponseResult deleteFileMemberByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID) {
        authorityService.deleteFileMemberByID(projectID, memberID);
        return ResultGenerator.success();
    }

}
