package c4q.com.unit_5_finalassessment.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import c4q.com.unit_5_finalassessment.R;
import c4q.com.unit_5_finalassessment.api.NewsDBService;
import c4q.com.unit_5_finalassessment.model.Article;
import c4q.com.unit_5_finalassessment.model.NewsDataWrapper;
import c4q.com.unit_5_finalassessment.service.NewsDatabaseServiceGenerator;
import c4q.com.unit_5_finalassessment.adapter.SportsAdapter;
import c4q.com.unit_5_finalassessment.sync.NewsRefreshTask;
import c4q.com.unit_5_finalassessment.utils.RefreshStoriesUtilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final NewsDBService sportsNewsCallback = NewsDatabaseServiceGenerator
            .createService();
    private static final String API_KEY = "aabd804e78a548cfaaa7ef737708b084";
    private List<Article> articleList = new ArrayList<>();

    private RecyclerView articlesRV;
    private SportsAdapter sportsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articlesRV = findViewById(R.id.articles_rv);
        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        articlesRV.setLayoutManager(layout);

        if (RefreshStoriesUtilities.isNetworkAvailable(getApplicationContext())) {
            getSportsNewsData();
        } else {
            getSportsNewsDataFromDatabase();
        }
    }


    public void getSportsNewsData() {
        Call<NewsDataWrapper> call = sportsNewsCallback
                .getNewsDiscover(API_KEY);
        call.enqueue(new Callback<NewsDataWrapper>() {
            @Override
            public void onResponse(Call<NewsDataWrapper> call, Response<NewsDataWrapper> response) {
                List<Article> responseList = response.body().getArticles();
                articleList.addAll(responseList);
                sportsAdapter = new SportsAdapter(articleList);
                articlesRV.setAdapter(sportsAdapter);

                for (Article a : responseList) {
                    Log.d("onSuccess", a.getTitle() + " " + a.getAuthor());
                }
            }

            @Override
            public void onFailure(Call<NewsDataWrapper> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });
    }

    public void getSportsNewsDataFromDatabase() {
        NewsRefreshTask newsRefreshTask = new NewsRefreshTask();
        newsRefreshTask.getArticlesData(getApplicationContext());
    }
}

