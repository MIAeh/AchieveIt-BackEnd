package com.achieveit.application.mapper;

import com.achieveit.application.entity.FeatureEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Insert("insert into featurelist(featureid,featurelevel,fatherid,projectid,featurename)"+
    "values(#{featureId},#{featureLevel},#{fatherId},#{projectId},#{featureName})")
    Integer insertFeatures(FeatureEntity featureEntity);
}
