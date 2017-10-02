package com.example.android.normalnotdagger.models.new_model.map;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MapModel {
    @SerializedName("map")
    @Expose
    private List<Map> map = null;

    public List<Map> getMap() {
        return map;
    }

    public void setMap(List<Map> map) {
        this.map = map;
    }

}
