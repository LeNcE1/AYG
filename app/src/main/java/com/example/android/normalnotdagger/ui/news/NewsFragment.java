package com.example.android.normalnotdagger.ui.news;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.normalnotdagger.R;
import com.example.android.normalnotdagger.models.new_model.news.News;
import com.example.android.normalnotdagger.ui.commits.ICommentsFragment;
import com.example.android.normalnotdagger.ui.full_news.FullNewsFragment;
import com.example.android.normalnotdagger.ui.user_wall.UserWallFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class NewsFragment extends Fragment implements NewsMVP{
    List<News> posts = new ArrayList<>();
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    NewsPresentr pr;
    SharedPreferences user;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);


        posts = new ArrayList<>();

        pr = new NewsPresentr(this);
        pr.loadNews("1",0);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(newsAdapter);
        newsAdapter = new NewsAdapter(posts, pr, user);
        recyclerView.setAdapter(newsAdapter);

        return recyclerView;
    }

    @Override
    public void showNews(List<News> posts) {
        Log.e("testPresentr", posts.get(0).getText());
        newsAdapter.addPosts(posts);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "Ошибка соеденения с интернетом", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showIsEmpty() {
        Toast.makeText(getActivity(), "Новостей нет", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addLike() {
       // Toast.makeText(getActivity(), "Add like", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addLikeError(String res) {
        Toast.makeText(getActivity(), res,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProgresBar() {
        //запустить прогрес бар
    }

    @Override
    public void stopProgresBar() {
        //остановить прогрес бар
    }

    @Override
    public void startUserInfo(String avtor_id) {
        UserWallFragment youFragment = new UserWallFragment();
        Bundle bundle = new Bundle();
        bundle.putString("avtor_id", avtor_id);
        youFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.content, youFragment)
                .addToBackStack("myStack")
                .commit();
    }

    @Override
    public void startComments(String post_id) {
        ICommentsFragment youFragment = new ICommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("post_id", post_id);
        youFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.content, youFragment)
                .addToBackStack("myStack")
                .commit();

    }
    @Override
    public void startFullNews(News post) {
        FullNewsFragment youFragment = new FullNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("post_id", post.getPostId().toString());
        bundle.putString("title", post.getTitle());
        bundle.putString("short", post.getShort());
        bundle.putString("text", post.getText());
        bundle.putString("data", post.getDate());
        bundle.putString("avtor", post.getUserLogin());
        bundle.putString("view", post.getViews());
        bundle.putString("reyting", post.getMark().toString());
        List<String> imags = new ArrayList<>();
        for(int i = 0; i < post.getImages().size(); i++ ){
            imags.add(post.getImages().get(i).toString());
        }
        bundle.putString("imags", imags.toString());

        youFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.content, youFragment)
                .addToBackStack("myStack")
                .commit();

    }


}
