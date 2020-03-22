package com.achieveit.application.mapper;

import com.achieveit.application.entity.MemberEntity;
import com.achieveit.application.entity.ProjectEntity;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * Client Mapper
 */
@Mapper
public interface AuthorityMapper {

    @Select("SELECT members.* FROM members, gitpermission WHERE (members.projectid = #{projectID} AND members.deleted=false);")
    ArrayList<MemberEntity> getGitMembersByID(String projectID);

    @Select("SELECT members.* FROM members, gitpermission WHERE (members.projectid = #{projectID} AND members.memberid = #{memberID} AND members.deleted=false);")
    MemberEntity getGitMemberByID(String projectID, String memberID);

    @Insert("INSERT INTO gitpermission(projectid, memberid) VALUES (#{projectID}, #{memberID});")
    void addGitMemberByID(String projectID, String memberID);

    @Delete("DELETE FROM gitpermission WHERE (projectid = #{projectID}, memberid = #{memberID})")
    void deleteGitMemberByID(String projectID, String memberID);

    @Select("SELECT members.* FROM members, mailpermission WHERE (members.projectid = #{projectID} AND members.deleted=false);")
    ArrayList<MemberEntity> getMailMembersByID(String projectID);

    @Select("SELECT members.* FROM members, mailpermission WHERE (members.projectid = #{projectID} AND members.memberid = #{memberID} AND members.deleted=false);")
    MemberEntity getMailMemberByID(String projectID, String memberID);

    @Insert("INSERT INTO mailpermission(projectid, memberid) VALUES (#{projectID}, #{memberID});")
    void addMailMemberByID(String projectID, String memberID);

    @Delete("DELETE FROM mailpermission WHERE (projectid = #{projectID}, memberid = #{memberID})")
    void deleteMailMemberByID(String projectID, String memberID);

    @Select("SELECT members.* FROM members, filepermission WHERE (members.projectid = #{projectID} AND members.deleted=false);")
    ArrayList<MemberEntity> getFileMembersByID(String projectID);

    @Select("SELECT members.* FROM members, mailpermission WHERE (members.projectid = #{projectID} AND members.memberid = #{memberID} AND members.deleted=false);")
    MemberEntity getFileMemberByID(String projectID, String memberID);

    @Insert("INSERT INTO filepermission(projectid, memberid) VALUES (#{projectID}, #{memberID});")
    void addFileMemberByID(String projectID, String memberID);

    @Delete("DELETE FROM filepermission WHERE (projectid = #{projectID}, memberid = #{memberID})")
    void deleteFileMemberByID(String projectID, String memberID);
}
