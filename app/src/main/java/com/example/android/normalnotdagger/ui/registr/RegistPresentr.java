package com.example.android.normalnotdagger.ui.registr;


import android.content.SharedPreferences;
import android.util.Log;

import com.example.android.normalnotdagger.api.App;
import com.example.android.normalnotdagger.models.new_model.registr.RegistModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistPresentr {
    RegistMVP mvp;
    SharedPreferences user;

    public RegistPresentr(RegistMVP mvp, SharedPreferences user) {
        this.mvp = mvp;
        this.user = user;
    }

    void loadRegist(String login, String pass, String city, String tel){
        mvp.startProgresBar();
        App.getApi().getRegist(login, pass, city,tel).enqueue(new Callback<RegistModel>() {
            @Override
            public void onResponse(Call<RegistModel> call, Response<RegistModel> response) {
                Log.e("regist", response.body().getStatus()+" "+response.body().getId());
                if(response.body().getRespons().getId().equals("-1")){
                    mvp.showError(response.body().getStatus());
                    mvp.stopProgresBar();
                }
                else {
                    user.edit().putString("id", response.body().getId()).commit();
                    Log.e("regist", "id: " + user.getString("id","error"));
                    mvp.showStatus(response.body().getStatus());
                    mvp.stopProgresBar();
                }
            }
            @Override
            public void onFailure(Call<RegistModel> call, Throwable t) {
                mvp.showError("Ошибка соеденения");
                mvp.stopProgresBar();
            }
        });
    }
}
