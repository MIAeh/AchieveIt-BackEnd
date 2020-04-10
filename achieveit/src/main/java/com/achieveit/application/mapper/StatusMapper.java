package com.achieveit.application.mapper;

import com.achieveit.application.entity.ArchiveEntity;
import com.achieveit.application.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StatusMapper {

    @Update("UPDATE project SET projectstatus=#{projectStatus} WHERE projectid=#{projectID};")
    void updateProjectStatusByID(ProjectEntity projectEntity);

    @Select("SELECT * FROM archives WHERE projectid=#{projectID};")
    ArchiveEntity getArchiveByID(String projectID);

    @Update("UPDATE archives SET archivelink=#{archiveLink} WHERE projectid=#{projectID};")
    void updateArchiveLink(String projectID, String archiveLink);

    @Update("UPDATE archives SET archived=#{archived} WHERE projectid=#{projectID};")
    void updateArchived(String projectID, Boolean archived);
}
