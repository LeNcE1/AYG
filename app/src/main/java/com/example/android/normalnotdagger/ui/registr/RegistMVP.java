package com.example.android.normalnotdagger.ui.registr;

public interface RegistMVP {
    void showStatus(String status);
    void showError(String error);
    void startProgresBar();
    void stopProgresBar();
}
