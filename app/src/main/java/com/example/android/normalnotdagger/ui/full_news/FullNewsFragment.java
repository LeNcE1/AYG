package com.example.android.normalnotdagger.ui.full_news;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.news.News;
import com.example.android.normalnotdagger.ui.commits.ICommentsFragment;
import com.example.android.normalnotdagger.ui.news.NewsMVP;
import com.example.android.normalnotdagger.ui.news.NewsPresentr;
import com.example.android.normalnotdagger.ui.user_wall.UserWallFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FullNewsFragment extends Fragment implements NewsMVP {

    SharedPreferences user;
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
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        final Bundle bundle = getArguments();
        List<String> imags = new ArrayList<>();

        if(bundle!=null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                text.setText(Html.fromHtml(bundle.getString("text"), Html.FROM_HTML_MODE_LEGACY));
            }
            else{
                text.setText(Html.fromHtml(bundle.getString("text")));
            }

            shorts.setText(bundle.getString("short"));
            title.setText(bundle.getString("title"));
            dateTextView.setText(bundle.getString("data"));
            autorTextView.setText(bundle.getString("avtor"));
            viewsTextView.setText(bundle.getString("view"));
            ratingTextView.setText(bundle.getString("reyting"));
            if (bundle.getString("like").equals("1")) {
                like.setColorFilter(Color.RED);
            }
            else{
                if(bundle.getString("like").equals("0")){

                }
                else{
                    deslike.setColorFilter(Color.RED);
                }
            }


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
            final NewsPresentr pr = new NewsPresentr(this, user);
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!user.getString("id","error").equals("error")) {
                        switch (bundle.getString("like")){
                            case "0":{
                                pr.addLike(user.getString("id", "1"), bundle.getString("post_id"), 1);
                                Integer i = Integer.valueOf(ratingTextView.getText().toString());
                                i++;
                                ratingTextView.setText(i.toString());
                                like.setColorFilter(Color.RED);
                                deslike.setColorFilter(Color.parseColor("#FFC107"));
                                bundle.putString("like","1");
                                break;
                            }
                            case "1":{
                                pr.addLike(user.getString("id", "1"), bundle.getString("post_id"),0);
                                bundle.putString("like","0");
                                Integer i = Integer.valueOf(ratingTextView.getText().toString());
                                i--;
                                ratingTextView.setText(i.toString());
                                like.setColorFilter(Color.parseColor("#FFC107"));
                                deslike.setColorFilter(Color.parseColor("#FFC107"));
                                break;

                            }
                            case "-1":{
                                pr.addLike(user.getString("id", "1"), bundle.getString("post_id"), 1);
                                Integer i = Integer.valueOf(ratingTextView.getText().toString());
                                i--;
                                i--;
                                ratingTextView.setText(i.toString());
                                bundle.putString("like","1");
                                deslike.setColorFilter(Color.parseColor("#FFC107"));
                                like.setColorFilter(Color.RED);
                                break;
                            }
                        }
                    }
                }
            });
            deslike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!user.getString("id","error").equals("error")) {
                        switch (bundle.getString("like")){
                            case "0":{
                                pr.addLike(user.getString("id", "1"), bundle.getString("post_id"), -1);
                                Integer i = Integer.valueOf(ratingTextView.getText().toString());
                                i--;
                                bundle.putString("like","-1");
                                ratingTextView.setText(i.toString());
                                deslike.setColorFilter(Color.RED);
                                break;
                            }
                            case "1":{
                                pr.addLike(user.getString("id", "1"), bundle.getString("post_id"), -1);
                                Integer i = Integer.valueOf(ratingTextView.getText().toString());
                                i--;
                                i--;
                                ratingTextView.setText(i.toString());
                                like.setColorFilter(Color.parseColor("#FFC107"));
                                deslike.setColorFilter(Color.RED);
                                bundle.putString("like","-1");
                            }
                            case "-1":{
                                pr.addLike(user.getString("id", "1"), bundle.getString("post_id"),0);
                                Integer i = Integer.valueOf(ratingTextView.getText().toString());
                                i++;
                                ratingTextView.setText(i.toString());
                                bundle.putString("like","0");
                                like.setColorFilter(Color.parseColor("#FFC107"));
                                deslike.setColorFilter(Color.parseColor("#FFC107"));
                                break;
                            }
                        }
                    }

                }
            });
            autorTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(autorTextView.getText().length()==0){
                        Log.e("avtor","avtor");
                    }
                    else{
                        UserWallFragment youFragment = new UserWallFragment();
                        Bundle bundle1 = new Bundle();
                        Log.e("avtor_id",  bundle.getString("avtor_id") + "ee");
                        bundle1.putString("avtor_id", bundle.getString("avtor_id"));
                        youFragment.setArguments(bundle1);
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                                .replace(R.id.content, youFragment)
                                .addToBackStack("myStack")
                                .commit();
                    }
                }
            });

            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ICommentsFragment youFragment = new ICommentsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("post_id", bundle.getString("post_id"));
                    youFragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                            .replace(R.id.content, youFragment)
                            .addToBackStack("myStack")
                            .commit();
                }
            });
        }
        return view;
    }

    @Override
    public void showNews(List<News> posts) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showIsEmpty() {

    }

    @Override
    public void addLike() {

    }

    @Override
    public void addLikeError(String res) {

    }

    @Override
    public void startProgresBar() {

    }

    @Override
    public void stopProgresBar() {

    }

    @Override
    public void startUserInfo(String avtor_id) {

    }

    @Override
    public void startComments(String post_id) {

    }

    @Override
    public void showDeletePost(String status) {

    }

    @Override
    public void startFullNews(News news) {

    }
}
