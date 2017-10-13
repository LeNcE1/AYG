package com.example.android.normalnotdagger.ui.message.list_dialog;


import android.util.Log;

import com.example.android.normalnotdagger.api.App;
import com.example.android.normalnotdagger.models.new_model.messages.MessagesModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesPresentr {
    MessagersMVP mvp;
    MessagesPresentr(MessagersMVP mvp){
        this.mvp = mvp;
    }

    void loadMessage(String user_id){
        App.getApi().getMessages(user_id).enqueue(new Callback<MessagesModel>() {
            @Override
            public void onResponse(Call<MessagesModel> call, Response<MessagesModel> response) {
                mvp.stopProgressBar();
                Log.e("send", "respons");
                if(response.message().equals("OK")){
                    Log.e("send", "OK");
                    if(response.body().getMessages().isEmpty()){
                        mvp.messagIsEmpty();
                    }
                    else {
                        mvp.creadDialogs(response.body().getMessages());
                    }
                }
                else{
                    mvp.showError("Ошибка загрузки");
                }
            }

            @Override
            public void onFailure(Call<MessagesModel> call, Throwable t) {
                mvp.stopProgressBar();
                mvp.showError("Ошибка соеденения");
            }
        });
    }
}
