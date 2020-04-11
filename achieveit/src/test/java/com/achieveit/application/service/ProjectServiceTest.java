package com.achieveit.application.service;

import com.achieveit.application.entity.MemberInfo;
import com.achieveit.application.entity.Milestone;
import com.achieveit.application.entity.ProjectInfo;
import com.achieveit.application.entity.ProjectListItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.achieveit.application.mapper")
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceTest.class);

    private HttpSession session;

    @Before
    public void setSession(){
        session = new MockHttpSession();
    }

    @Autowired
    private ProjectService projectService;


    @Test
    public void getProjectIDListTest() {
        List<String> projectIDs = projectService.getProjectIDList();
        Assert.assertNotNull(projectIDs);
    }

    @Test
    public void getProjectListTest() {
        List<ProjectListItem> projectList = projectService.getProjectList("project", 0);
        Assert.assertNotNull(projectList);
    }

    @Test
    public void createProjectByIDTest() throws ParseException {
        String projectID = "2018-0005-M-05";
        String projectName = "test project 1";
        String projectManagerID = "48fb8377-664f-4a9b-b13f-6729b00a9e22";
        String projectMonitorID = "b6703879-e1e2-499c-8ffe-d8b29f71f156";
        String projectClientID = "0000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date projectStartDate = dateFormat.parse("2019-11-11");
        Date projectEndDate = dateFormat.parse("2020-11-11");
        String projectFrameworks = "MVC";
        List<String> projectLanguages = new ArrayList<>();
        projectLanguages.add("C++");
        projectLanguages.add("Java");
        List<Milestone> projectMilestones = new ArrayList<>();
        projectMilestones.add(new Milestone(dateFormat.parse("2019-12-11"), "milestone 1"));
        projectMilestones.add(new Milestone(dateFormat.parse("2019-12-15"), "milestone 2"));
        projectMilestones.add(new Milestone(dateFormat.parse("2019-12-12"), "milestone 3"));
        Integer domain = 0;
        projectService.createProjectByID(projectID, projectName, projectManagerID, projectMonitorID, projectClientID, projectStartDate, projectEndDate, projectFrameworks, projectLanguages, projectMilestones, domain,null, session);
        Assert.assertTrue(true);
    }

    @Test
    public void getProjectByIDTest() {
        String projectID = "2019-0000-D-01";
        ProjectInfo projectInfo = projectService.getProjectByID(projectID);
        Assert.assertEquals(projectID, projectInfo.getProjectID());
    }

    @Test
    public void updateProjectByIDTest() throws ParseException {
        String projectID = "2018-0005-M-05";
        String projectName = "unit test project";
        Integer projectStatus = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date projectStartDate = dateFormat.parse("2019-11-11");
        Date projectEndDate = dateFormat.parse("2020-11-11");
        String projectFrameworks = "MVC";
        List<String> projectLanguages = new ArrayList<>();
        projectLanguages.add("C++");
        projectLanguages.add("Java");
        List<Milestone> projectMilestones = new ArrayList<>();
        projectMilestones.add(new Milestone(dateFormat.parse("2019-12-11"), "milestone 1"));
        projectMilestones.add(new Milestone(dateFormat.parse("2019-12-15"), "milestone 2"));
        projectMilestones.add(new Milestone(dateFormat.parse("2019-12-12"), "milestone 3"));
        Integer domain = 0;
        projectService.updateProjectByID(projectID, projectName, projectStartDate, projectEndDate, projectFrameworks, projectLanguages, projectMilestones, domain);
        Assert.assertTrue(true);
    }

    @Test
    public void getMembersByIDTest() {
        String projectID = "2018-0005-M-05";
        Integer memberRole = 0;
        List<MemberInfo> memberInfos = projectService.getMembersByID(projectID, memberRole);
        Assert.assertNotNull(memberInfos);
    }

    @Test
    public void addMemberByIDTest() {
        String projectID = "2019-0000-D-01";
        String memberID = "0005";
        projectService.addMemberByID(projectID, memberID);
        Assert.assertTrue(true);
    }

    @Test
    public void addMemberRoleByIDTest() {
        String projectID = "2019-0000-D-01";
        String memberID = "0001";
        Integer memberRole = 0;
        projectService.addMemberRoleByID(projectID, memberID, memberRole);
        Assert.assertTrue(true);
    }

    @Test
    public void removeMemberRoleByIDTest() {
        String projectID = "2019-0000-D-01";
        String memberID = "0001";
        Integer memberRole = 0;
        projectService.removeMemberRoleByID(projectID, memberID, memberRole);
        Assert.assertTrue(true);
    }

    @Test
    public void updateMemberSuperiorByIDTest() {
        String projectID = "2018-0005-M-05";
        String memberID = "0001";
        String superiorID = "48fb8377-664f-4a9b-b13f-6729b00a9e22";
        projectService.updateMemberSuperiorByID(projectID, memberID, superiorID);
        Assert.assertTrue(true);
    }

    @Test
    public void getGitRepoByID() {
        String projectID = "2019-0000-D-01";
        String gitRepo = projectService.getGitRepoByID(projectID);
        Assert.assertNotNull(gitRepo);
    }

    @Test
    public void updateGitRepoByID() {
        String projectID = "2019-0000-D-01";
        String gitRepo = "https://github.com/achieveit/project_sample";
        projectService.updateGitRepoByID(projectID, gitRepo);
        Assert.assertTrue(true);
    }
}
