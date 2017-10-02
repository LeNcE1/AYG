package com.example.android.normalnotdagger.ui.commits;


import android.content.SharedPreferences;

import com.example.android.normalnotdagger.api.App;
import com.example.android.normalnotdagger.models.new_model.status.StatusModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsPresAdd {
    CommentsMVPadd mvp;
    SharedPreferences user;

    CommentsPresAdd(CommentsMVPadd mvp, SharedPreferences user){
        this.mvp = mvp;
        this.user = user;

    }
    void addComment(String text, String post_id){
        mvp.startProgresBar();
        Calendar calendar = Calendar.getInstance();
        String dataMessage = calendar.getTime().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = dateFormat.format(new Date());
        App.getApi().getComment(post_id,user.getString("id","eeeeerr"),text,data).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if(response.body() == null){
                    mvp.showError("Ошибка добавления коментария");
                    mvp.stopProgresBar();
                }
                else{
                    mvp.stopProgresBar();
                    mvp.showStatus(response.body().getStatus());
                }

            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                mvp.showError("Ошибка соедениения");
            }
        });
    }
}
