package com.example.android.normalnotdagger.api;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
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



    List<Integer> count = new ArrayList<>();
    int mainCount = 0;
    private Timer mTimer;
    private MyTimer mMyTimer;
    SharedPreferences user;
    int ret = 0;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMyTimer.cancel();
        Log.e("servise","Служба остановлена");
    }

    void show(String name, String text){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_mail_outline_amber_500_24dp)
                        .setContentTitle(name)
                        .setContentText(text);
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
                                show(response.body().getMessages().get(response.body().getMessages().size()-1).getUserLogin(),
                                        response.body().getMessages().get(response.body().getMessages().size()-1).getUserMessages().get(response.body().getMessages().get(response.body().getMessages().size()-1).getUserMessages().size()-1).getText());;
                            } else {
                                String name = null;
                                String text = null;
                                for (int i = 0; i < response.body().getMessages().size(); i++) {
                                    if (count.get(i) != response.body().getMessages().get(i).getCount()) {
                                        if(!response.body().getMessages().get(i).getUserId().equals(user.getString("id","e"))) {
                                            name = response.body().getMessages().get(i).getUserLogin();
                                            text = response.body().getMessages().get(i).getUserMessages().get(response.body().getMessages().get(i).getUserMessages().size() - 1).getText();
                                        }
                                    }
                                }
                                if( name != null){
                                    show(name, text);
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
