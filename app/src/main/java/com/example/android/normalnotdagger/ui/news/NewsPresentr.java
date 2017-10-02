package com.example.android.normalnotdagger.ui.news;


import android.util.Log;

import com.example.android.normalnotdagger.api.App;
import com.example.android.normalnotdagger.models.new_model.news.News;
import com.example.android.normalnotdagger.models.new_model.news.NewsModel;
import com.example.android.normalnotdagger.models.new_model.status.StatusModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPresentr {
    NewsMVP mvp;
    public NewsPresentr(NewsMVP mvp){
        this.mvp = mvp;
    }

    public void loadNews(String user_id, int offset){
        mvp.startProgresBar();
        App.getApi().getData("20", offset, "1").enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                Log.e("error",response.message());
                if(response.message().equals("OK")) {
                    Log.e("sizeTest", response.body().getNews().get(0).getText() + "test");
                    if (response.body().getNews().isEmpty()) {
                        mvp.showIsEmpty();
                        mvp.stopProgresBar();
                    } else {
                        mvp.showNews(response.body().getNews());
                        mvp.stopProgresBar();
                    }
                }
                else{
                    mvp.showError();
                }

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Log.e("error1", t.getMessage());
                mvp.showError();
                mvp.stopProgresBar();
                //Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addLike(String user_id, String post_id, int like_disLike){
        App.getApi().addLike(user_id, post_id, like_disLike).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if(response.body().getStatus().equals("OK")){
                    mvp.addLike();
                }
                else{
                    mvp.addLikeError("Ошибка соеденения+1"+response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                mvp.addLikeError("Ошибка соеденения");
            }
        });
    }
    public void addView(String id, String post_id){
        App.getApi().addView(id, post_id).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if(response.body().getStatus().equals("OK")){
                    Log.e("View", "add");
                }
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                mvp.addLikeError("Ошибка соеденения");

            }
        });
    }

    void startUserInfo(String post_id){
        mvp.startUserInfo(post_id);
    }

    void startFullNews(News post_id){
        mvp.startFullNews(post_id);
    }

    void startComments(String post_id) { mvp.startComments(post_id);}

}
