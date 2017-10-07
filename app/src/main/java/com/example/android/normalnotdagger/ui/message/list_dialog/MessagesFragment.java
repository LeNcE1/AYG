package com.example.android.normalnotdagger.ui.message.list_dialog;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.messages.Message;
import com.example.android.normalnotdagger.ui.message.dialog_item.DialogItemFragment;

import java.util.ArrayList;
import java.util.List;

public class MessagesFragment extends Fragment implements MessagersMVP{
    List<Message> posts = new ArrayList<>();
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    MessagesPresentr pr;
    SharedPreferences user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        if(!user.getString("id","null").equals("null")) {
            pr = new MessagesPresentr(this);
            pr.loadMessage(user.getString("id", "error"));

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
           // recyclerView.setAdapter(messageAdapter);
           // messageAdapter = new MessageAdapter(posts);
           // recyclerView.setAdapter(messageAdapter);

        }
        return recyclerView;
    }

    @Override
    public void creadDialogs(List<Message> list) {
        Log.e("mes", "OK");
        messageAdapter = new MessageAdapter(list ,this);
        recyclerView.setAdapter(messageAdapter);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void messagIsEmpty() {

    }

    @Override
    public void startDiolog() {
        DialogItemFragment youFragment = new DialogItemFragment();
    android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.content, youFragment)
                .addToBackStack("myStack")
                .commit();
    }
}
