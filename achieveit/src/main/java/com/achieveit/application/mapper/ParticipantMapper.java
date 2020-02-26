package com.achieveit.application.mapper;

import com.achieveit.application.entity.Participants;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ParticipantMapper {

    @Select("select \"planID\" from participants where \"participantID\"=#{participantID} and \"isDelete\"=false")
    int getPlanIDByuserID(@Param("participantID") Integer userID);

    @Select("select * from participants where \"planID\"=#{planID} and \"isDelete\"=false" )
    List<Participants> getByPlanId(Participants participants);

    @Select("select * from participants where \"planID\"=#{planID} and \"participantID\"=#{participantID} and \"isDelete\"=false")
    Participants getByPlanIdAndParticipantId(Participants participants);

    @Insert("insert into participants(\"planID\",\"participantID\",\"participantAuthorization\",\"isDelete\")values(#{planID},#{participantID},CAST(#{participantAuthorizationType} as \"enum_authorization\"),#{isDelete})")
    int insert(Participants participants);

    @Update("update participants set \"planID\"=#{planID},\"participantAuthorization\"=CAST(#{participantAuthorizationType} as \"enum_authorization\"),\"isDelete\"=#{isDelete} where \"participantID\"=#{participantID}")
    int updateParticipants(Participants participants);

}