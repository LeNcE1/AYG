package com.example.android.normalnotdagger.ui.history.ItemCard;


import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.ui.history.cards.ItemCardListImages;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemCardsFragment extends Fragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.images)
    GridLayout images;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_item_fragment, container, false);
        ButterKnife.bind(this, view);
        title.setText(ItemCardListImages.getInstance().getCard().getTitle());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            text.setText(Html.fromHtml(ItemCardListImages.getInstance().getCard().getText(), Html.FROM_HTML_MODE_LEGACY));
        }
        else{
            text.setText(Html.fromHtml(ItemCardListImages.getInstance().getCard().getText()));
        }


        return view;
    }


}
