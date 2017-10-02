package com.example.android.normalnotdagger.ui.login;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.ui.registr.RegistFragment;
import com.example.android.normalnotdagger.ui.user_info.UserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment implements LoginMVP{
    LoginPresentr loginPresentr;
    SharedPreferences user;
    @BindView(R.id.login)
    EditText login;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.buttonLogin)
    Button avtor;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton create;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login, container, false);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        loginPresentr = new LoginPresentr(this,user);
        ButterKnife.bind(this, view);
        avtor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresentr.loadLogin(login.getText().toString(), pass.getText().toString());
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistFragment youFragment = new RegistFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                        .replace(R.id.content, youFragment)
                        .addToBackStack("myStack")
                        .commit();
            }
        });


        return view;
    }

    @Override
    public void showStatus(String status) {
        Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();
        UserFragment youFragment = new UserFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.content, youFragment)
                .addToBackStack("myStack")
                .commit();

    }

    @Override
    public void showError(String error) {

        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
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
