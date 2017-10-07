package com.example.android.normalnotdagger.models.new_model.categ_model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardsModel {

    @SerializedName("cards")
    @Expose
    private List<Card> cards = null;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

}