package c4q.com.unit_5_finalassessment.sync;

import android.content.Context;
import c4q.com.unit_5_finalassessment.api.NewsDBService;
import c4q.com.unit_5_finalassessment.model.Articles;
import c4q.com.unit_5_finalassessment.model.NewsDataWrapper;
import c4q.com.unit_5_finalassessment.service.NewsDatabaseServiceGenerator;
import java.security.PrivateKey;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by c4q on 2/4/18.
 */

public class NewsRefreshTask {

  private static final NewsDBService refreshStoriesCallback = NewsDatabaseServiceGenerator
      .createService();

  public void getMoviesData(final Context context) {
    Call<NewsDataWrapper> refreshCall = refreshStoriesCallback
        .getNewsDiscover(PrivateAPI.getNewsApiKey());
    refreshCall.cancel();
    refreshCall.enqueue(new Callback<NewsDataWrapper>() {
      @Override
      public void onResponse(Call<NewsDataWrapper> call, Response<NewsDataWrapper> response) {
        List<Articles> responseList = response.body().getArticles();
        saveData(responseList);
        ((NewsStoriesFirebaseJobService) context).jobCompleted();
      }

      @Override
      public void onFailure(Call<NewsDataWrapper> call, Throwable t) {

      }
    });

  }

  private void saveData(List<Articles> responseList) {

  }
}
