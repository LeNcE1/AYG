package com.example.android.normalnotdagger.ui.history;


import android.util.Log;

import com.example.android.normalnotdagger.api.App;
import com.example.android.normalnotdagger.models.new_model.categ_model.CardsModel;
import com.example.android.normalnotdagger.models.new_model.categ_model.CategModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPresentr {
    HistoryMVP mvp;
    HistoryPresentr(HistoryMVP mvp){
        this.mvp = mvp;
    }
    void loadingCateg(int levl){
        mvp.startProgressBar();
        if(levl==0){
            App.getApi().getCateg().enqueue(new Callback<CategModel>() {
                @Override
                public void onResponse(Call<CategModel> call, Response<CategModel> response) {
                    if(response.message().equals("OK")){
                        Log.e("JSON", response.body().getCategories()+"");
                        mvp.stopProgressBar();
                        mvp.showCateg(response.body().getCategories());
                    }
                    else {
                        mvp.stopProgressBar();
                        mvp.showError("Сервис недоступен");
                    }
                }

                @Override
                public void onFailure(Call<CategModel> call, Throwable t) {
                    mvp.stopProgressBar();
                    mvp.showError("Ошибка соеденения");
                }
            });
        }
        else{
            App.getApi().getCategChildrens(levl).enqueue(new Callback<CategModel>() {
                @Override
                public void onResponse(Call<CategModel> call, Response<CategModel> response) {
                    if(response.message().equals("OK")){
                        mvp.stopProgressBar();
                        mvp.showCateg(response.body().getCategories());
                    }
                    else {
                        mvp.stopProgressBar();
                        mvp.showError("Сервис недоступен");
                    }

                }
                @Override
                public void onFailure(Call<CategModel> call, Throwable t) {
                    mvp.stopProgressBar();
                    mvp.showError("Ошибка соеденения");
                }
            });
        }

    }
    void carsIsEmpty(final int id){
        App.getApi().getCards(id, 1, 0).enqueue(new Callback<CardsModel>() {
            @Override
            public void onResponse(Call<CardsModel> call, Response<CardsModel> response) {
                if(response.body().getCards().isEmpty()){
                    mvp.cardsFinish(false, id);
                }
                else{
                    mvp.cardsFinish(true, id);
                }
            }

            @Override
            public void onFailure(Call<CardsModel> call, Throwable t) {
               mvp.cardsFinish(false, id);
            }
        });
    }
}
