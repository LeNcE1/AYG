package com.example.android.normalnotdagger.models.new_model.messages;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("user_login")
    @Expose
    private String userLogin;
    @SerializedName("user_messages")
    @Expose
    private List<UserMessage> userMessages = null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public List<UserMessage> getUserMessages() {
        return userMessages;
    }

    public void setUserMessages(List<UserMessage> userMessages) {
        this.userMessages = userMessages;
    }

}