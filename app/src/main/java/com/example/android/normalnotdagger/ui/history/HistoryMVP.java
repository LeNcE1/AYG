package com.example.android.normalnotdagger.ui.history;


import com.example.android.normalnotdagger.models.new_model.categ_model.Category;
import java.util.List;


public interface HistoryMVP {
    void showCateg(List<Category> list);
    void showError(String error);
    void startProgressBar();
    void stopProgressBar();
    void cardsFinish(boolean mark, int id);
    void cardsStart(int id);
    void creadList(int id);
}
