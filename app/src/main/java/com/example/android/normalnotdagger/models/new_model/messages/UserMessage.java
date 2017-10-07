package com.example.android.normalnotdagger.models.new_model.messages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMessage {

    @SerializedName("from_id")
    @Expose
    private Integer fromId;
    @SerializedName("to_id")
    @Expose
    private Integer toId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("is_readed")
    @Expose
    private Integer isReaded;

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(Integer isReaded) {
        this.isReaded = isReaded;
    }

}
