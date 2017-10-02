package com.example.android.normalnotdagger.models.new_model.cread_news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreadNewModel {

    @SerializedName("post_id")
    @Expose
    private Integer postId;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}