package com.example.android.normalnotdagger.ui.history;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.categ_model.Category;
import com.example.android.normalnotdagger.ui.history.cards.CardFragment;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment implements HistoryMVP{
    RecyclerView recyclerView;
    List<Category> list = new ArrayList<>();
    HistoryPresentr pr;
    HistoryAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        Bundle bundle = getArguments();
        if(bundle!=null){
            pr = new HistoryPresentr(this);
            pr.loadingCateg(Integer.valueOf(bundle.getString("id")));
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            adapter = new HistoryAdapter(list, pr, this);
            recyclerView.setAdapter(adapter);
        }
        else {
            pr = new HistoryPresentr(this);
            pr.loadingCateg(0);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
            adapter = new HistoryAdapter(list, pr, this);
            recyclerView.setAdapter(adapter);
        }
        return recyclerView;
    }



    @Override
    public void showCateg(List<Category> list) {
        adapter = new HistoryAdapter(list, pr, this);
        recyclerView.setAdapter(adapter);
        recyclerView.getAdapter().notifyDataSetChanged();

    }

    @Override
    public void showError(String error) {
        Log.e("Error", error);
    }

    @Override
    public void startProgressBar() {

    }

    @Override
    public void stopProgressBar() {

    }

    @Override
    public void cardsStart(int id) {
        CardFragment youFragment = new CardFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id+"");
        youFragment.setArguments(bundle);
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.news_list, youFragment)
                .addToBackStack("myStack")
                .commit();
    }


    @Override
    public void creadList(int id) {
        HistoryFragment youFragment = new HistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id+"");
        youFragment.setArguments(bundle);
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.news_list, youFragment)
                .addToBackStack("myStack")
                .commit();
    }
}
