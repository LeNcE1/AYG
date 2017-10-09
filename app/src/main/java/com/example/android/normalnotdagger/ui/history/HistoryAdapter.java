package com.example.android.normalnotdagger.ui.history;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.categ_model.Category;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{

    List<Category> categories = new ArrayList<>();
    HistoryPresentr pr;
    HistoryMVP mvp;
    HistoryAdapter(List<Category> categories, HistoryPresentr pr, HistoryMVP mvp){
        this.categories = categories;
        this.pr = pr;
        this.mvp = mvp;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hisory_item, parent, false);

        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final HistoryViewHolder holder, int position) {
        final Category category = categories.get(position);
        holder.name.setText(category.getTitle()+" " +category.getId());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(category.getChildrens()==0){
                    mvp.creadList(category.getId());
                }
                else{
                    if(pr.carsIsEmpty(category.getId().toString())){
                        mvp.cardsStart(category.getId());
                    }
                    else{
                        holder.start.setVisibility(View.GONE);
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    class HistoryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.start)
        ImageView start;
        @BindView(R.id.layout)
        View view;

        HistoryViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
