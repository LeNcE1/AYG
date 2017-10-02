package com.example.android.normalnotdagger.models.new_model.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties {

    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("fillColor")
    @Expose
    private String fillColor;
    @SerializedName("opacity")
    @Expose
    private String opacity;
    @SerializedName("fillOpacity")
    @Expose
    private String fillOpacity;
    @SerializedName("backend_layer_id")
    @Expose
    private Integer backendLayerId;
    @SerializedName("text")
    @Expose
    private String text;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public String getOpacity() {
        return opacity;
    }

    public void setOpacity(String opacity) {
        this.opacity = opacity;
    }

    public String getFillOpacity() {
        return fillOpacity;
    }

    public void setFillOpacity(String fillOpacity) {
        this.fillOpacity = fillOpacity;
    }

    public Integer getBackendLayerId() {
        return backendLayerId;
    }

    public void setBackendLayerId(Integer backendLayerId) {
        this.backendLayerId = backendLayerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}