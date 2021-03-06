package com.example.android.normalnotdagger.ui.news;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.example.android.normalnotdagger.ui.user_info.UserFragment;
import com.example.android.normalnotdagger.ui.user_wall.UserWallFragment;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment implements NewsMVP {
    private static final String LAYOUT_MANAGER_KEY = "news_state";
    List<News> posts = new ArrayList<>();
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    NewsPresentr pr;
    SharedPreferences user;
    String pod = null;
    String m = null;
    ProgressDialog loading;
    Parcelable mLayoutManagerState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        pr = new NewsPresentr(this, user);

        Bundle bundle = getArguments();

        loading = new ProgressDialog(getActivity());
        loading.setMessage("Загрузка новостей");
        loading.setIndeterminate(true);
        loading.setCancelable(false);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        if (bundle != null) {
            Log.e("Bundle", "not NULL");
            pod = bundle.getString("pod");
            m = bundle.getString("my");
            if (m != null) {
                recyclerView.setAdapter(newsAdapter);
                newsAdapter = new NewsAdapter(posts, pr, user, pod, m);
                recyclerView.setAdapter(newsAdapter);
                pr.loadNewsMy(user.getString("id", "error"), 0);
            }
        }
        if (pod == null) {
            if (m == null) {
                Log.e("pod", "NULL");
                pr.loadNews(user.getString("id", "1"), 0);
            }
        } else {
            if (!user.getString("id", "n").equals("n")) {
                Log.e("pod", " not NULL" + bundle.getString("pod"));
                pr.loadNewspod(user.getString("id", "1"), 0);
            } else {
                Toast.makeText(getActivity(), "Авторезуйтесь", Toast.LENGTH_SHORT).show();
            }
        }


        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(newsAdapter);
        newsAdapter = new NewsAdapter(posts, pr, user, pod, m);
        recyclerView.setAdapter(newsAdapter);

        return recyclerView;

    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(LAYOUT_MANAGER_KEY);
            recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }

    }


    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        state.putParcelable(LAYOUT_MANAGER_KEY, recyclerView.getLayoutManager().onSaveInstanceState());

    }

    @Override
    public void showNews(List<News> posts) {
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
        Toast.makeText(getActivity(), res, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProgresBar() {
        loading.show();
        //запустить прогрес бар
    }

    @Override
    public void stopProgresBar() {
        loading.dismiss();
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
    public void showDeletePost(String status) {
        Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();
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
        bundle.putString("avtor", post.getUserId() == 0 ? "Администратор" : post.getUserLogin());
        Log.e("News", "avtor " + post.getUserId());
        bundle.putString("avtor_id", post.getUserId().toString());
        bundle.putString("view", post.getViews());
        bundle.putString("reyting", post.getMark().toString());
        bundle.putString("like", post.getUserMark().toString());
        List<String> imags = new ArrayList<>();
        for (int i = 0; i < post.getImages().size(); i++) {
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

    @Override
    public void startMyInfo() {
        UserFragment youFragment = new UserFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.content, youFragment)
                .addToBackStack("myStack")
                .commit();
    }

    @Override
    public void replase() {
        NewsFragment youFragment = new NewsFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("my", "123");
        youFragment.setArguments(bundle1);
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()          // получаем экземпляр FragmentTransaction
                .replace(R.id.news_list, youFragment)
                .addToBackStack("myStack")
                .commit();
    }


}
