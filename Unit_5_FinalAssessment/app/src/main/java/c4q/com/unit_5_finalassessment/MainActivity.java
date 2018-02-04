package c4q.com.unit_5_finalassessment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import c4q.com.unit_5_finalassessment.API.NewsDBService;
import c4q.com.unit_5_finalassessment.Model.Articles;
import c4q.com.unit_5_finalassessment.Model.NewsDataWrapper;
import c4q.com.unit_5_finalassessment.Service.NewsDatabaseServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final NewsDBService sportsNewsCallback = NewsDatabaseServiceGenerator
            .createService();
    private List<Articles> articlesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSportsNewsData();
    }

    public void getSportsNewsData() {
        Call<NewsDataWrapper> call = sportsNewsCallback
                .getNewsDiscover(PrivateAPI.getNewsApiKey());
        call.enqueue(new Callback<NewsDataWrapper>() {
            @Override
            public void onResponse(Call<NewsDataWrapper> call, Response<NewsDataWrapper> response) {
                List<Articles> responseList = response.body().getArticles();
                Log.d("News Callback", "onSuccess: " + response.isSuccessful());
            }

            @Override
            public void onFailure(Call<NewsDataWrapper> call, Throwable t) {
                Log.d("News Callback", "onFailure: ", t.fillInStackTrace());
            }
        });
    }
}