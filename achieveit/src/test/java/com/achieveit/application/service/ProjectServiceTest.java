package com.achieveit.application.service;

import com.achieveit.application.entity.ProjectListItem;
import com.achieveit.application.wrapper.ResponseResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.achieveit.application.mapper")
public class ProjectServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceTest.class);

    @Autowired
    private ProjectService projectService;

    @Test
    public void getProjectIDListTest() {
        ResponseResult<ArrayList<String>> responseResult = projectService.getProjectIDList();
        LOGGER.info(responseResult.toString());
        Assert.assertNotNull(responseResult);
    }

    @Test
    public void getProjectListTest() {
        ResponseResult<List<ProjectListItem>> responseResult = projectService.getProjectList("project", 0);
        LOGGER.info(responseResult.toString());
        Assert.assertNotNull(responseResult);
    }

}
