package com.example.android.normalnotdagger.ui.map;


import android.util.Log;
import android.widget.TextView;

import com.example.android.normalnotdagger.api.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapPresentr {
    MapMVP mvp;
    MapPresentr(MapMVP mvp){
        this.mvp = mvp;
    }

    void loadMap() {
        mvp.startProgresBar();
        App.getApi().getMap().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String json = null;
                    try {
                        json = response.body().string();
                    } catch (IOException e) {
                        mvp.showError("?????? ??????????");
                        mvp.stopProgresBar();
                        e.printStackTrace();
                    }
               // String json = "{\"map\":[\"{\"type\":\"Feature\",\"properties\":{\"color\":\"#bada55\",\"fillColor\":\"\",\"opacity\":\"0.5\",\"fillOpacity\":\"0.2\",\"backend_layer_id\":\"42\",\"text\":\"u0425u043eu0440u0438u043du0446u044b\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[\"106.18972778320311\",\"51.95611479866179\"],[\"106.18972778320311\",\"51.95611479866179\"]]]}}\",\"{\"type\":\"Feature\",\"properties\":{\"color\":\"#e74c3c\",\"fillColor\":\"#16a085\",\"opacity\":\"0.5\",\"fillOpacity\":\"0.4\",\"backend_layer_id\":45,\"text\":\"test\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[\"110.3851318359375\",\"53.028000167735165\"],[\"110.8685302734375\",\"52.47608904123904\"],[\"110.3851318359375\",\"53.028000167735165\"]]]}}\"]}";
                //Log.e("json", "Not gson:" + json);
                    String newStr = "";
                    String g3 ="\\";
//                    for(int i = 0; i < json.length() - 1; i++) {
//                        newStr += json.charAt(i) != g3.charAt(0) && json.charAt(i) != g3.charAt(0) ? json.charAt(i) : "";
//                    }
                        newStr = json.replace(String.valueOf(g3.charAt(0)),"");
                        String g = "[\"{";
                        String g1 = "}\",\"{";
                        String g2 = "}\"]";
                      //  Log.e("respons",(g + "|"+g1));
                        String str1 = newStr.replace(g,"[{");
                        String str2 = str1.replace(g1,"},{");
                        String str3 = str2.replace(g2,"}]");
                     //   Log.e("respons",str3);
                        JSONObject dataJsonObj = null;
                        String secondName = "";
                        try {
                            dataJsonObj = new JSONObject(str3);
                            secondName = dataJsonObj.getString("map");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String g4= "{\"map\":";
                        str1 = str3.replace(g4,"");
                        JSONArray mass = null;
                        try {
                        //    Log.e("respons", str1);
                            mass = new JSONArray(str1);
                            mvp.showMap(mass);



                            mvp.stopProgresBar();
                       //     Log.e("respons Finish","jason1"+mass.getJSONObject(2));
                        } catch (JSONException e) {
                            Log.e("error,", e.getMessage());
                            mvp.showError("?????? ??????????? ?????");
                            mvp.stopProgresBar();
                            e.printStackTrace();
                        }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("error", "error: "+ t);
                mvp.showError("?????? ??????????");
                mvp.stopProgresBar();
            }
        });


    }

}
