package com.example.android.normalnotdagger.ui.message.list_dialog;


import com.example.android.normalnotdagger.models.new_model.messages.Message;

import java.util.List;

public interface MessagersMVP {
    void creadDialogs(List<Message> list);
    void showError(String error);
    void messagIsEmpty();
    void startDiolog();
}
