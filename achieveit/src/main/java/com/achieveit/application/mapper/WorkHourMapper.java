package com.achieveit.application.mapper;

import com.achieveit.application.entity.WorkHourEntity;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface WorkHourMapper {
    @Insert("insert into workhour(workhourid,applytime,applyerid,approverid,featurename,activityname,starttime,endtime,status)"+
            " values(#{workHourId},#{applyTime},#{applyerId},#{approverId},#{featureName},#{activityName},#{startTime},#{endTime},#{status}) ")
    int insertWorkHour(WorkHourEntity workHourEntity);

    @Update("update workhour set status=#{status} where workhourId=#{workHourId}")
    int changeWordHourStatus(@Param("wordHourId") String workHourId,@Param("status") int status);

    @Update("update workhour set approverId=#{approverId} where workhourId=#{workHourId}")
    int setWorkHourApproverId(@Param("wordHourId") String workHourId,@Param("approverId") String approverId);

    @Select("select * from workhour where status=#{status}")
    ArrayList<WorkHourEntity> getWorkHoursByStatus(@Param("status")int status);


}
