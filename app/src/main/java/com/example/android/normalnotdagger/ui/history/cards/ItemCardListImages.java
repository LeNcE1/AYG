package com.example.android.normalnotdagger.ui.history.cards;


import com.example.android.normalnotdagger.models.new_model.categ_model.Card;

import java.util.ArrayList;
import java.util.List;

public class ItemCardListImages {

    List<String> imag = new ArrayList<>();
    Card card;
    private static final ItemCardListImages ourInstance = new ItemCardListImages();

    public static ItemCardListImages getInstance() {
        return ourInstance;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    private ItemCardListImages() {
    }

    public void setImag(List<String> imag) {
        this.imag = imag;
    }

    public List<String> getImag() {
        return imag;
    }
}
