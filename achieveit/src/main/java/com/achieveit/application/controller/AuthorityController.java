package com.achieveit.application.controller;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.annotation.PostControl;
import com.achieveit.application.entity.*;
import com.achieveit.application.exception.AchieveitException;
import com.achieveit.application.service.AuthorityService;
import com.achieveit.application.service.ProjectService;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getAllMembersByID")
    public ResponseResult<AuthorityList> getAllMembersByID(@RequestParam("projectID") String projectID) {
        AuthorityList authorityList = authorityService.getAllMembersByID(projectID);
        return ResultGenerator.success(authorityList);
    }

    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getGitMembersByID")
    public ResponseResult<List<AuthorityEntity>> getGitMembersByID(@RequestParam("projectID") String projectID) {
        List<AuthorityEntity> gitMembers = authorityService.getGitMembersByID(projectID);
        return ResultGenerator.success(gitMembers);
    }

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

    @PostControl
    @CrossOrigin
    @Logged({"projectID", "memberID"})
    @PostMapping("/deleteGitMemberByID")
    public ResponseResult deleteGitMemberByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID) {
        authorityService.deleteGitMemberByID(projectID, memberID);
        return ResultGenerator.success();
    }

    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getMailMembersByID")
    public ResponseResult<List<AuthorityEntity>> getMailMembersByID(@RequestParam("projectID") String projectID) {
        List<AuthorityEntity> mailMembers = authorityService.getMailMembersByID(projectID);
        return ResultGenerator.success(mailMembers);
    }

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

    @PostControl
    @CrossOrigin
    @Logged({"projectID", "memberID"})
    @PostMapping("/deleteMailMemberByID")
    public ResponseResult deleteMailMemberByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID) {
        authorityService.deleteMailMemberByID(projectID, memberID);
        return ResultGenerator.success();
    }

    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getFileMembersByID")
    public ResponseResult<List<AuthorityEntity>> getFileMembersByID(@RequestParam("projectID") String projectID) {
        List<AuthorityEntity> fileMembers = authorityService.getFileMembersByID(projectID);
        return ResultGenerator.success(fileMembers);
    }

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

    @PostControl
    @CrossOrigin
    @Logged({"projectID", "memberID"})
    @PostMapping("/deleteFileMemberByID")
    public ResponseResult deleteFileMemberByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID) {
        authorityService.deleteFileMemberByID(projectID, memberID);
        return ResultGenerator.success();
    }

}
