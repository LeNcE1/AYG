package com.example.android.normalnotdagger.ui.message.list_dialog;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.messages.Message;
import com.example.android.normalnotdagger.ui.message.ListMessageSingleton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends  RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{
    List<Message> messages = new ArrayList<>();

    MessagersMVP mvp;

    MessageAdapter(List<Message> list ,MessagersMVP mvp){
        this.messages = list;
        this.mvp = mvp;

    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dialog_item, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, int position) {
        final Message message = messages.get(position);
        holder.date.setText(message.getUserMessages().get(message.getUserMessages().size()-1).getDate());
        holder.lastMessag.setText(message.getUserMessages().get(message.getUserMessages().size()-1).getText());
        holder.name.setText(message.getUserLogin());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("messages", message.getUserMessages().size()+"");
                ListMessageSingleton.getInstance().setList(message.getUserMessages());
                ListMessageSingleton.getInstance().setName(message.getUserLogin());
                ListMessageSingleton.getInstance().setId(message.getUserId());
                mvp.startDiolog();


            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.dialog)
        View view;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.last_messag)
        TextView lastMessag;

        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
