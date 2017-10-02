package com.example.android.normalnotdagger.ui.user_info;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.ui.login.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFragment extends Fragment implements UserMVP{
    SharedPreferences user;
    View view;
    UserPresenter userPresenter;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.family)
    TextView family;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.tel)
    TextView tel;
    @BindView(R.id.count_podpis)
    TextView p;
    @BindView(R.id.exit)
    Button exit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.user_info, container, false);


        Log.e("id", "id = "+user.getString("id","error"));
        if(!user.getString("id","error").equals("error")){
            userPresenter = new UserPresenter(this,user);
            userPresenter.loadInfo();
            ButterKnife.bind(this, view);
            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user.edit().clear().commit();
                    user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                    //start логин фрагмент
                    LoginFragment youFragment = new LoginFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                            .replace(R.id.content, youFragment)
                            .addToBackStack("myStack")
                            .commit();

                }
            });
        }
        else{
            LoginFragment youFragment = new LoginFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();
            //start логин фрагмент
        }
        return view;
    }

    @Override
    public void showInfo(String name, String family, String city, String tel, String countPodpis) {
        this.name.setText("Имя:\n"+name);
        this.family.setText("Фамилия: \n"+family);
        this.city.setText("Город: \n"+city);
        this.tel.setText("Телефон: \n"+tel);
        this.p.setText("Подписчиков: \n"+countPodpis);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startProgresBar() {
        //запустить прогрес бар
    }

    @Override
    public void stopProgresBar() {
        //остановить прогрес бар
    }
}
