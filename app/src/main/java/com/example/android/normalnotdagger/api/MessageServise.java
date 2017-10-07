package com.example.android.normalnotdagger.api;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.example.android.normalnotdagger.MainActivity;
import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.messages.MessagesModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageServise extends Service  {

    static final int MSG_SAY_HELLO = 1;

    /**
     * Handler of incoming messages from clients.
     */
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:

                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());




    List<Integer> count = new ArrayList<>();
    int mainCount = 0;
    private Timer mTimer;
    private MyTimer mMyTimer;
    SharedPreferences user;
    boolean flag = false;
    int ret = 0;






    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {




        mTimer = new Timer();
        mMyTimer = new MyTimer();
        user = getSharedPreferences("user", Context.MODE_PRIVATE);
        mTimer.schedule(mMyTimer, 1000, 5 * 1000);
        Log.e("servise","Служба запущена" + user.getString("id", "1"));
        return super.onStartCommand(intent, flags, startId);
    }


    int getNewMessage(){
        return ret;
    }
    void set(int s){
        this.ret = s;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMyTimer.cancel();
        Log.e("servise","Служба остановлена");
    }


    void show(){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_mail_outline_amber_500_24dp)
                        .setContentTitle("AYG")
                        .setContentText("Новое сообщение");
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
        count = new ArrayList<>();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("servise","Служба создана");
    }
    class MyTimer extends TimerTask {
        @Override
        public void run() {
            if(!user.getString("id", "n").equals("n")) {
                Log.e("servise", "cread");
                App.getApi().getMessages(user.getString("id", "0")).enqueue(new Callback<MessagesModel>() {
                    @Override
                    public void onResponse(Call<MessagesModel> call, Response<MessagesModel> response) {
                        int counts = 0;
                        if (count.isEmpty()) {
                            for (int i = 0; i < response.body().getMessages().size(); i++) {
                                count.add(response.body().getMessages().get(i).getCount());
                            }
                            Log.e("servise", "one Recqest");
                            Log.e("servise", "count diolog:"+count.size());
                        } else {
                            if (count.size() != response.body().getMessages().size()) {
                                Log.e("servise", "new Dialog");
                                ret = 1;
                                flag=true;
                                show();
                                //mvp.showPush(1);
                            } else {
                                for (int i = 0; i < response.body().getMessages().size(); i++) {
                                    if (count.get(i) != response.body().getMessages().get(i).getCount())
                                        counts++;
                                }
                                Log.e("servise", "Message counts: "+counts);
                                if (counts != mainCount) {
                                    Log.e("servise", "new message counts: " + (counts-mainCount));
                                    //mvp.showPush(counts - mainCount);
                                    show();
                                    ret = counts - mainCount;
                                }

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MessagesModel> call, Throwable t) {
                        Log.e("servise", "not conect");
                    }
                });
            }
            else{
                Log.e("servise", "nou USER");
            }
        }
    }
}
