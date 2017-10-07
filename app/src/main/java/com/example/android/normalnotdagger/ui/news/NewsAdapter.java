package com.example.android.normalnotdagger.ui.news;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.news.News;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.RibotViewHolder> {


    private List<News> mRibots = new ArrayList<>();
    NewsPresentr pr;
    SharedPreferences user;
    int pag =20;
    String pod;
    String m;

    public NewsAdapter(List<News> posts, NewsPresentr pr,SharedPreferences user, String pod, String m) {
        this.pod = pod;
        this.mRibots = posts;
        this.pr = pr;
        this.user = user;
        this.m = m;
    }

    public void addPosts(List<News> ribots) {
        for(News i: ribots){
            mRibots.add(i);
        }
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        return new RibotViewHolder(itemView);
    }

    int doposition = 0;
    @Override
    public void onBindViewHolder(final RibotViewHolder holder, final int position) {
        if(position == (pag-6)){
            if(pod == null) {
                if(m != null){
                    Log.e("My post", "my post");
                    holder.delete.setVisibility(View.VISIBLE);
                    pr.loadNewsMy(user.getString("id", "1"), pag);
                }
                else {
                    pr.loadNews(user.getString("id", "1"), pag);
                }
            }
            else{
                pr.loadNewspod(user.getString("id", "1"), pag);
            }
            pag+=20;
        }
        else{
            if(m != null){
                Log.e("My post", "my post");
                holder.delete.setVisibility(View.VISIBLE);
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        pr.deletePost(mRibots.get(position).getPostId());

                    }
                });
            }
        }




        final News example = mRibots.get(position);
        holder.dateTextView.setText(example.getDate());
        if(example.getUserId() == 0) {
            String adm_name = "Администратор";
            SpannableString spannableString = new SpannableString(adm_name);
            spannableString.setSpan(new UnderlineSpan(), 0, adm_name.length(), 0);
            holder.autorTextView.setText(spannableString);
        }
        else{
            SpannableString spannableString = new SpannableString(example.getUserLogin());
            spannableString.setSpan(new UnderlineSpan(), 0,example.getUserLogin().length(), 0);
            holder.autorTextView.setText(spannableString);
        }
        holder.headTextView.setText(example.getTitle());
        holder.subTextView.setText(example.getShort());
        holder.viewsTextView.setText(example.getViews());
        holder.ratingTextView.setText(example.getMark().toString());


            doposition = position;
            if (!user.getString("id", "error").equals("error")) {
               // Log.e("USerMark", example.getUserMark() + " " + position);
                switch (mRibots.get(position).getUserMark()) {
                    case 1: {
                        holder.like.setColorFilter(Color.RED);
                        holder.deslike.setColorFilter(Color.parseColor("#FFC107"));
                        break;
                    }
                    case -1: {
                        holder.deslike.setColorFilter(Color.RED);
                        holder.like.setColorFilter(Color.parseColor("#FFC107"));
                        break;
                    }
                    case 0:{
                        holder.deslike.setColorFilter(Color.parseColor("#FFC107"));
                        holder.like.setColorFilter(Color.parseColor("#FFC107"));
                        break;
                    }
                }
                pr.addView(user.getString("id", "1"), example.getPostId().toString());
            }

        holder.autorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(example.getUserId() != 0)
                pr.startUserInfo(example.getUserId().toString());
            }
        });

        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pr.startComments(example.getPostId().toString());
                //тут комменты
            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!user.getString("id","error").equals("error")) {
                    switch (mRibots.get(position).getUserMark()){
                        case 0:{
                            pr.addLike(user.getString("id", "1"), example.getPostId().toString(), 1);
                            mRibots.get(position).setMark(example.getMark() + 1);
                            holder.ratingTextView.setText(mRibots.get(position).getMark().toString());
                            holder.like.setColorFilter(Color.RED);
                            holder.deslike.setColorFilter(Color.parseColor("#FFC107"));
                            mRibots.get(position).setUserMark(1);
                            break;
                        }
                        case 1:{
                            pr.addLike(user.getString("id", "1"), example.getPostId().toString(),0);
                            mRibots.get(position).setMark(mRibots.get(position).getMark()-1);
                            mRibots.get(position).setUserMark(0);
                            holder.ratingTextView.setText(mRibots.get(position).getMark().toString());
                            holder.like.setColorFilter(Color.parseColor("#FFC107"));
                            holder.deslike.setColorFilter(Color.parseColor("#FFC107"));
                            break;

                        }
                        case -1:{
                            pr.addLike(user.getString("id", "1"), example.getPostId().toString(), 1);
                            mRibots.get(position).setMark(mRibots.get(position).getMark() + 2);
                            holder.ratingTextView.setText(mRibots.get(position).getMark().toString());
                            mRibots.get(position).setUserMark(1);
                            holder.deslike.setColorFilter(Color.parseColor("#FFC107"));
                            holder.like.setColorFilter(Color.RED);
                            break;
                        }
                    }
                }
            }
        });

        holder.deslike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!user.getString("id","error").equals("error")) {
                    switch (mRibots.get(position).getUserMark()){
                        case 0:{
                            pr.addLike(user.getString("id", "1"), example.getPostId().toString(), -1);
                            mRibots.get(position).setMark(mRibots.get(position).getMark() - 1);
                            mRibots.get(position).setUserMark(-1);
                            holder.ratingTextView.setText(mRibots.get(position).getMark().toString());
                            holder.deslike.setColorFilter(Color.RED);
                            break;
                        }
                        case 1:{
                            pr.addLike(user.getString("id", "1"), example.getPostId().toString(), -1);
                            mRibots.get(position).setMark(mRibots.get(position).getMark() - 2);
                            holder.ratingTextView.setText(mRibots.get(position).getMark().toString());
                            holder.like.setColorFilter(Color.parseColor("#FFC107"));
                            holder.deslike.setColorFilter(Color.RED);
                            mRibots.get(position).setUserMark(-1);
                        }
                        case -1:{
                            pr.addLike(user.getString("id", "1"), example.getPostId().toString(),0);
                            mRibots.get(position).setMark(mRibots.get(position).getMark()+1);
                            holder.ratingTextView.setText(mRibots.get(position).getMark().toString());
                            mRibots.get(position).setUserMark(0);
                            holder.like.setColorFilter(Color.parseColor("#FFC107"));
                            holder.deslike.setColorFilter(Color.parseColor("#FFC107"));
                            break;
                        }
                    }
                }
                //код для дизлайка
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pr.startFullNews(example);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mRibots.size();
    }


    class RibotViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.textDate)
        TextView dateTextView;
        @BindView(R.id.textAutor)
        TextView autorTextView;
        @BindView(R.id.head)
        TextView headTextView;
        @BindView(R.id.subtitle)
        TextView subTextView;
        @BindView(R.id.views)
        TextView viewsTextView;
        @BindView(R.id.rating)
        TextView ratingTextView;
        @BindView(R.id.layout)
        View layout;
        @BindView(R.id.comment)
        ImageView comment;
        @BindView(R.id.like)
        ImageView like;
        @BindView(R.id.deslike)
        ImageView deslike;
        @BindView(R.id.delete)
        ImageView delete;



        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
