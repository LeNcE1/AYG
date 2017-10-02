package com.example.android.normalnotdagger.ui.user_info;


import android.content.SharedPreferences;

import com.example.android.normalnotdagger.api.App;
import com.example.android.normalnotdagger.models.new_model.user_info.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {

    UserMVP mvp;
    SharedPreferences user;
    public UserPresenter(UserMVP mvp, SharedPreferences user){
        this.mvp = mvp;
        this.user = user;
    }
    void loadInfo(){
        mvp.startProgresBar();
        App.getApi().getUserInfo(user.getString("id","error")).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                mvp.showInfo(response.body().getName(),
                        response.body().getFamily(),
                        response.body().getCity(),
                        response.body().getTel(),
                        response.body().getSubNum());
                mvp.stopProgresBar();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                mvp.showError("Ошибка соеденения");
                mvp.stopProgresBar();
            }
        });

    }
}
