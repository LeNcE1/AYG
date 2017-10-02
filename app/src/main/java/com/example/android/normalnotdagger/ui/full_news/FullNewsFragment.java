package com.example.android.normalnotdagger.ui.full_news;

import android.app.Fragment;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FullNewsFragment extends Fragment implements FullNewsMVP{

    @BindView(R.id.textDate)
    TextView dateTextView;
    @BindView(R.id.textAutor)
    TextView autorTextView;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.shorts)
    TextView shorts;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.views)
    TextView viewsTextView;
    @BindView(R.id.rating)
    TextView ratingTextView;
    @BindView(R.id.comment)
    ImageView comment;
    @BindView(R.id.like)
    ImageView like;
    @BindView(R.id.deslike)
    ImageView deslike;
    @BindView(R.id.gallery_imag1)
    ImageView gallery_imag1;
    @BindView(R.id.gallery_imag2)
    ImageView gallery_imag2;
    @BindView(R.id.gallery_imag3)
    ImageView gallery_imag3;



    List<String> getList(String mass){
        List<String> rez = new ArrayList<>();


        JSONArray m = null;
        try {
            m = new JSONArray(mass.replace("[","[\"").replace(",","\",\"").replace("]","\"]"));
            for(int i = 0; i < m.length(); i++) {
                rez.add(m.get(i).toString());
            }
        } catch (JSONException e) {
            Log.e("error", "dont List: "+e);
            e.printStackTrace();
        }

        return rez;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.full_news_fragment, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        List<String> imags = new ArrayList<>();

        if(bundle!=null){
            text.setText( bundle.getString("text"));
            shorts.setText(bundle.getString("short"));
            title.setText(bundle.getString("title"));
            dateTextView.setText(bundle.getString("data"));
            autorTextView.setText(bundle.getString("avtor"));
            viewsTextView.setText(bundle.getString("view"));
            ratingTextView.setText(bundle.getString("reyting"));
            imags = getList(bundle.getString("imags"));
            Log.e("imags",imags.size()+"");
            switch (imags.size()){
                case 0:{
                    break;
                }
                case 1:{
                    gallery_imag1.setVisibility(View.VISIBLE);
                    Picasso.with(gallery_imag1.getContext()).load(("http://9834436605.myjino.ru/"+imags.get(0)).replace(" ",""))
                            //.placeholder(R.drawable.ic_camera_alt_black_24dp)
                            .fit()
                            .centerCrop()
                            .into(gallery_imag1);
                    break;
                }
                case 2:{
                    gallery_imag1.setVisibility(View.VISIBLE);
                    Log.e("imag", "1: " + "http://9834436605.myjino.ru/"+imags.get(0));
                    Picasso.with(gallery_imag1.getContext()).load(("http://9834436605.myjino.ru/"+imags.get(0)).replace(" ",""))
                            //.placeholder(R.drawable.ic_camera_alt_black_24dp)
                            .fit()
                            .centerCrop()
                            .into(gallery_imag1);

                    gallery_imag2.setVisibility(View.VISIBLE);
                    Log.e("imag", "2: " + ("http://9834436605.myjino.ru/"+imags.get(1)).replace(" ",""));
                    Picasso.with(gallery_imag2.getContext()).load(("http://9834436605.myjino.ru/"+imags.get(1)).replace(" ",""))
                            //.placeholder(R.drawable.ic_camera_alt_black_24dp)
                            .fit()
                            .centerCrop()
                            .into(gallery_imag2);
                    break;
                }
                case 3:{
                    Log.e("imag", "1: " + "http://9834436605.myjino.ru/"+imags.get(0));
                    gallery_imag1.setVisibility(View.VISIBLE);
                    Picasso.with(gallery_imag1.getContext()).load(("http://9834436605.myjino.ru/"+imags.get(0)).replace(" ",""))
                            //.placeholder(R.drawable.ic_camera_alt_black_24dp)
                            .fit()
                            .centerCrop()
                            .into(gallery_imag1);
                    Log.e("imag", "2: " + "http://9834436605.myjino.ru/"+imags.get(1));
                    gallery_imag2.setVisibility(View.VISIBLE);
                    Picasso.with(gallery_imag1.getContext()).load(("http://9834436605.myjino.ru/"+imags.get(1)).replace(" ",""))
                            //.placeholder(R.drawable.ic_camera_alt_black_24dp)
                            .fit()
                            .centerCrop()
                            .into(gallery_imag2);
                    Log.e("imag", "3: " + "http://9834436605.myjino.ru/"+imags.get(2));
                    gallery_imag3.setVisibility(View.VISIBLE);
                    Picasso.with(gallery_imag1.getContext()).load(("http://9834436605.myjino.ru/"+imags.get(2)).replace(" ",""))
                            //.placeholder(R.drawable.ic_camera_alt_black_24dp)
                            .fit()
                            .centerCrop()
                            .into(gallery_imag3);
                    break;
                }
            }
        }
        return view;
    }
}
