package com.example.android.normalnotdagger.ui.news;


import com.example.android.normalnotdagger.models.new_model.news.News;

import java.util.List;

public interface NewsMVP {
    void showNews(List<News> posts);
    void showError();
    void showIsEmpty();
    void addLike();
    void addLikeError(String res);
    void startProgresBar();
    void stopProgresBar();
    void startUserInfo(String avtor_id);
    void startComments(String post_id);
    void showDeletePost(String status);
    void startFullNews(News news);
    void startMyInfo();
    void replase();
}
