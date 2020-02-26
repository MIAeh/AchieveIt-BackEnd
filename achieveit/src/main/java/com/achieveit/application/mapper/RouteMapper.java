package com.achieveit.application.mapper;

import com.achieveit.application.entity.Route;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RouteMapper {
    @Select("select * from route where origin=#{origin} and destination=#{destination} and \"isDelete\"=false")
    Route getRouteByOriginAndDestination(Route route);

    @Select("select * from route where \"planID\"=#{planID} and \"isDelete\"=false")
    List<Route> getRoutesByPlanId(Route route);

    @Insert("insert into route(\"planID\",\"origin\",\"destination\",\"routeTransportation\",\"routeTime\",\"routeIsChosen\",\"isDelete\") values(#{planID},#{origin},#{destination},CAST(#{routeTransportation} as \"enum_transportation\"),#{routeTime},#{routeIsChosen},#{isDelete})")
    int insert(Route route);

    @Update("update route set \"planID\"=#{planID},\"origin\"=#{origin},\"destination\"=#{destination},\"routeTransportation\"=CAST(#{routeTransportation} as \"enum_transportation\"),\"routeTime\"=#{routeTime},\"routeIsChosen\"=#{routeIsChosen},\"isDelete\"=#{isDelete}")
    int updateByRouteId(Route route);


}
