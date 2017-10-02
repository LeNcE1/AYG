package com.example.android.normalnotdagger.ui.commits;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.android.normalnotdagger.api.App;
import com.example.android.normalnotdagger.models.new_model.comments.ComentsListModel;
import com.example.android.normalnotdagger.models.new_model.status.StatusModel;
import com.example.android.normalnotdagger.models.new_model.user_info.UserModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentPresentr {
    ComentsMVP mvp;
    SharedPreferences user;

    CommentPresentr(ComentsMVP mvp, SharedPreferences user){
        this.mvp = mvp;
        this.user = user;

    }

    String name = new String();
    String loadUserName(String id){
        App.getApi().getUserInfo(id).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                name = response.body().getName();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                name = "null name";
            }
        });

        return name;
    }

    void loadComments(String post_id){
        mvp.startProgresBar();
        App.getApi().getComments(post_id).enqueue(new Callback<ComentsListModel>() {
            @Override
            public void onResponse(Call<ComentsListModel> call, Response<ComentsListModel> response) {
                mvp.stopProgresBar();
                if (response.body() == null){
                    mvp.showError("Ошибка загрузки");
                }
                else {
                    if(response.body().getComments().isEmpty()){
                        mvp.showIsEmpty();
                    }
                    else{
                        mvp.showComments(response.body().getComments());
                    }
                }
            }

            @Override
            public void onFailure(Call<ComentsListModel> call, Throwable t) {
                mvp.stopProgresBar();
                mvp.showError("Ошибка соедениения");

            }
        });
    }
}
