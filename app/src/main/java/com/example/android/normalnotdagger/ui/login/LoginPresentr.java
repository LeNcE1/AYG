package com.example.android.normalnotdagger.ui.login;


import android.content.SharedPreferences;
import android.util.Log;

import com.example.android.normalnotdagger.api.App;
import com.example.android.normalnotdagger.models.new_model.registr.RegistModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresentr {
    LoginMVP mvp;
    SharedPreferences user;
    public LoginPresentr(LoginMVP mvp, SharedPreferences user){
        this.mvp = mvp;
        this.user = user;
    }
    void loadLogin(String login, String pass){
        mvp.startProgresBar();
        App.getApi().getAvtor(login,pass).enqueue(new Callback<RegistModel>() {
            @Override
            public void onResponse(Call<RegistModel> call, Response<RegistModel> response) {
                if(response.body().getId().equals("-1")){
                    mvp.showError("Неверный логин или пароль");
                    mvp.stopProgresBar();
                }
                else{
                    Log.e("id:", response.body().getId());
                    user.edit().putString("id", response.body().getId()).commit();
                    mvp.showStatus("Авторизация прошла успешна");
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
