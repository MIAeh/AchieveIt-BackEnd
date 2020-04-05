package com.achieveit.application.entity;

import com.achieveit.application.entity.FeatureUpLoadEntity;
import java.util.ArrayList;

public class FeatureUpLoad{
    private  ArrayList<FeatureUpLoadEntity> data;

    public FeatureUpLoad() {
    }

    public FeatureUpLoad(ArrayList<FeatureUpLoadEntity> data) {
        this.data = data;
    }

    public ArrayList<FeatureUpLoadEntity> getData() {
        return data;
    }

    public void setData(ArrayList<FeatureUpLoadEntity> data) {
        this.data = data;
    }
}
