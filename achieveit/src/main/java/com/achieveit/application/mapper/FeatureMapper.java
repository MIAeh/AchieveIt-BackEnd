package com.achieveit.application.mapper;

import com.achieveit.application.entity.FeatureEntity;
import org.apache.ibatis.annotations.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Mapper for FeatureEntity
 * @author Alevery, Felix
 */

@Mapper
public interface FeatureMapper {
    @Select("select * from featurelist where featurelevel = 0")
    ArrayList<FeatureEntity> getAllTopFeatures();

    @Select("select * from featurelist where fatherid = #{fatherId}")
    ArrayList<FeatureEntity> getChildrenByFatherId(@Param("fatherId") String fatherId);

    @Select("select * from featurelist where featureid = #{featureId}")
    FeatureEntity getFeatureById(@Param("featureId") String fatherId);

    @Select("select * from featurelist where projectid = #{projectId}")
    ArrayList<FeatureEntity> getFeatureByProjectId(@Param("projectId") String projectId);

    @Select("select * from featurelist where featurename = #{featureName}")
    ArrayList<FeatureEntity> getFeatureByFeatureName(@Param("featureName") String featureName);

    @Insert("insert into featurelist(featureid,featurelevel,fatherid,projectid,featurename,featuredescription,createtime)"+
    "values(#{featureId},#{featureLevel},#{fatherId},#{projectId},#{featureName},#{featureDescription},#{createTime})")
    Integer insertFeatures(FeatureEntity featureEntity);

    @Delete("delete from featurelist where featureid=#{featureId}")
    Integer deleteFeatureByFeatureId(@Param("featureId")String featureId);
}
