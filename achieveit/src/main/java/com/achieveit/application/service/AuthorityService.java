package com.achieveit.application.service;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.*;
import com.achieveit.application.mapper.AuthorityMapper;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public ResponseResult<AuthorityList> getAllMembersByID(String projectID) {
        List<MemberEntity> gitMemberEntityList = authorityMapper.getGitMembersByID(projectID);
        List<AuthorityEntity> gitAuthorityEntities = new ArrayList<>();
        for (MemberEntity memberEntity: gitMemberEntityList) {
            gitAuthorityEntities.add(new AuthorityEntity(memberEntity));
        }
        List<MemberEntity> mailMemberEntityList = authorityMapper.getMailMembersByID(projectID);
        List<AuthorityEntity> mailAuthorityEntities = new ArrayList<>();
        for (MemberEntity memberEntity: mailMemberEntityList) {
            mailAuthorityEntities.add(new AuthorityEntity(memberEntity));
        }
        List<MemberEntity> fileMemberEntityList = authorityMapper.getMailMembersByID(projectID);
        List<AuthorityEntity> fileAuthorityEntities = new ArrayList<>();
        for (MemberEntity memberEntity: fileMemberEntityList) {
            fileAuthorityEntities.add(new AuthorityEntity(memberEntity));
        }
        AuthorityList authorityList = new AuthorityList(gitAuthorityEntities, mailAuthorityEntities, fileAuthorityEntities);
        return ResultGenerator.success(authorityList);
    }

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
        logger.info("projectID: " + projectID);
        logger.info("memberIDs: " + memberIDs.toString());
        List<AuthorityEntity> authorityEntityList = new ArrayList<>();
        for (String memberID: memberIDs) {
            MemberEntity memberEntity = authorityMapper.getMemberByID(projectID, memberID);
            if (memberEntity == null) {
                return ResultGenerator.error("Null member.");
            }
            logger.info("Get Member By Project ID " + projectID + " and Member ID " + memberID + ": " + memberEntity.toString());
            AuthorityEntity authorityEntity = new AuthorityEntity(memberEntity);
            logger.info("Authority Entity: " + authorityEntity.toString());
            authorityEntityList.add(authorityEntity);
            authorityMapper.addGitMemberByID(projectID, memberID);
            logger.info("Added member By Project ID " + projectID + " and Member ID " + memberID);
        }
        // call git trigger use authorityEntityList
        logger.info("Authority Entity List: " + authorityEntityList.toString());
        return ResultGenerator.success();
    }

    @Logged({"projectID", "memberID"})
    public ResponseResult deleteGitMemberByID(String projectID, String memberID) {
        AuthorityEntity authorityEntity = new AuthorityEntity(authorityMapper.getMemberByID(projectID, memberID));
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

    @Transactional
    @Logged({"projectID", "memberIDs"})
    public ResponseResult addMailMembersByID(String projectID, List<String> memberIDs) {
        logger.info("projectID: " + projectID);
        logger.info("memberIDs: " + memberIDs.toString());
        List<AuthorityEntity> authorityEntityList = new ArrayList<>();
        for (String memberID: memberIDs) {
            MemberEntity memberEntity = authorityMapper.getMemberByID(projectID, memberID);
            if (memberEntity == null) {
                return ResultGenerator.error("Null member.");
            }
            logger.info("Get Member By Project ID " + projectID + " and Member ID " + memberID + ": " + memberEntity.toString());
            AuthorityEntity authorityEntity = new AuthorityEntity(memberEntity);
            logger.info("Authority Entity: " + authorityEntity.toString());
            authorityEntityList.add(authorityEntity);
            authorityMapper.addMailMemberByID(projectID, memberID);
            logger.info("Added member By Project ID " + projectID + " and Member ID " + memberID);
        }
        // call mail trigger use authorityEntityList
        logger.info("Authority Entity List: " + authorityEntityList.toString());
        return ResultGenerator.success();
    }

    @Logged({"projectID", "memberID"})
    public ResponseResult deleteMailMemberByID(String projectID, String memberID) {
        AuthorityEntity authorityEntity = new AuthorityEntity(authorityMapper.getMemberByID(projectID, memberID));
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

    @Transactional
    @Logged({"projectID", "memberIDs"})
    public ResponseResult addFileMembersByID(String projectID, List<String> memberIDs) {
        logger.info("projectID: " + projectID);
        logger.info("memberIDs: " + memberIDs.toString());
        List<AuthorityEntity> authorityEntityList = new ArrayList<>();
        for (String memberID: memberIDs) {
            MemberEntity memberEntity = authorityMapper.getMemberByID(projectID, memberID);
            if (memberEntity == null) {
                return ResultGenerator.error("Null member.");
            }
            logger.info("Get Member By Project ID " + projectID + " and Member ID " + memberID + ": " + memberEntity.toString());
            AuthorityEntity authorityEntity = new AuthorityEntity(memberEntity);
            logger.info("Authority Entity: " + authorityEntity.toString());
            authorityEntityList.add(authorityEntity);
            authorityMapper.addFileMemberByID(projectID, memberID);
            logger.info("Added member By Project ID " + projectID + " and Member ID " + memberID);
        }
        // call file trigger use authorityEntityList
        logger.info("Authority Entity List: " + authorityEntityList.toString());
        return ResultGenerator.success();
    }

    @Logged({"projectID", "memberID"})
    public ResponseResult deleteFileMemberByID(String projectID, String memberID) {
        AuthorityEntity authorityEntity = new AuthorityEntity(authorityMapper.getMemberByID(projectID, memberID));
        authorityMapper.deleteFileMemberByID(projectID, memberID);
        // call file trigger use authorityEntity
        return ResultGenerator.success();
    }
}
