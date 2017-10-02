package com.example.android.normalnotdagger.ui.login;


public interface LoginMVP {
    void showStatus(String status);

    void showError(String error);

    void startProgresBar();

    void stopProgresBar();
}