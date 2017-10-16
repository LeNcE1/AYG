package com.example.android.normalnotdagger.ui.message.dialog_item;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.api.ServiseMenager;
import com.example.android.normalnotdagger.ui.message.ListMessageSingleton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogItemFragment extends Fragment implements SendMVP{
    @BindView(R.id.send_message)
    ImageView sendMessage;
    @BindView(R.id.text_message)
    TextView text;
    DialogPresentr pr;
    SharedPreferences user;
    ProgressDialog loading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        ButterKnife.bind(this, view);
        pr = new DialogPresentr(this);
        loading = new ProgressDialog(getActivity());
        loading.setMessage("Отправка сообщения");
        loading.setIndeterminate(true);
        loading.setCancelable(false);
        ServiseMenager.getInstance().setMvpList(this);
        ServiseMenager.getInstance().setMvp(null);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        Log.e("name", ListMessageSingleton.getInstance().getName());
        getActivity().setTitle(ListMessageSingleton.getInstance().getName());
        ListMessage youFragment = new ListMessage();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.message_list, youFragment)
                .addToBackStack("myStack")
                .commit();

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text.getText().length() == 0) {
                    Toast.makeText(getActivity(), "Введите текст", Toast.LENGTH_SHORT).show();
                } else {
                    loading.show();
                    pr.senrMessage(user.getString("id", "error"), ListMessageSingleton.getInstance().getId(), text.getText().toString().replace("\n\n", "").replace("   ", "").replace("  "," "));
                    text.setText("");
                }
            }
        });

        return view;
    }


    @Override
    public void stopProgressBar() {
        if(loading.isShowing()) {
            loading.dismiss();
        }
    }

    @Override
    public void restart() {
        ListMessageSingleton.getInstance().setList(ServiseMenager.getInstance().getDialog(ListMessageSingleton.getInstance().getId()));
        ListMessage youFragment = new ListMessage();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.message_list, youFragment)
                .addToBackStack("myStack")
                .commit();
    }
}
