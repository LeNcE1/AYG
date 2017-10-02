package com.example.android.normalnotdagger.ui.map;


import org.json.JSONArray;


public interface MapMVP {
    void showMap(JSONArray maps);

    void showError(String error);

    void startProgresBar();

    void stopProgresBar();

}
