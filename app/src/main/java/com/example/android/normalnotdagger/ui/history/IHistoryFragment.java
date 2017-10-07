package com.example.android.normalnotdagger.ui.history;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.normalnotdagger.R;

public class IHistoryFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.i_news_fragment, container, false);
        HistoryFragment youFragment = new HistoryFragment();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.news_list, youFragment)
                .addToBackStack("myStack")
                .commit();
        return view;
    }
}
