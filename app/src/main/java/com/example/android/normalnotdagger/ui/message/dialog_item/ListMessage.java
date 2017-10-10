package com.example.android.normalnotdagger.ui.message.dialog_item;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.ui.message.ListMessageSingleton;



public class ListMessage extends Fragment {
    RecyclerView recyclerView;
    SharedPreferences user;
    DialogAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Log.e("SIZE LIST", ListMessageSingleton.getInstance().getList().size() + "");
        adapter = new DialogAdapter(ListMessageSingleton.getInstance().getList(),user.getString("id","error"));
        recyclerView.setAdapter(adapter);

        //скролл при открытии клавиатуры
        if (Build.VERSION.SDK_INT >= 11) {
            recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v,
                                           int left, int top, int right, int bottom,
                                           int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (bottom < oldBottom) {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                recyclerView.smoothScrollToPosition(
                                        recyclerView.getAdapter().getItemCount() - 1);
                            }
                        }, 100);
                    }
                }
            });
        }
       // recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
        return recyclerView;
    }
}
