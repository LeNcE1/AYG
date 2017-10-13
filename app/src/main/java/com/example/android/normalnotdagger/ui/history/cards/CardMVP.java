package com.example.android.normalnotdagger.ui.history.cards;


import com.example.android.normalnotdagger.models.new_model.categ_model.Card;


import java.util.List;

public interface CardMVP {
    void showError(String error);
    void startProgressBar();
    void stopProgressBar();
    void addCards(List<Card> list);
    void itemClick(Card item);
}
