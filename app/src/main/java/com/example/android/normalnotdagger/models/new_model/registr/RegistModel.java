package com.example.android.normalnotdagger.models.new_model.registr;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RegistRespons getRespons(){
        RegistRespons respons = new RegistRespons();
        respons.setId(id);
        respons.setStatus(status);
        return respons;
    }

}
