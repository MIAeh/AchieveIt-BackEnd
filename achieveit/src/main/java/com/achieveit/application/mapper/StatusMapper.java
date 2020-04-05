package com.achieveit.application.mapper;

import com.achieveit.application.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StatusMapper {

    @Update("UPDATE project SET projectstatus=#{projectStatus} WHERE projectid=#{projectID};")
    void updateProjectStatusByID(ProjectEntity projectEntity);

}
