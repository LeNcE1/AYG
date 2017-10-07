package com.example.android.normalnotdagger.data_base;


import com.example.android.normalnotdagger.models.new_model.messages.Message;

import java.util.List;

public interface DBMVP {
    List<Message> newsMessage(List<Message> list);
    void addMessage(List<Message> list);
    List<Message> getMessage();
}
