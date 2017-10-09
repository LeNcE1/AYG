package com.example.android.normalnotdagger.ui.history.cards;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.categ_model.Card;
import com.example.android.normalnotdagger.ui.history.ItemCard.ItemCardsFragment;

import java.util.List;

public class CardFragment extends Fragment implements CardMVP{
    RecyclerView recyclerView;
    CardsPresentr pr;
    CardsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        Bundle bundle = getArguments();

        if(bundle!=null){
            pr = new CardsPresentr(this);
            adapter = new CardsAdapter(pr,bundle.getString("id"), this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            pr.getCars(bundle.getString("id"), 0);
        }
        return recyclerView;
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(),error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startProgressBar() {

    }

    @Override
    public void stopProgressBar() {

    }

    @Override
    public void addCards(List<Card> list) {
        adapter.addCards(list);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void itemClick(Card item) {
        ItemCardListImages.getInstance().setCard(item);
        ItemCardsFragment youFragment = new ItemCardsFragment();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.news_list, youFragment)
                .addToBackStack("myStack")
                .commit();
    }
}
