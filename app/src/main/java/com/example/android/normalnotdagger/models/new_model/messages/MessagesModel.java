package com.example.android.normalnotdagger.models.new_model.messages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MessagesModel {
    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
