package com.example.android.normalnotdagger.ui.history.ItemCard;


import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.ui.history.cards.ItemCardListImages;
import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ItemCardsFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.imageLayout)
    LinearLayout imageLayout;
    //    @BindView(R.id.gridView1)
//    GridView gridView;
    List<String> imags;
    @BindView(R.id.arrowBack)
    ImageView arrowBack;
    @BindView(R.id.viewFlipper)
    ViewFlipper flipper;
    @BindView(R.id.goneLayot)
    LinearLayout goneLayot;

    private float fromPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_item_fragment, container, false);
        ButterKnife.bind(this, view);
        imags = ItemCardListImages.getInstance().getCard().getImages();
        title.setText(ItemCardListImages.getInstance().getCard().getTitle());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            text.setText(Html.fromHtml(ItemCardListImages.getInstance().getCard().getText(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            text.setText(Html.fromHtml(ItemCardListImages.getInstance().getCard().getText()));
        }


        int n = 0;
        
        for (String s : imags) {
            final ImageView imageView = new ImageView(getActivity());
            ImageView imageView2 = new ImageView(getActivity());
            imageView.setPadding(0, 0, 16, 0);
            Picasso.with(getActivity()).load(("http://9834436605.myjino.ru/" + s).replace(" ", ""))
                    .placeholder(R.drawable.ic_image_amber_500_24dp)
                    .into(imageView);
            Picasso.with(getActivity()).load(("http://9834436605.myjino.ru/" + s).replace(" ", ""))
                    .placeholder(R.drawable.ic_image_amber_500_24dp)
                    .into(imageView2);
            LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams(400, 300);
            imageView.setLayoutParams(imageViewLayoutParams);
            imageView.setTag(n);
            imageView.setClickable(true);
            final int finalN = n;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goneLayot.setVisibility(View.VISIBLE);
                    flipper.setDisplayedChild(finalN);
                    Log.e("Click", "click" + view.getTag());
                }
            });
            n++;

            imageLayout.addView(imageView);
//            LinearLayout linearLayout = new LinearLayout(getActivity());
//            linearLayout.addView(imageView);
//            flipper.addView(inflater.inflate((XmlPullParser) linearLayout,goneLayot));



            flipper.addView(imageView2);

            //flipper.addView(imageView);
        }
        flipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        fromPosition = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        float toPosition = event.getX();
                        if (fromPosition > toPosition) {
                            flipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.go_next_in));
                            flipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.go_next_out));
                            flipper.showNext();
                        } else if (fromPosition < toPosition) {
                            flipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.go_prev_in));
                            flipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.go_prev_out));
                            flipper.showPrevious();
                        }
                    default:
                        break;
                }
                return true;
            }
        });

        return view;
    }


    @Override
    public void onClick(View view) {
        Log.e("Click", "click" + view.getTag());
    }


    @OnClick(R.id.arrowBack)
    public void onViewClicked() {
        goneLayot.setVisibility(View.GONE);
    }
}

