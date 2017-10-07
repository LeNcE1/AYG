package com.example.android.normalnotdagger.ui.message;

import com.example.android.normalnotdagger.models.new_model.messages.UserMessage;
import java.util.List;



public class ListMessageSingleton {
    private static final ListMessageSingleton ourInstance = new ListMessageSingleton();

    public static ListMessageSingleton getInstance() {
        return ourInstance;
    }

    private ListMessageSingleton() {}

    List<UserMessage> list;
    String name;
    String id;

    public void setName(String name) {
        this.name = name;
    }

    public List<UserMessage> getList() {
        return list;
    }

    public String getName() {
        return name;
    }

    public void setList(List<UserMessage> list) {
        this.list = list;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
