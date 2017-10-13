package com.example.android.normalnotdagger.ui.history.cards;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.android.normalnotdagger.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.normalnotdagger.R.id.gallery_imag1;

public class ImagsAdapter extends BaseAdapter {
    List<String> imags = new ArrayList<>();
    private Context mContext;

    public ImagsAdapter(Context c, List<String> imags){
        this.mContext = c;
        this.imags = imags;
    }

    @Override
    public int getCount() {
        return imags.size();
    }

    @Override
    public Object getItem(int i) {
        return imags.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(256, 256));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) view;
        }

        Picasso.with(mContext).load(("http://9834436605.myjino.ru/" + imags.get(i)).replace(" ", ""))
                .placeholder(R.drawable.ic_image_amber_500_24dp)
                .fit()
                .centerCrop()
                .into(imageView);
        return imageView;
    }
}
