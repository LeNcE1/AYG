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
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.news.News;
import com.example.android.normalnotdagger.ui.commits.ICommentsFragment;
import com.example.android.normalnotdagger.ui.news.NewsMVP;
import com.example.android.normalnotdagger.ui.news.NewsPresentr;
import com.example.android.normalnotdagger.ui.user_info.UserFragment;
import com.example.android.normalnotdagger.ui.user_wall.UserWallFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
    @BindView(R.id.arrowBack)
    ImageView arrowBack;
    @BindView(R.id.viewFlipper)
    ViewFlipper flipper;
    @BindView(R.id.goneLayot)
    LinearLayout goneLayot;
    @BindView(R.id.imageLayout)
    LinearLayout imageLayout;
    @BindView(R.id.hos)
    HorizontalScrollView hos;

    private float fromPosition;

    List<String> getList(String mass) {
        List<String> rez = new ArrayList<>();
        JSONArray m = null;
        try {
            m = new JSONArray(mass.replace("[", "[\"").replace(",", "\",\"").replace("]", "\"]"));
            for (int i = 0; i < m.length(); i++) {
                rez.add(m.get(i).toString());
            }
        } catch (JSONException e) {
            Log.e("error", "dont List: " + e);
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

        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                text.setText(Html.fromHtml(bundle.getString("text"), Html.FROM_HTML_MODE_LEGACY));
            } else {
                text.setText(Html.fromHtml(bundle.getString("text")));
            }

            shorts.setText(bundle.getString("short"));
            title.setText(bundle.getString("title"));
            dateTextView.setText(bundle.getString("data"));
            SpannableString spannableString = new SpannableString(bundle.getString("avtor"));
            spannableString.setSpan(new UnderlineSpan(), 0, bundle.getString("avtor").length(), 0);
            autorTextView.setText(spannableString);
            viewsTextView.setText(bundle.getString("view"));
            ratingTextView.setText(bundle.getString("reyting"));
            if (bundle.getString("like").equals("1")) {
                like.setColorFilter(Color.RED);
            } else {
                if (bundle.getString("like").equals("0")) {

                } else {
                    deslike.setColorFilter(Color.RED);
                }
            }

            int n = 0;


            imags = getList(bundle.getString("imags"));
            Log.e("Imags", imags + "_" + imags.get(0).isEmpty());
            if (!imags.get(0).isEmpty()) {
                Log.e("imags", imags.size() + "");
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
            } else
                hos.setVisibility(View.GONE);
            final NewsPresentr pr = new NewsPresentr(this, user);
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!user.getString("id", "error").equals("error")) {
                        switch (bundle.getString("like")) {
                            case "0": {
                                pr.addLike(user.getString("id", "1"), bundle.getString("post_id"), 1);
                                Integer i = Integer.valueOf(ratingTextView.getText().toString());
                                i++;
                                ratingTextView.setText(i.toString());
                                like.setColorFilter(Color.RED);
                                deslike.setColorFilter(Color.parseColor("#FFC107"));
                                bundle.putString("like", "1");
                                break;
                            }
                            case "1": {
                                pr.addLike(user.getString("id", "1"), bundle.getString("post_id"), 0);
                                bundle.putString("like", "0");
                                Integer i = Integer.valueOf(ratingTextView.getText().toString());
                                i--;
                                ratingTextView.setText(i.toString());
                                like.setColorFilter(Color.parseColor("#FFC107"));
                                deslike.setColorFilter(Color.parseColor("#FFC107"));
                                break;

                            }
                            case "-1": {
                                pr.addLike(user.getString("id", "1"), bundle.getString("post_id"), 1);
                                Integer i = Integer.valueOf(ratingTextView.getText().toString());
                                i--;
                                i--;
                                ratingTextView.setText(i.toString());
                                bundle.putString("like", "1");
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
                    if (!user.getString("id", "error").equals("error")) {
                        switch (bundle.getString("like")) {
                            case "0": {
                                pr.addLike(user.getString("id", "1"), bundle.getString("post_id"), -1);
                                Integer i = Integer.valueOf(ratingTextView.getText().toString());
                                i--;
                                bundle.putString("like", "-1");
                                ratingTextView.setText(i.toString());
                                deslike.setColorFilter(Color.RED);
                                break;
                            }
                            case "1": {
                                pr.addLike(user.getString("id", "1"), bundle.getString("post_id"), -1);
                                Integer i = Integer.valueOf(ratingTextView.getText().toString());
                                i--;
                                i--;
                                ratingTextView.setText(i.toString());
                                like.setColorFilter(Color.parseColor("#FFC107"));
                                deslike.setColorFilter(Color.RED);
                                bundle.putString("like", "-1");
                            }
                            case "-1": {
                                pr.addLike(user.getString("id", "1"), bundle.getString("post_id"), 0);
                                Integer i = Integer.valueOf(ratingTextView.getText().toString());
                                i++;
                                ratingTextView.setText(i.toString());
                                bundle.putString("like", "0");
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
                    if (autorTextView.getText().length() == 0) {
                        Log.e("avtor", "avtor");
                    } else {
                        if (!bundle.getString("avtor").equals("Администратор")) {
                            if (bundle.getString("avtor_id").equals(user.getString("id", "e"))) {
                                UserFragment youFragment = new UserFragment();
                                FragmentManager fragmentManager = getFragmentManager();
                                fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                                        .replace(R.id.content, youFragment)
                                        .addToBackStack("myStack")
                                        .commit();
                            } else {
                                UserWallFragment youFragment = new UserWallFragment();
                                Bundle bundle1 = new Bundle();
                                Log.e("avtor_id", bundle.getString("avtor_id") + "ee");
                                bundle1.putString("avtor_id", bundle.getString("avtor_id"));
                                youFragment.setArguments(bundle1);
                                FragmentManager fragmentManager = getFragmentManager();
                                fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                                        .replace(R.id.content, youFragment)
                                        .addToBackStack("myStack")
                                        .commit();
                            }
                        }
                    }
                }
            });

            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ICommentsFragment youFragment = new ICommentsFragment();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("post_id", bundle.getString("post_id"));
                    youFragment.setArguments(bundle1);
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

    @OnClick(R.id.arrowBack)
    public void onViewClicked() {
        goneLayot.setVisibility(View.GONE);

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

    @Override
    public void startMyInfo() {

    }



}
