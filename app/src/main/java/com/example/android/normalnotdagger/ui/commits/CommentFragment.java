package com.example.android.normalnotdagger.ui.commits;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.comments.Comment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentFragment extends Fragment implements ComentsMVP{

    RecyclerView recyclerView;
    SharedPreferences user;
    List<Comment> comments = new ArrayList<>();
    CommentPresentr presentr;
    CommentsAdapter commentsAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        presentr = new CommentPresentr(this, user);
        final Bundle bundle = getArguments();
        if(bundle == null){
            Toast.makeText(getActivity(),"Публикация не найдена 2", Toast.LENGTH_LONG).show();
        }
        else{
            presentr.loadComments(bundle.getString("post_id"));
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(commentsAdapter);
            commentsAdapter = new CommentsAdapter(comments,presentr,user);
            recyclerView.setAdapter(commentsAdapter);
        }


        return recyclerView;
    }

    @Override
    public void showComments(List<Comment> comments) {
        commentsAdapter.addcomments(comments);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), "Ошибка соеденения с интернетом", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showIsEmpty() {

        Toast.makeText(getActivity(), "Комментариев нет", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProgresBar() {

    }

    @Override
    public void stopProgresBar() {

    }

    @Override
    public void showStatus(String status) {
        Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();
    }
}
