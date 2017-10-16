package com.example.android.normalnotdagger.api;

import com.example.android.normalnotdagger.models.new_model.messages.Message;
import com.example.android.normalnotdagger.models.new_model.messages.UserMessage;
import com.example.android.normalnotdagger.ui.message.ListMessageSingleton;
import com.example.android.normalnotdagger.ui.message.dialog_item.SendMVP;
import com.example.android.normalnotdagger.ui.message.list_dialog.MessagersMVP;

import java.util.ArrayList;
import java.util.List;



public class ServiseMenager {
    private static final ServiseMenager ourInstance = new ServiseMenager();

    public static ServiseMenager getInstance() {
        return ourInstance;
    }

    private ServiseMenager() {
    }

    public  List<Message> list;

    public void setList(List<Message> list) {
        this.list = list;
    }

    public List<Message> getList() {
        return list;
    }

    public boolean newMessag;

    public void setNewMessag(boolean newMessag) {
        this.newMessag = newMessag;
    }

    public boolean getNewMessag() {
        return newMessag;
    }

    public List<UserMessage> getDialog(String to_id){
        List<UserMessage> rez = new ArrayList<>();
        for(Message i: list){
            if(i.getUserId().equals(to_id)){
                rez = i.getUserMessages();
            }
        }
        return rez;
    }

    public String getLastNewMessage(String user_id){
        String messagLast = null;
        for(Message i : list){
            if(i.getUserId().equals(user_id)){
                messagLast = i.getUserMessages().get(i.getUserMessages().size()-1).getText();
            }
        }
        return messagLast;
    }

    public MessagersMVP mvp = null;

    public void setMvp(MessagersMVP mvp) {
        this.mvp = mvp;
    }
    public void startReplas(){
        if(mvp != null){
            mvp.reset();
        }
    }

    public SendMVP mvpList = null;

    public void setMvpList(SendMVP mvpList) {
        this.mvpList = mvpList;
    }

    public void startReplasDialog(){
        if(mvpList != null){
            mvpList.restart();
        }
    }
}
