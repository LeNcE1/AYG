package com.example.android.normalnotdagger.ui.commits;



public interface CommentsMVPadd {
    void showIsEmpty();
    void startProgresBar();
    void stopProgresBar();
    void showStatus(String status);
    void showError(String error);
}
