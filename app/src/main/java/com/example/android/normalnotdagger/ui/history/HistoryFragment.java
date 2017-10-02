package com.example.android.normalnotdagger.ui.history;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.normalnotdagger.R;

import java.util.ArrayList;

public class HistoryFragment extends Fragment implements HistoryMVP{
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.comennts_list, container, false);

        ListView lvMain = (ListView) view.findViewById(R.id.lvMain);

        return view;
    }

    @Override
    public void showCateg(ArrayList<String> categName, ArrayList<String> id) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
               R.layout.hisory_item, categName);

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void startProgressBar() {

    }

    @Override
    public void stopProgressBar() {

    }
}
