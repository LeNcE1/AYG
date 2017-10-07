package com.example.android.normalnotdagger.ui.user_wall;



import android.util.Log;

import com.example.android.normalnotdagger.api.App;
import com.example.android.normalnotdagger.models.new_model.status.StatusModel;
import com.example.android.normalnotdagger.models.new_model.user_info.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserWallPresentr {
    UserWallMVP mvp;
    public UserWallPresentr(UserWallMVP mvp){
        this.mvp = mvp;
    }
    void loadInfo(String avtor_id){
        mvp.startProgresBar();
        App.getApi().getUserInfo(avtor_id).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                mvp.showInfo(response.body());
                mvp.stopProgresBar();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                mvp.showError("Ошибка соеденения");
                mvp.stopProgresBar();
            }
        });
    }
    void addPod(String my_id, String user_id){
        App.getApi().addPodpiska(my_id,user_id).enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if(response.body().getStatus().equals("false")){
                    Log.e("addpod", "fiasko");
                }
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                Log.e("addpod", "fiasko");
            }
        });
    }

}
