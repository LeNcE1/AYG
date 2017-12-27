package com.example.android.normalnotdagger.ui.user_info;

public interface UserMVP {
    void showInfo(String name, String family, String city, String tel, String countPodpis, String dateRoj);

    void showError(String error);

    void startProgresBar();

    void stopProgresBar();
}
