package com.example.android.normalnotdagger.ui.news;


import android.content.SharedPreferences;
import android.util.Log;

import com.example.android.normalnotdagger.api.App;
import com.example.android.normalnotdagger.models.new_model.news.News;
import com.example.android.normalnotdagger.models.new_model.news.NewsModel;
import com.example.android.normalnotdagger.models.new_model.status.StatusModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPresentr {
    NewsMVP mvp;
    SharedPreferences user;

    public NewsPresentr(NewsMVP mvp, SharedPreferences user) {
        this.mvp = mvp;
    }

    public void loadNews(String user_id, int offset) {
        mvp.startProgresBar();
        Log.e("CONECT", "loadNews");

        App.getApi().getData("20", offset, user_id).enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                //  Log.e("error",response.message());
                if (response.message().equals("OK")) {
                    Log.e("sizeTest", response.body().getNews().get(0).getUserMark() + " test");
                    if (response.body().getNews().isEmpty()) {
                        mvp.stopProgresBar();
                        mvp.showIsEmpty();
                    } else {
                        mvp.stopProgresBar();
                        mvp.showNews(response.body().getNews());

                    }
                } else {
                    mvp.stopProgresBar();
                    mvp.showError();
                }

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Log.e("error1", t + "");
                mvp.stopProgresBar();
                mvp.showError();
                //Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadNewspod(final String user_id, int offset) {
        mvp.startProgresBar();

        final List<News> myNews = new ArrayList<>();
        Log.e("CONECT", "loadNewspod");
        App.getApi().getNewsPod("20", offset, user_id).enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                //  Log.e("error",response.message());
                if (response.message().equals("OK")) {
                    //Log.e("sizeTest", response.body().getNews().get(0).getUserMark() + " test");
                    if (response.body().getNews().isEmpty()) {
                        mvp.stopProgresBar();
                        mvp.showIsEmpty();
                    } else {
                        Log.e("sizeTest", response.body().getNews().get(0).getUserMark() + " test");
                        for (News i : response.body().getNews()) {
                            if (!i.getUserId().toString().equals(user_id)) {
                                myNews.add(i);
                            }
                        }
                        mvp.stopProgresBar();
                        mvp.showNews(myNews);
                    }
                } else {
                    mvp.stopProgresBar();
                    mvp.showError();
                }

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Log.e("error1", t + "");
                mvp.stopProgresBar();
                mvp.showError();
                //Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadNewsMy(final String user_id, int offset) {
        mvp.startProgresBar();
        Log.e("CONECT", "loadNewsMy");
        App.getApi().getData("20", offset, user_id).enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                mvp.stopProgresBar();
                //  Log.e("error",response.message());
                if (response.message().equals("OK")) {

                    // Log.e("sizeTest", response.body().getNews().get(0).getUserMark() + " test");
                    if (response.body().getNews().isEmpty()) {
                        mvp.showIsEmpty();

                    } else {
                        List<News> my = new ArrayList<News>();
                        Log.e("myID", " = " + user_id);
                        for (int i = 0; i < response.body().getNews().size(); i++) {
                            Log.e("POST_ID", "= " + response.body().getNews().get(i).getUserId());
                            if (response.body().getNews().get(i).getUserId().toString().equals(user_id)) {
                                Log.e("true", "true");
                                my.add(response.body().getNews().get(i));
                            }
                        }
                        if (my.isEmpty()) {
                            mvp.showIsEmpty();
                        } else {
                            mvp.showNews(my);
                        }
                    }
                } else {
                    mvp.showError();
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                mvp.stopProgresBar();
                Log.e("error1", t + "");
                mvp.showError();

                //Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addLike(String user_id, String post_id, int like_disLike) {
        App.getApi().addLike(user_id, post_id, like_disLike).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if (response.body().getStatus().equals("OK")) {
                    mvp.addLike();
                } else {
                    mvp.addLikeError("Ошибка соеденения" + response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                mvp.addLikeError("Ошибка соеденения");
            }
        });
    }

    public void addView(String id, String post_id) {
        App.getApi().addView(id, post_id).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if (response.body().getStatus().equals("OK")) {
                    // Log.e("View", "add");
                }
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                mvp.addLikeError("Ошибка соеденения");
            }
        });
    }

    void startUserInfo(String post_id) {
        mvp.startUserInfo(post_id);
    }

    void startFullNews(News post_id) {
        mvp.startFullNews(post_id);
    }

    void startMyInfo() {
        mvp.startMyInfo();
    }

    void startComments(String post_id) {
        mvp.startComments(post_id);
    }

    void deletePost(int post_id) {
        App.getApi().deletePost(post_id).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if (response.body().getStatus().equals("OK")) {
                    mvp.showDeletePost("Пост успешно удалён");
                } else {
                    mvp.showDeletePost("Ошибка удаления данных");
                }
            }
            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                mvp.showDeletePost("Ошибка удаления данных");
            }
        });
    }

}
