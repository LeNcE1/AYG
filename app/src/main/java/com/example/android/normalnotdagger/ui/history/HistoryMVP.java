package com.example.android.normalnotdagger.ui.history;


import java.util.ArrayList;

public interface HistoryMVP {
    void showCateg(ArrayList<String> categName, ArrayList<String> id);
    void showError(String error);
    void startProgressBar();
    void stopProgressBar();
}
