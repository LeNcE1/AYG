package com.example.android.normalnotdagger.ui.message.dialog_item;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.messages.UserMessage;
import com.example.android.normalnotdagger.ui.message.ListMessageSingleton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.DialogViewHolder>{
    List<UserMessage> messages = new ArrayList<>();
    DialogPresentr pr;
    String id;

    DialogAdapter(List<UserMessage> messages, String id, DialogPresentr pr) {
        this.messages = messages;
        this.id = id;
        this.pr =pr;

        Log.e("CREAD", "cread Adapter");
    }



    @Override
    public DialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DialogViewHolder vh;
        View itemLayoutView  = LayoutInflater.from(parent.getContext()).inflate(R.layout.messag_item_right, parent, false);;

        //загружаем разметку в зависимости от типа и возвращаем
        //нужный холдер
        switch (viewType)
        {
            case 0:
                itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.messag_item_right, parent, false);
                break;
            case 1:
                itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item_left, parent, false);
                break;
        }

        return new DialogViewHolder(itemLayoutView);
    }

    @Override
    public int getItemViewType(int position) {
        if (id.equals(messages.get(position).getFromId().toString()))
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }



    @Override
    public void onBindViewHolder(DialogViewHolder holder, int position) {
        UserMessage message = messages.get(position);
        holder.text.setText(message.getText());
        holder.date.setText(message.getDate());

        pr.addView(id, ListMessageSingleton.getInstance().getId());
        Log.e("message", "my ID:" + id + " Dialog id:" + message.getFromId() + " text:" + message.getText() + " read:" + message.getIsReaded());
        if (id.equals(message.getFromId().toString())) {

            if (message.getIsReaded() == 0) {
                Log.e("isRead", "hi not read");
            } else {
                Log.e("isRead", "hi yas read");
            }
        } else {

           // holder.mainView.setPadding(holder.view.getPaddingRight(), holder.view.getPaddingTop(), holder.view.getPaddingRight(), holder.view.getPaddingBottom());
        }


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }



    public class DialogViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_messag)
        TextView text;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.dialog)
        LinearLayout view;


        public DialogViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
