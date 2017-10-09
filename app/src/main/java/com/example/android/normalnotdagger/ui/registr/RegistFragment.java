package com.example.android.normalnotdagger.ui.registr;


import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.ui.user_info.UserFragment;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistFragment extends Fragment implements RegistMVP {
    RegistPresentr pr;
    SharedPreferences user;
    @BindView(R.id.regButton)
    Button reg;
    @BindView(R.id.mail)
    TextView login;
    @BindView(R.id.pass)
    TextView pass;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.surname)
    TextView family;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.phone)
    TextView tel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.regist, container, false);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        pr = new RegistPresentr(this, user);
        ButterKnife.bind(this, view);

        //добавить проверку на валидацию полей, и на заполнения, сделать так что бы при открытии клавиатуры дизер не плыл


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("button","clik");
                if(pass.getText().length() < 5){
                    Toast.makeText(getActivity(), "Пароль должен содерать более 5 символов", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(login.getText().length()<5){
                        Toast.makeText(getActivity(), "Логин должен содерать более 5 символов", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(name.getText().length()==0){
                            Toast.makeText(getActivity(), "Введите имя", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(family.getText().length()==0){
                                Toast.makeText(getActivity(), "Введите фамилию", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                if(city.getText().length()==0){
                                    Toast.makeText(getActivity(), "Введите город", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    if(tel.getText().length()==0){
                                        Toast.makeText(getActivity(), "Введите телефон", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Pattern p = Pattern.compile("^[a-z0-9_-]{4,15}$");
                                        Matcher m = p.matcher(pass.getText().toString());
                                        if(!m.matches()){
                                            Toast.makeText(getActivity(), "Некорректный пароль", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Pattern log = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{4,20}$");
                                            Matcher mLog = log.matcher(login.getText().toString());
                                            if(!mLog.matches()){
                                                Toast.makeText(getActivity(), "Логин должен начинатся с букв и содержать цифры", Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                pr.loadRegist(login.getText().toString(),
                                                        pass.getText().toString(),
                                                        name.getText().toString(),
                                                        family.getText().toString(),
                                                        city.getText().toString(),
                                                        tel.getText().toString());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
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
