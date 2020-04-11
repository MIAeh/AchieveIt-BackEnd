package com.achieveit.application.mapper;

import com.achieveit.application.entity.MemberEntity;
import com.achieveit.application.entity.ProjectEntity;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface AuthorityMapper {

    @Select("SELECT members.*, users.username AS membername, users.usermail AS membermail, superior.username AS superiorname FROM members, users, users superior WHERE (members.memberid = #{memberID} AND members.projectid = #{projectID} AND users.userid = members.memberid AND superior.userid = members.superiorid);")
    MemberEntity getMemberByID(String projectID, String memberID);

    @Select("SELECT members.*, users.username AS membername, users.usermail AS membermail FROM members, users, gitpermission WHERE (members.memberid = gitpermission.memberid AND users.userid = members.memberid AND members.projectid = #{projectID});")
    ArrayList<MemberEntity> getGitMembersByID(String projectID);

    @Insert("INSERT INTO gitpermission(projectid, memberid) VALUES (#{projectID}, #{memberID});")
    void addGitMemberByID(String projectID, String memberID);

    @Delete("DELETE FROM gitpermission WHERE (projectid = #{projectID} AND memberid = #{memberID});")
    void deleteGitMemberByID(String projectID, String memberID);

    @Select("SELECT members.*, users.username AS membername, users.usermail AS membermail FROM members, users, mailpermission WHERE (members.memberid = mailpermission.memberid AND users.userid = members.memberid AND members.projectid = #{projectID});")
    ArrayList<MemberEntity> getMailMembersByID(String projectID);

    @Insert("INSERT INTO mailpermission(projectid, memberid) VALUES (#{projectID}, #{memberID});")
    void addMailMemberByID(String projectID, String memberID);

    @Delete("DELETE FROM mailpermission WHERE (projectid = #{projectID} AND memberid = #{memberID});")
    void deleteMailMemberByID(String projectID, String memberID);

    @Select("SELECT members.*, users.username AS membername, users.usermail AS membermail FROM members, users, filepermission WHERE (members.memberid = filepermission.memberid AND users.userid = members.memberid AND members.projectid = #{projectID});")
    ArrayList<MemberEntity> getFileMembersByID(String projectID);

    @Insert("INSERT INTO filepermission(projectid, memberid) VALUES (#{projectID}, #{memberID});")
    void addFileMemberByID(String projectID, String memberID);

    @Delete("DELETE FROM filepermission WHERE (projectid = #{projectID} AND memberid = #{memberID});")
    void deleteFileMemberByID(String projectID, String memberID);
}
