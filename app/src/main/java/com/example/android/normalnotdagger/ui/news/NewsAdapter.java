package com.example.android.normalnotdagger.ui.news;

import android.content.Context;
import android.content.SharedPreferences;
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

    public NewsAdapter(List<News> posts, NewsPresentr pr,SharedPreferences user) {

        this.mRibots = posts;
        this.pr = pr;
        this.user = user;
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

    @Override
    public void onBindViewHolder(final RibotViewHolder holder, int position) {
        if(position == (pag-6)){
            pr.loadNews("1",pag);
            pag+=20;
        }


        final News example = mRibots.get(position);
        //Log.e("ExampleList", "id=" + example.getId() + " url= " + example.getAvatarUrl());

//        if (example.getAvatarUrl() != null)
//            if (!example.getAvatarUrl().isEmpty()) {
//                Picasso.with(holder.itemView.getContext())
//                        .load(example.getAvatarUrl())
//                        .placeholder(R.drawable.ic_account_circle_black_24dp)
//                        .fit()
//                        .centerCrop()
//                        .into(holder.avatar);
//            }



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
        if(!user.getString("id","error").equals("error")){
            pr.addView(user.getString("id", "1"), example.getPostId().toString());
        }

        holder.autorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    pr.addLike(user.getString("id", "1"), example.getPostId().toString(), 1);
                    example.setViews((example.getMark() + 1) + "");
                    holder.ratingTextView.setText(example.getMark().toString());
                    //лайк
                }
            }
        });

        holder.deslike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!user.getString("id","error").equals("error")) {
                    pr.addLike(user.getString("id", "1"), example.getPostId().toString(), -1);
                    example.setViews((example.getMark() + 1) + "");
                    holder.ratingTextView.setText(example.getMark().toString());
                    //лайк
                }
                //код для дизлайка
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pr.startFullNews(example);

//                Log.e("image","imag: "+example.getImages());
//
//
//                // при онклике на имя автора сробатывает вот этот кусок кода
//                pr.startUserInfo(example.getUserId().toString());
//
//                // при нажатии на коменты выполнить этот код
//                //pr.startComments(example.getPostId().toString());
//
//
//
//
//                //проследить нажатия на лайк или дизлайк, в соответствии с параметром передать 1 или -1
//                if(!user.getString("id","error").equals("error")){
//                    pr.addLike(user.getString("id", "1"),example.getPostId().toString(),1);
//                    //example.setViews((example.getMark()+1)+"");
//                }
//                else{
//                    //заблочить кнопки лайка
//                }


//                itemClickAdapter(v.getContext(), holder.getAdapterPosition());
//                Log.e("click", "click id= "+ example.getPostId());

            }
        });
    }

    public void itemClickAdapter(Context context, int position) {
        //код для перехода в полную новость


//        Example example = mRibots.get(position);
//        Intent intent = new Intent(context, UserActivity.class);
//
//        intent.putExtra("Id", example.getId());
//        if (example.getAvatarUrl() == null) {
//            intent.putExtra("AvatarUrl", "");
//        } else {
//            intent.putExtra("AvatarUrl", example.getAvatarUrl());
//        }
//        intent.putExtra("FirstName", example.getFirstName());
//        intent.putExtra("LastName", example.getLastName());
//        intent.putExtra("Email", example.getEmail());
//
//        context.startActivity(intent);








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


        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
