package com.achieveit.application.service;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.*;
import com.achieveit.application.mapper.AuthorityMapper;
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
public class AuthorityService {

    private final AuthorityMapper authorityMapper;

    public AuthorityService(AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }

    /**
     * Logger
     */
    private final Logger logger = LoggerFactory.getLogger(AuthorityService.class);

    @Logged({"projectID"})
    public ResponseResult<List<AuthorityEntity>> getGitMembersByID(String projectID) {
        List<MemberEntity> memberEntityList = authorityMapper.getGitMembersByID(projectID);
        List<AuthorityEntity> authorityEntities = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList) {
            authorityEntities.add(new AuthorityEntity(memberEntity));
        }
        return ResultGenerator.success(authorityEntities);
    }

    @Transactional
    @Logged({"projectID", "memberIDs"})
    public ResponseResult addGitMembersByID(String projectID, List<String> memberIDs) {
        List<AuthorityEntity> authorityEntityList = new ArrayList<>();
        for (String memberID: memberIDs) {
            authorityEntityList.add(new AuthorityEntity(authorityMapper.getGitMemberByID(projectID, memberID)));
            authorityMapper.addGitMemberByID(projectID, memberID);
        }
        // call git trigger use authorityEntityList
        return ResultGenerator.success();
    }

    @Logged({"projectID", "memberID"})
    public ResponseResult deleteGitMemberByID(String projectID, String memberID) {
        AuthorityEntity authorityEntity = new AuthorityEntity(authorityMapper.getGitMemberByID(projectID, memberID));
        authorityMapper.deleteGitMemberByID(projectID, memberID);
        // call git trigger use authorityEntity
        return ResultGenerator.success();
    }

    @Logged({"projectID"})
    public ResponseResult<List<AuthorityEntity>> getMailMembersByID(String projectID) {
        List<MemberEntity> memberEntityList = authorityMapper.getMailMembersByID(projectID);
        List<AuthorityEntity> authorityEntities = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList) {
            authorityEntities.add(new AuthorityEntity(memberEntity));
        }
        return ResultGenerator.success(authorityEntities);
    }

    @Logged({"projectID", "memberIDs"})
    public ResponseResult addMailMembersByID(String projectID, List<String> memberIDs) {
        List<AuthorityEntity> authorityEntityList = new ArrayList<>();
        for (String memberID: memberIDs) {
            authorityEntityList.add(new AuthorityEntity(authorityMapper.getMailMemberByID(projectID, memberID)));
            authorityMapper.addMailMemberByID(projectID, memberID);
        }
        // call mail trigger use authorityEntityList
        return ResultGenerator.success();
    }

    @Logged({"projectID", "memberID"})
    public ResponseResult deleteMailMemberByID(String projectID, String memberID) {
        AuthorityEntity authorityEntity = new AuthorityEntity(authorityMapper.getMailMemberByID(projectID, memberID));
        authorityMapper.deleteMailMemberByID(projectID, memberID);
        // call mail trigger use authorityEntity
        return ResultGenerator.success();
    }

    @Logged({"projectID"})
    public ResponseResult<List<AuthorityEntity>> getFileMembersByID(String projectID) {
        List<MemberEntity> memberEntityList = authorityMapper.getFileMembersByID(projectID);
        List<AuthorityEntity> authorityEntities = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList) {
            authorityEntities.add(new AuthorityEntity(memberEntity));
        }
        return ResultGenerator.success(authorityEntities);
    }

    @Logged({"projectID", "memberIDs"})
    public ResponseResult addFileMembersByID(String projectID, List<String> memberIDs) {
        List<AuthorityEntity> authorityEntityList = new ArrayList<>();
        for (String memberID: memberIDs) {
            authorityEntityList.add(new AuthorityEntity(authorityMapper.getFileMemberByID(projectID, memberID)));
            authorityMapper.addFileMemberByID(projectID, memberID);
        }
        // call file trigger use authorityEntityList
        return ResultGenerator.success();
    }

    @Logged({"projectID", "memberID"})
    public ResponseResult deleteFileMemberByID(String projectID, String memberID) {
        AuthorityEntity authorityEntity = new AuthorityEntity(authorityMapper.getFileMemberByID(projectID, memberID));
        authorityMapper.deleteFileMemberByID(projectID, memberID);
        // call file trigger use authorityEntity
        return ResultGenerator.success();
    }
}
