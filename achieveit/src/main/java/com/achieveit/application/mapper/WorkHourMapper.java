package com.achieveit.application.mapper;

import com.achieveit.application.entity.WorkHourEntity;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface WorkHourMapper {
    @Insert("insert into workhour(workhourid,applytime,applyerid,approverid,featurename,activityname,starttimestamp,endtimestamp,status,projectid,applyername,approvername)"+
            " values(#{workHourId},#{applyTime},#{applyerId},#{approverId},#{featureName},#{activityName},#{startTimeStamp},#{endTimeStamp},#{status},#{projectId},#{applyerName},#{approverName})")
    Integer insertWorkHour(WorkHourEntity workHourEntity);

    @Update("update workhour set status=#{status} where workhourId=#{workHourId}")
    Integer changeWordHourStatus(@Param("workHourId") String workHourId,@Param("status") int status);

    @Update("update workhour set approverId=#{approverId},approvername=#{approverName} where workhourId=#{workHourId}")
    Integer setWorkHourApproverIdAndName(@Param("workHourId") String workHourId,@Param("approverId") String approverId,@Param("approverName")String approverName);

    @Select("select * from workhour where status=#{status}")
    ArrayList<WorkHourEntity> getWorkHoursByStatus(@Param("status")int status);

    @Select("select * from workhour where status=#{status} and projectid=#{projectId}")
    ArrayList<WorkHourEntity> getWorkHoursByStatusAndProjectId(@Param("status")int status,@Param("projectId")String projectId);

    @Select("select * from workhour")
    ArrayList<WorkHourEntity> getAllWorkHours();

    @Select("select * from workhour where applyerid=#{applyerId} and projectid=#{projectId}")
    ArrayList<WorkHourEntity> getWorkHoursByIdAndProjectID(@Param("applyerId")String applyerId,@Param("projectId")String projectId);

    @Select("select * from workhour where projectid=#{projectId}")
    ArrayList<WorkHourEntity> getWorkHoursByProjectID(@Param("projectId")String projectId);

    @Select("select * from workhour where workhourid=#{workHourId}")
    WorkHourEntity getWorkHourByID(@Param("workHourId")String workHourId);

    @Delete("delete from workhour where applyerid=#{applyerId}")
    Integer deleteWorkHoursByApplyerId(@Param("applyerId")String applyerId);

    @Delete("delete from workhour where approverid=#{approverId}")
    Integer deleteWorkHoursByApproverId(@Param("approver")String approverId);
}
