package com.achieveit.application.controller;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.*;
import com.achieveit.application.service.AuthorityService;
import com.achieveit.application.service.ProjectService;
import com.achieveit.application.wrapper.ResponseResult;
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
        return authorityService.getAllMembersByID(projectID);
    }

    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getGitMembersByID")
    public ResponseResult<List<AuthorityEntity>> getGitMembersByID(@RequestParam("projectID") String projectID) {
        return authorityService.getGitMembersByID(projectID);
    }

    @CrossOrigin
    @Logged({"jsonObject"})
    @PostMapping("/addGitMembersByID")
    public ResponseResult addGitMembersByID(@RequestBody JSONObject jsonObject) {
        String projectID = jsonObject.getString("projectID");
        List<String> memberIDs = JSONObject.parseArray(jsonObject.getJSONArray("memberIDs").toJSONString(), String.class);
        return authorityService.addGitMembersByID(projectID, memberIDs);
    }

    @CrossOrigin
    @Logged({"projectID", "memberID"})
    @PostMapping("/deleteGitMemberByID")
    public ResponseResult deleteGitMemberByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID) {
        return authorityService.deleteGitMemberByID(projectID, memberID);
    }

    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getMailMembersByID")
    public ResponseResult<List<AuthorityEntity>> getMailMembersByID(@RequestParam("projectID") String projectID) {
        return authorityService.getMailMembersByID(projectID);
    }

    @CrossOrigin
    @Logged({"jsonObject"})
    @PostMapping("/addMailMembersByID")
    public ResponseResult addMailMembersByID(@RequestBody JSONObject jsonObject) {
        String projectID = jsonObject.getString("projectID");
        List<String> memberIDs = JSONObject.parseArray(jsonObject.getJSONArray("memberIDs").toJSONString(), String.class);
        return authorityService.addMailMembersByID(projectID, memberIDs);
    }

    @CrossOrigin
    @Logged({"projectID", "memberID"})
    @PostMapping("/deleteMailMemberByID")
    public ResponseResult deleteMailMemberByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID) {
        return authorityService.deleteMailMemberByID(projectID, memberID);
    }

    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getFileMembersByID")
    public ResponseResult<List<AuthorityEntity>> getFileMembersByID(@RequestParam("projectID") String projectID) {
        return authorityService.getFileMembersByID(projectID);
    }

    @CrossOrigin
    @Logged({"jsonObject"})
    @PostMapping("/addFileMembersByID")
    public ResponseResult addFileMembersByID(@RequestBody JSONObject jsonObject) {
        String projectID = jsonObject.getString("projectID");
        List<String> memberIDs = JSONObject.parseArray(jsonObject.getJSONArray("memberIDs").toJSONString(), String.class);
        return authorityService.addFileMembersByID(projectID, memberIDs);
    }

    @CrossOrigin
    @Logged({"projectID", "memberID"})
    @PostMapping("/deleteFileMemberByID")
    public ResponseResult deleteFileMemberByID(@RequestParam("projectID") String projectID, @RequestParam("memberID") String memberID) {
        return authorityService.deleteFileMemberByID(projectID, memberID);
    }

}
