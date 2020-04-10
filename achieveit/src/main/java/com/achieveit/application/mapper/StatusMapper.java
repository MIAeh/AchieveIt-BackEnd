package com.achieveit.application.mapper;

import com.achieveit.application.entity.ArchiveEntity;
import com.achieveit.application.entity.ProjectEntity;
import com.achieveit.application.entity.ProjectSubStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StatusMapper {

    @Update("UPDATE project SET projectstatus=#{projectStatus} WHERE projectid=#{projectID};")
    void updateProjectStatusByID(ProjectEntity projectEntity);

    @Select("SELECT * FROM archives WHERE projectid=#{projectID};")
    ArchiveEntity getArchiveByID(String projectID);

    @Select("SELECT * FROM projectsubstatus WHERE projectid=#{projectID};")
    ProjectSubStatus getProjectSubStatusByID(String projectID);

    @Update("UPDATE projectsubstatus SET configurationcompleted=true WHERE projectid=#{projectID};")
    void confirmProjectSubStatusConfigurationCompletedByID(String projectID);

    @Update("UPDATE projectsubstatus SET configurationcompleted=true WHERE projectid=#{projectID};")
    void updateProjectSubStatusConfigurationCompletedByID(String projectID);

    @Update("UPDATE archives SET archivelink=#{archiveLink} WHERE projectid=#{projectID};")
    void updateArchiveLink(String projectID, String archiveLink);

    @Update("UPDATE archives SET archived=#{archived} WHERE projectid=#{projectID};")
    void updateArchived(String projectID, Boolean archived);
}
