package com.example.android.normalnotdagger.ui.news;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.ui.news.NewsFragment;


public class InewsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.i_news_fragment, container, false);
        NewsFragment youFragment = new NewsFragment();
        Bundle bundle = getArguments();
        if(bundle != null){
            if(bundle.getString("pod")!= null) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("pod", "123");
                youFragment.setArguments(bundle1);
            }
            else{
                Bundle bundle1 = new Bundle();
                bundle1.putString("my", "123");
                youFragment.setArguments(bundle1);
            }
        }
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.news_list, youFragment)
                .addToBackStack("myStack")
                .commit();


        return view;
    }
}
