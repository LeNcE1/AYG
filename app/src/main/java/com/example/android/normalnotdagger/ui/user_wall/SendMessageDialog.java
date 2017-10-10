package com.example.android.normalnotdagger.ui.user_wall;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.ui.message.dialog_item.DialogPresentr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SendMessageDialog extends DialogFragment {
    @BindView(R.id.text)
    EditText text;
    @BindView(R.id.send)
    Button send;
    @BindView(R.id.censel)
    Button censel;
    Unbinder unbinder;

    SharedPreferences user;
    String id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString("id", "e");
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.send)
    public void onSendClicked() {
        DialogPresentr presentr = new DialogPresentr();
        if (text.getText().toString().length() > 0) {
            presentr.senrMessage(user.getString("id", "e"), id, text.getText().toString().replace("\n\n", "").replace("  ", ""));
            dismiss();
        } else {
            Toast.makeText(getActivity(), "Введите текст", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.censel)
    public void onCenselClicked() {
        dismiss();
    }


}
