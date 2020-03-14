package com.achieveit.application.mapper;

import com.achieveit.application.entity.ProjectEntity;
import org.apache.ibatis.annotations.*;

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
    @Select("SELECT project.*, manager.username AS projectmanagername, monitor.username AS projectmonitorname, client.clientcontactname AS projectclientcontactname, client,clientcompany AS projectclientcompany FROM project, users manager, users monitor, client WHERE (manager.userid = project.projectmanagerid AND monitor.userid = project.projectmonitorid AND client.clientid = project.projectclientid);")
    ArrayList<ProjectEntity> getProjectList();

    @Insert("INSERT INTO project(projectid,projectname,projectmanagerid,projectmonitorid,projectclientid,projectstatus,projectstartdate,projectenddate,projectframeworks,projectlanguages,projectmilestones)" +
            " values(#{projectID},#{projectName},#{projectManagerID},#{projectMonitorID},#{projectClientID},#{projectStatus},#{projectStartDate},#{projectEndDate},#{projectFrameworks},#{projectLanguages},#{projectMilestones})")
    void createProjectByID(ProjectEntity projectEntity);

    @Select("SELECT project.*, manager.username AS projectmanagername, monitor.username AS projectmonitorname, client.clientcontactname AS projectclientcontactname, client,clientcompany AS projectclientcompany FROM project, users manager, users monitor, client WHERE (manager.userid = project.projectmanagerid AND monitor.userid = project.projectmonitorid AND client.clientid = project.projectclientid AND project.projectid = #{projectID});")
    ProjectEntity getProjectByID(String projectID);

    @Insert("INSERT INTO domain(projectid, domain) values(#{projectID}, #{domain});")
    void createDomainByProjectID(String projectID, Integer domain);

    @Select("SELECT domain FROM domain WHERE (projectid = #{projectID});")
    Integer getDomainByProjectID(String projectID);

    @Update("UPDATE domain SET domain=#{domain} WHERE projectid=#{projectID};")
    void updateDomainByProjectID(String projectID, Integer domain);

    @Update("UPDATE project SET projectid=#{projectID}, projectname=#{projectName}, projectstatus=#{projectStatus}, projectstartdate=#{projectStartDate}, projectenddate=#{projectEndDate}, projectframeworks=#{projectFrameworks}, projectlanguages=#{projectLanguages}, projectmilestones=#{projectMilestones}")
    void updateProjectByID(ProjectEntity projectEntity);
}
