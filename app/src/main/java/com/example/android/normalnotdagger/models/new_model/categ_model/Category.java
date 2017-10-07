package com.example.android.normalnotdagger.models.new_model.categ_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("childrens")
    @Expose
    private Integer childrens;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getChildrens() {
        return childrens;
    }

    public void setChildrens(Integer childrens) {
        this.childrens = childrens;
    }

}
