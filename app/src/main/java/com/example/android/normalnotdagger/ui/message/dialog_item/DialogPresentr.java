package com.example.android.normalnotdagger.ui.message.dialog_item;


import android.util.Log;

import com.example.android.normalnotdagger.api.App;
import com.example.android.normalnotdagger.models.new_model.status.StatusModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogPresentr {

    void addView(String id, String user_id){
        App.getApi().getView(id, user_id).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                Log.e("addView", "zbs");
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                Log.e("addView", "xuyna");
            }
        });
    }
  public void senrMessage(String form_id, String to_id, String text){
        Calendar calendar = Calendar.getInstance();
        String dataMessage = calendar.getTime().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = dateFormat.format(new Date());
        App.getApi().sendMessage(form_id, to_id, text, data).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if(response.body().getStatus().equals("OK")){
                    Log.e("messag", "send");
                }
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                Log.e("messag", "error");
            }
        });
    }
}
