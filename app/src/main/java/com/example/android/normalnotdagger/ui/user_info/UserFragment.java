package com.example.android.normalnotdagger.ui.user_info;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.normalnotdagger.MainActivity;
import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.ui.login.LoginFragment;
import com.example.android.normalnotdagger.ui.news.NewsFragment;
import com.example.android.normalnotdagger.ui.red_profil.RedProfilFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserFragment extends Fragment implements UserMVP {
    SharedPreferences user;
    View view;
    UserPresenter userPresenter;
    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.tel)
    TextView tel;
    @BindView(R.id.count_podpis)
    TextView p;
    @BindView(R.id.exit)
    Button exit;
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.red_profil)
    TextView mRedProfil;
    @BindView(R.id.count_posts)
    TextView mCountPosts;
    @BindView(R.id.date_roj)
    TextView mDateRoj;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.user_info, container, false);
        Log.e("id", "id = " + user.getString("id", "error"));
        ButterKnife.bind(this, view);
        if (!user.getString("id", "error").equals("error")) {
            userPresenter = new UserPresenter(this, user);
            userPresenter.loadInfo();
            ButterKnife.bind(this, view);
            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user.edit().clear().commit();
                    user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                    //start логин фрагмент
                    startActivity(new Intent(getActivity(), MainActivity.class));
//                    LoginFragment youFragment = new LoginFragment();
//                    FragmentManager fragmentManager = getFragmentManager();
//                    fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
//                            .replace(R.id.content, youFragment)
//                            .addToBackStack("myStack")
//                            .commit();

                }
            });
        } else {
            LoginFragment youFragment = new LoginFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.content, youFragment)
                    .addToBackStack("myStack")
                    .commit();
            //start логин фрагмент
        }

        if (!user.getString("id", "error").equals("error")) {
            NewsFragment youFragment = new NewsFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putString("my", "123");
            youFragment.setArguments(bundle1);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                    .replace(R.id.news_list, youFragment)
                    .addToBackStack("myStack")
                    .commit();
        }


        return view;
    }

    @Override
    public void showInfo(String name, String family, String city, String tel, String countPodpis, String dateRoj) {
        this.name.setText(name + " " + family);
        this.city.setText("Город: " + city);
        this.tel.setText("Телефон: " + tel);
        this.p.setText(countPodpis + " подписчиков");
        this.mDateRoj.setText(dateRoj + " подписчиков");
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


    @OnClick(R.id.red_profil)
    public void onViewClicked() {
        RedProfilFragment redProfilFragment = new RedProfilFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("city", city.getText().toString());
        bundle2.putString("tel", tel.getText().toString());
        bundle2.putString("dateRoj", mDateRoj.getText().toString());
        redProfilFragment.setArguments(bundle2);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.content, redProfilFragment)
                .addToBackStack("myStack")
                .commit();

    }


}
