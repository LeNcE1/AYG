package com.example.android.normalnotdagger.ui.red_profil;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.normalnotdagger.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class RedProfilFragment extends Fragment implements RedProfilMVP {

    @BindView(R.id.phone)
    EditText mPhone;
    @BindView(R.id.city)
    EditText mCity;
    @BindView(R.id.date)
    EditText mDate;
    @BindView(R.id.old_pass)
    EditText mOldPass;
    @BindView(R.id.new_pass)
    EditText mNewPass;
    @BindView(R.id.save)
    Button mSave;
    Unbinder unbinder;
    RedProfilPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.red_profil, container, false);
        mPresenter = new RedProfilPresenter();
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCity.setText(bundle.getString("city", ""));
            mPhone.setText(bundle.getString("tel", ""));
            mDate.setText(bundle.getString("dateRoj", ""));
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.save)
    public void onViewClicked() {
        // TODO: 27.12.2017 сохранение настроек
    }
}
