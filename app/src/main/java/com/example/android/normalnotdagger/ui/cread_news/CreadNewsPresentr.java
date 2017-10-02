package com.example.android.normalnotdagger.ui.cread_news;


import android.app.Activity;

import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.example.android.normalnotdagger.api.App;

import com.example.android.normalnotdagger.ipaulpro.afilechooser.utils.FileUtils;
import com.example.android.normalnotdagger.models.new_model.cread_news.CreadNewModel;

import java.io.File;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreadNewsPresentr {

    CreadNewsMVP mvp;
    SharedPreferences user;
    Activity context;
    public CreadNewsPresentr(CreadNewsMVP mvp, SharedPreferences user, Activity context){
        this.mvp = mvp;
        this.user = user;
        this.context = context;
    }
    void loadNew(String title, String shorts, String text,List<Uri> files){
        mvp.startProgresBar();
        Calendar calendar = Calendar.getInstance();
        String dataMessage = calendar.getTime().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = dateFormat.format(new Date());

        List<MultipartBody.Part> f = new ArrayList<>();



        for(Uri i: files){
            File file = FileUtils.getFile(context, i);
            RequestBody requestBody = RequestBody.create(MediaType.parse("images"), file);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("images[]", file.getName(), requestBody);
            f.add(filePart);
        }
        if(f.isEmpty()){
            if (!user.getString("id", "error").equals("error")) {
                Log.e("News", title + ", " + shorts + ", " + text + ", " + user.getString("id", "eeeeerr") + ", " + data );
                Call<CreadNewModel> call = App.getApi().getCreadNewNotFile(title, shorts, text, data, user.getString("id", "1"));
                call.enqueue(new Callback<CreadNewModel>() {
                    @Override
                    public void onResponse(Call<CreadNewModel> call,
                                           Response<CreadNewModel> response) {
                        if (!response.body().getStatus().equals("fail")) {
                            mvp.stopProgresBar();
                            mvp.showStatus("Пост успешно опубликован");
                        } else {
                            mvp.stopProgresBar();
                            mvp.showStatus("Ошибка создания поста");
                        }
                        Log.e("error", response.body().getStatus());
                        Log.e("Upload", "success");
                    }

                    @Override
                    public void onFailure(Call<CreadNewModel> call, Throwable t) {
                        Log.e("Upload error:", t + "");
                        mvp.showError("Ошибка интернет соеденения");
                    }
                });
            }
            else {
                mvp.stopProgresBar();
                mvp.showError("Для создание поста необходимо авторезироваться");
            }
        }
        else {
            if (!user.getString("id", "error").equals("error")) {
                Log.e("News", title + ", " + shorts + ", " + text + ", " + user.getString("id", "eeeeerr") + ", " + data + ", " + f);
                Call<CreadNewModel> call = App.getApi().getCreadNew(title, shorts, text, data, user.getString("id", "1"), f);
                call.enqueue(new Callback<CreadNewModel>() {
                    @Override
                    public void onResponse(Call<CreadNewModel> call,
                                           Response<CreadNewModel> response) {
                        if (!response.body().getStatus().equals("fail")) {
                            mvp.stopProgresBar();
                            mvp.showStatus("Пост успешно опубликован");
                        } else {
                            mvp.stopProgresBar();
                            mvp.showStatus("Ошибка создания поста");
                        }
                        Log.e("error", response.body().getStatus());
                        Log.e("Upload", "success");
                    }
                    @Override
                    public void onFailure(Call<CreadNewModel> call, Throwable t) {
                        Log.e("Upload error:", t + "");
                        mvp.showError("Ошибка интернет соеденения");
                    }
                });
            }
            else {
                mvp.stopProgresBar();
                mvp.showError("Для создание поста необходимо авторезироваться");
            }
        }
    }

}
