package com.example.android.normalnotdagger.ui.user_wall;


import com.example.android.normalnotdagger.models.new_model.user_info.UserModel;

public interface UserWallMVP {
    void showInfo(UserModel user);
    void showError(String error);
    void startProgresBar();
    void stopProgresBar();
}
