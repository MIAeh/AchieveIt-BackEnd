package com.achieveit.application.mapper;

import com.achieveit.application.entity.ProjectEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Client Mapper
 */
@Mapper
public interface ProjectMapper {

    /**
     * 获取项目ID列表
     * @return ArrayList of ProjectIDs
     */
    @Select("SELECT * FROM projectidlist")
    ArrayList<String> getProjectIDList();

    /**
     * 获取项目列表
     * @return ArrayList of Projects
     */
    @Select("SELECT project.*, maneger.username AS projectmanegername, monitor.username AS projectmonitorname, client.clientcontactname AS projectclientcontactname, client,clientcompany AS projectclientcompany FROM project, users maneger, users monitor, client WHERE (maneger.userid = project.projectmanegerid AND monitor.userid = project.projectmonitorid AND client.clientid = project.projectclientid);")
    ArrayList<ProjectEntity> getProjectList();

    @Insert("INSERT INTO project(projectid,projectname,projectmanagerid,projectmonitorid,projectclientid,projectstatus,projectstartdate,projectenddate,projectlanguages,projectmilestones)" +
            " values(#{projectID},#{projectName},#{projectManagerID},#{projectMonitorID},#{projectClientID},#{projectStatus},#{projectStartDate},#{projectEndDate},#{projectFrameworks},#{projectLanguages},#{projectMilestones})")
    int createProjectByID(ProjectEntity projectEntity);
}
