package com.achieveit.application.mapper;

import com.achieveit.application.entity.RiskEntity;
import com.achieveit.application.entity.RiskTemplate;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.sql.Date;

@Mapper
public interface RiskMapper {
    @Insert("insert into risklist(riskdescription,risktype,riskcharger,riskchargername,risklevel,riskinfluence,riskfrequency,riskstrategy,riskstatus,projectid) values"
    +"(#{riskDescription},#{riskType},#{riskCharger},#{riskChargerName},#{riskLevel},#{riskInfluence},#{riskFrequency},#{riskStrategy},#{riskStatus},#{projectID})")
    int insertRisk(RiskEntity risk);

    @Update("update risklist set riskdescription=#{riskEntity.riskDescription},risktype=#{riskEntity.riskType},riskcharger=#{riskEntity.riskCharger},riskchargername=#{riskEntity.riskChargerName}" +
            ",risklevel=#{riskEntity.riskLevel},riskinfluence=#{riskEntity.riskInfluence},riskfrequency=#{riskEntity.riskFrequency},riskstrategy=#{riskEntity.riskStrategy} where riskid=#{riskId}")
    int updateRiskByRiskId(RiskEntity riskEntity,@Param("riskId")int riskId);

    @Select("select * from risklist order by riskid")
    ArrayList<RiskEntity> getAllRisks();

    @Delete("delete from risklist where riskid=#{riskId}")
    int deleteRiskByRiskId(@Param("riskId")int riskId);

    @Update("update risklist set riskstatus=#{riskStatus} where riskid=#{riskId}")
    int changeRiskStatus(@Param("riskId")int riskId,@Param("riskStatus")int riskStatus);

    @Select("select * from risklist where riskid=#{riskId}")
    RiskEntity getRiskByRiskId(@Param("riskId")int riskId);

    @Insert("insert into riskholders(riskid,riskholder) values(#{riskId},#{riskHolder})")
    int addRiskHolder(@Param("riskId")int riskId,@Param("riskHolder")String riskHolder);

    @Select("select riskholder from riskholders where riskid=#{riskId}")
    ArrayList<String> getAllRiskHolderByRiskId(@Param("riskId")int riskId);

    @Delete("delete from riskholders where riskid=#{riskId}")
    int deleteAllRiskHoldersByRiskId(@Param("riskId")int riskId);

    @Select("select * from risktemplates")
    ArrayList<RiskTemplate> getRiskTemplates();

    @Insert("insert into risktemplates(riskdescription,risktype,risklevel,riskinfluence,riskstrategy) values" +
            "(#{riskDescription},#{riskType},#{riskLevel},#{riskInfluence},#{riskStrategy})")
    int addRiskTemplate(RiskTemplate riskTemplate);

    @Select("select risklastsendtime from risklist where riskid=#{riskId}")
    Date getRiskLastSendTimeFromRiskId(@Param("riskId")int riskId);

    @Update("update risklist set risklastsendtime=#{riskLastSendTime} where riskid=#{riskId}")
    int updateRiskLastSendTime(@Param("riskId")int riskId,@Param("riskLastSendTime")Date riskLastSendTime);

    @Select("select * from risklist where projectid=#{projectID}")
    ArrayList<RiskEntity> getRisksByProjectID(@Param("projectID")String projectID);

    @Delete("delete from riskholders where riskid=#{riskId} and riskholder=#{riskHolder}")
    int deleteFromRiskHolderByRiskIdAndHolderId(@Param("riskId")String riskId,@Param("riskHolder")String riskHolder);

    @Select("select MAX(riskid) from risklist")
    int getMaxRiskIdFromRiskList();
}
