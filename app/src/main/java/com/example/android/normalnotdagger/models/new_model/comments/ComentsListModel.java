package com.example.android.normalnotdagger.models.new_model.comments;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComentsListModel {
    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("comments")
    @Expose
    private List<Comment> comments = null;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
