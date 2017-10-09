package com.example.android.normalnotdagger.ui.commits;

import android.app.Fragment;
import android.app.FragmentManager;
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
import com.example.android.normalnotdagger.ui.user_info.UserFragment;
import com.example.android.normalnotdagger.ui.user_wall.UserWallFragment;

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
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setStackFromEnd(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(commentsAdapter);
            commentsAdapter = new CommentsAdapter(comments,presentr,user, this);
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
    public void startUserInfo(String avtor_id) {
        if(avtor_id.equals(user.getString("id","e"))){
            UserFragment youFragment = new UserFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();
        }
        else {
            UserWallFragment youFragment = new UserWallFragment();
            Bundle bundle = new Bundle();
            bundle.putString("avtor_id", avtor_id);
            youFragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();
        }
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
