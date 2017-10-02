package com.example.android.normalnotdagger.ui.commits;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.normalnotdagger.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ICommentsFragment extends Fragment implements CommentsMVPadd{

    View view;
    SharedPreferences user;
    CommentsPresAdd presentr;


    @BindView(R.id.addComment)
    ImageView addComment;
    @BindView(R.id.textComment)
    EditText text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.comennts_list, container, false);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        presentr = new CommentsPresAdd(this, user);
        ButterKnife.bind(this, view);

        final Bundle bundle = getArguments();
        if(bundle == null){
            Toast.makeText(getActivity(),"Публикация не найдена I", Toast.LENGTH_LONG).show();
        }
        else{
            CommentFragment youFragment = new CommentFragment();
            Bundle bundle1 = new Bundle();
            Log.e("testB", "post_id" + bundle.getString("post_id"));
            bundle1.putString("post_id", bundle.getString("post_id"));
            youFragment.setArguments(bundle1);
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .add(R.id.comment_list, youFragment)
                    .addToBackStack("myStack")
                    .commit();
        }
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text.getText().equals("")){
                    Toast.makeText(getActivity(),"Введите текст", Toast.LENGTH_LONG).show();
                }
                else{
                    presentr.addComment(text.getText().toString(),bundle.getString("post_id"));
                }
            }
        });
        return view;
    }


    @Override
    public void showIsEmpty() {

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

    @Override
    public void showError(String error) {

        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

    }
}
