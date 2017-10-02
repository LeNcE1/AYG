package com.example.android.normalnotdagger.ui.cread_news;


public interface CreadNewsMVP {

    void showStatus(String status);

    void showError(String error);

    void startProgresBar();

    void stopProgresBar();

}
