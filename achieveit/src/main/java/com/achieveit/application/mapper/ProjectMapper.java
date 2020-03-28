package com.achieveit.application.mapper;

import com.achieveit.application.entity.MemberEntity;
import com.achieveit.application.entity.ProjectEntity;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Date;

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

    @Delete("DELETE FROM projectidlist WHERE projectid = #{projectID}")
    void deleteProjectIDFromProjectIDList(String projectID);

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

    @Select("SELECT members.*, users.username AS membername, users.usermail AS membermail, superior.username AS superiorname FROM members, users, users superior WHERE (members.projectid = #{projectID} AND users.userid = members.memberid AND superior.userid = members.superiorid  AND members.deleted=false);")
    ArrayList<MemberEntity> getMembersByID(String projectID);

    @Select("SELECT members.*, users.username AS membername, users.usermail AS membermail, superior.username AS superiorname FROM members, users, users superior WHERE (members.memberid = #{memberID} AND members.projectid = #{projectID} AND users.userid = members.memberid AND superior.userid = members.superiorid  AND members.deleted=false);")
    MemberEntity getMemberByID(String projectID, String memberID);

    @Insert("INSERT INTO members(projectid, memberid, superiorid, memberrole, createtime, deleted) VALUES (#{projectID}, #{memberID}, #{superiorID}, '[]', current_date, false);")
    void addMemberByID(MemberEntity memberEntity);

    @Update("UPDATE members SET memberrole=#{memberRole} WHERE (projectid=#{projectID} AND memberid=#{memberID} AND deleted=false);")
    void updateMemberRoleByID(MemberEntity memberEntity);

    @Update("UPDATE members SET superiorid=#{superiorID} WHERE (projectid=#{projectID} AND memberid=#{memberID} AND deleted=false);")
    void updateMemberSuperiorByID(MemberEntity memberEntity);

    @Update("UPDATE members SET deleted=true WHERE (projectid=#{projectID} AND memberid=#{memberID} AND deleted=false);")
    void deleteMemberByID(String projectID, String memberID);

    @Insert("INSERT INTO gitrepos(projectid, gitrepo) VALUES (#{projectID}, #{gitRepo});")
    void addGitRepoByID(String projectID, String gitRepo);

    @Update("UPDATE gitrepos SET gitrepo=#{gitRepo} WHERE (projectid = #{projectID});")
    void updateGitRepoByID(String projectID, String gitRepo);

    @Select("SELECT gitrepo FROM gitrepos WHERE (projectid = #{projectID});")
    String getGitRepoByID(String projectID);

}
