package com.achieveit.application.mapper;

import com.achieveit.application.entity.RiskEntity;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface RiskMapper {
    @Insert("insert into risklist(riskdescription,risktype,riskcharger,risklevel,riskinfluence,riskstatus) values"
    +"(#{riskDescription},#{riskType},#{riskCharger},#{riskLevel},#{riskInfluence},#{riskStatus})")
    int insertRisk(RiskEntity risk);

    @Select("select * from risklist order by riskid")
    ArrayList<RiskEntity> getAllRisks();

    @Delete("delete from risklist where riskid=#{riskId}")
    int deleteRisk(@Param("riskId")int riskId);

}
