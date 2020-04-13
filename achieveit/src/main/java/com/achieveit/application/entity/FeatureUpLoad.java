package com.achieveit.application.entity;

import java.util.ArrayList;

public class FeatureUpLoad {
    private ArrayList<FeatureUpLoadEntity> data;

    public FeatureUpLoad() {
    }

    public FeatureUpLoad(FeatureUpLoadEntity[] dataArray) {
        data = new ArrayList<>();
        for (int i = 0; i < dataArray.length; i++) {
            data.add(dataArray[i]);
        }
    }

    public FeatureUpLoad(ArrayList<FeatureUpLoadEntity> data) {
        this.data = data;
    }

    public ArrayList<FeatureUpLoadEntity> getData() {
        return data;
    }

    public void setData(FeatureUpLoadEntity[] dataArray) {
        data = new ArrayList<>();
        for (int i = 0; i < dataArray.length; i++) {
            data.add(dataArray[i]);
        }
    }
}
