package com.example.android.normalnotdagger.models.new_model.map;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("coordinates")
    @Expose
    private List<List<List<String>>> coordinates = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<List<String>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<String>>> coordinates) {
        this.coordinates = coordinates;
    }

}