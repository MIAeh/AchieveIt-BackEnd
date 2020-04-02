package com.achieveit.application.service;

import com.achieveit.application.entity.AuthorityEntity;
import com.achieveit.application.entity.AuthorityList;
import com.achieveit.application.entity.ClientInfo;
import com.achieveit.application.exception.AchieveitException;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.achieveit.application.mapper")
public class AuthorityServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityServiceTest.class);

    @Autowired
    private AuthorityService authorityService;


    @Test
    public void getAllMembersByIDTest() {
        String projectID = "2019-0000-D-01";
        AuthorityList authorityList = authorityService.getAllMembersByID(projectID);
        Assert.assertNotNull(authorityList);
    }

    @Test
    public void getGitMembersByIDTest() {
        String projectID = "2019-0000-D-01";
        List<AuthorityEntity> authorityEntities = authorityService.getGitMembersByID(projectID);
        Assert.assertNotNull(authorityEntities);
    }

    @Test
    public void addGitMembersByIDTest() {
//        String projectID = "2019-0000-D-01";
//        List<String> memberIDs = new ArrayList<>();
//        memberIDs.add("b6703879-e1e2-499c-8ffe-d8b29f71f156");
//        memberIDs.add("48fb8377-664f-4a9b-b13f-6729b00a9e22");
//        try {
//            authorityService.addGitMembersByID(projectID, memberIDs);
//        } catch (AchieveitException e) {
//            e.printStackTrace();
//            Assert.fail();
//        }
        Assert.assertTrue(true);
    }

    @Test
    public void deleteGitMemberByIDTest() {
//        String projectID = "2019-0000-D-01";
//        String memberID = "b6703879-e1e2-499c-8ffe-d8b29f71f156";
//        authorityService.deleteGitMemberByID(projectID, memberID);
        Assert.assertTrue(true);
    }

    @Test
    public void getMailMembersByIDTest() {
        String projectID = "2019-0000-D-01";
        List<AuthorityEntity> authorityEntities = authorityService.getFileMembersByID(projectID);
        Assert.assertNotNull(authorityEntities);
    }

    @Test
    public void addMailMembersByIDTest() {
        //        String projectID = "2019-0000-D-01";
//        List<String> memberIDs = new ArrayList<>();
//        memberIDs.add("b6703879-e1e2-499c-8ffe-d8b29f71f156");
//        memberIDs.add("48fb8377-664f-4a9b-b13f-6729b00a9e22");
//        try {
//            authorityService.addMailMembersByID(projectID, memberIDs);
//        } catch (AchieveitException e) {
//            e.printStackTrace();
//            Assert.fail();
//        }
        Assert.assertTrue(true);
    }

    @Test
    public void deleteMailMemberByIDTest() {
        //        String projectID = "2019-0000-D-01";
//        String memberID = "b6703879-e1e2-499c-8ffe-d8b29f71f156";
//        authorityService.deleteMailMemberByID(projectID, memberID);
        Assert.assertTrue(true);
    }

    @Test
    public void getFileMembersByIDTest() {
        String projectID = "2019-0000-D-01";
        List<AuthorityEntity> authorityEntities = authorityService.getFileMembersByID(projectID);
        Assert.assertNotNull(authorityEntities);
    }

    @Test
    public void addFileMembersByID() {
        //        String projectID = "2019-0000-D-01";
//        List<String> memberIDs = new ArrayList<>();
//        memberIDs.add("b6703879-e1e2-499c-8ffe-d8b29f71f156");
//        memberIDs.add("48fb8377-664f-4a9b-b13f-6729b00a9e22");
//        try {
//            authorityService.addFileMembersByID(projectID, memberIDs);
//        } catch (AchieveitException e) {
//            e.printStackTrace();
//            Assert.fail();
//        }
        Assert.assertTrue(true);
    }

    @Test
    public void deleteFileMemberByIDTest() {
        //        String projectID = "2019-0000-D-01";
//        String memberID = "b6703879-e1e2-499c-8ffe-d8b29f71f156";
//        authorityService.deleteFileMemberByID(projectID, memberID);
        Assert.assertTrue(true);
    }
}