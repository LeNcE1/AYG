package com.example.android.normalnotdagger.ui.history.cards;

import com.example.android.normalnotdagger.api.App;
import com.example.android.normalnotdagger.models.new_model.categ_model.CardsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardsPresentr {
    CardMVP mvp;

    CardsPresentr( CardMVP mvp){
        this.mvp = mvp;
    }

    void getCars(final String id, int offset){
        mvp.startProgressBar();
        App.getApi().getCards(id, 20, offset).enqueue(new Callback<CardsModel>() {
            @Override
            public void onResponse(Call<CardsModel> call, Response<CardsModel> response) {
                mvp.stopProgressBar();
                if(response.body().getCards().isEmpty()){
                    mvp.showError("Нет карточек");
                }
                else{
                    mvp.addCards(response.body().getCards());
                }
            }
            @Override
            public void onFailure(Call<CardsModel> call, Throwable t) {
                mvp.stopProgressBar();
                mvp.showError("Ошибка соеденения");

            }
        });
    }
}
