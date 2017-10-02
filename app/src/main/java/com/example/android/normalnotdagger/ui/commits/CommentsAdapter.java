package com.example.android.normalnotdagger.ui.commits;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.comments.Comment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>{


    List<Comment> comments = new ArrayList<>();
    CommentPresentr presentr;
    SharedPreferences user;

    CommentsAdapter(List<Comment> comments, CommentPresentr presentr, SharedPreferences user){
        this.comments = comments;
        this.presentr = presentr;
        this.user = user;
    }

    void addcomments(List<Comment> list){
        for(Comment i: list){
            comments.add(i);
        }
    }

    @Override
    public CommentsAdapter.CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new CommentsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.CommentsViewHolder holder, int position) {

        Comment item = comments.get(position);
        Log.e("nameAdapter", item.getUserId().toString());

        holder.avtor.setText(item.getUser_login());
        holder.text.setText(item.getText());
        holder.data.setText(item.getDate());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
    class CommentsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_ids)
        TextView avtor;
        @BindView(R.id.text_comment)
        TextView text;
        @BindView(R.id.data_comment)
        TextView data;
        @BindView(R.id.layout)
        View layout;

        public CommentsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
