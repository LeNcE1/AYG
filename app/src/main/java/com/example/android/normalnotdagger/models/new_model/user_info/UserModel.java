package com.example.android.normalnotdagger.models.new_model.user_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("family")
    @Expose
    private String family;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("tel")
    @Expose
    private String tel;
    @SerializedName("sub_num")
    @Expose
    private String subNum;
    @SerializedName("status")
    @Expose
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSubNum() {
        return subNum;
    }

    public void setSubNum(String subNum) {
        this.subNum = subNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}