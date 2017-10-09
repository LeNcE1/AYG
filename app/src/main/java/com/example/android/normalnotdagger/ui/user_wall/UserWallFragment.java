package com.example.android.normalnotdagger.ui.user_wall;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.user_info.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserWallFragment extends Fragment implements UserWallMVP{

    View view;
    SharedPreferences user;
    UserWallPresentr presentr;
    @BindView(R.id.user_name)
    TextView name;
    @BindView(R.id.user_family)
    TextView family;
    @BindView(R.id.user_phone)
    TextView phone;
    @BindView(R.id.user_city)
    TextView city;
    @BindView(R.id.user_count_pod)
    TextView podp;
    @BindView(R.id.startmessage)
    Button message;
    @BindView(R.id.podpiska)
    Button podpiska;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_wall, container, false);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        ButterKnife.bind(this, view);
        presentr = new UserWallPresentr(this);
        final Bundle bundle = getArguments();
        if(bundle == null){
            Toast.makeText(getActivity(),"Пользователь не найден", Toast.LENGTH_LONG).show();
        }
        else {
            presentr.loadInfo(bundle.getString("avtor_id"));
            if (user.getString("id", "error").equals("error")) {
                message.setVisibility(View.INVISIBLE);
                podpiska.setVisibility(View.INVISIBLE);
            }
        }
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                SendMessageDialog deleteDialog = new SendMessageDialog();
                Bundle bundle1 = new Bundle();
                bundle1.putString("id", bundle.getString("avtor_id"));
                deleteDialog.setArguments(bundle1);
                deleteDialog.show(fm, "Change");
            }
        });
        Log.e("TEST","StartName "+ podpiska.getText().toString());
        Log.e("TEST", "isPodpis "+presentr.isPodpis(user.getString("id", "error"),bundle.getString("avtor_id")) + "");
        if(!presentr.isPodpis(user.getString("id", "error"),bundle.getString("avtor_id"))){
            podpiska.setText("Отписаться");
        }
        else{
            podpiska.setText("Подписаться");
        }
        podpiska.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TEST", "FinishName "+podpiska.getText().toString());
                if(podpiska.getText().equals("Отписаться")){
                    presentr.deletePod(user.getString("id", "error"),bundle.getString("avtor_id"));

                }
                else{
                    presentr.addPod(user.getString("id", "error"),bundle.getString("avtor_id"));
                }
               // Toast.makeText(getActivity(),"Подписаться на обновления to do", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void showInfo(UserModel user) {
        name.setText(user.getName());
        family.setText(user.getFamily());
        phone.setText(user.getTel());
        city.setText(user.getCity());
        podp.setText(user.getSubNum());
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(),"Ошибка интернет соеденения", Toast.LENGTH_LONG).show();
    }

    @Override
    public void startProgresBar() {

    }

    @Override
    public void stopProgresBar() {

    }

    @Override
    public void replaseNameButton() {
        switch (podpiska.getText().toString()){
            case "Отписаться":{
                podpiska.setText("Подписаться");
                break;
            }
            case "Подписаться":{
                podpiska.setText("Отписаться");
                break;
            }
        }
    }
}
