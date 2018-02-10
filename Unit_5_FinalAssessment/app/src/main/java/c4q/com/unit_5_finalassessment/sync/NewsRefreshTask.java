package c4q.com.unit_5_finalassessment.sync;

import static java.nio.channels.AsynchronousServerSocketChannel.open;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import c4q.com.unit_5_finalassessment.api.NewsDBService;
import c4q.com.unit_5_finalassessment.databases.SQLDatabase;
import c4q.com.unit_5_finalassessment.model.Article;
import c4q.com.unit_5_finalassessment.model.NewsDataWrapper;
import c4q.com.unit_5_finalassessment.service.NewsDatabaseServiceGenerator;
import c4q.com.unit_5_finalassessment.utils.NotificationUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by c4q on 2/4/18.
 */

public class NewsRefreshTask {

  private static final String API_KEY = "aabd804e78a548cfaaa7ef737708b084";
  private static final NewsDBService refreshStoriesCallback = NewsDatabaseServiceGenerator
      .createService();

  private static final String ACTION_GOTO_ARTICLE_NOTIFICATION = "goto-article";
  private static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
  private static final String TAG = NewsRefreshTask.class.getSimpleName();
  private Call<NewsDataWrapper> newsCall;

  public void executeAction(Context context, String string) {

  }


  public void getArticlesData(final Context context) {
    Call<NewsDataWrapper> refreshCall = refreshStoriesCallback
        .getNewsDiscover(API_KEY);
    refreshCall.enqueue(new Callback<NewsDataWrapper>() {
      @Override
      public void onResponse(Call<NewsDataWrapper> call, Response<NewsDataWrapper> response) {
        List<Article> responseList = response.body().getArticles();
        addMovieUsingContentValues(context, responseList);
        ((NewsStoriesFirebaseJobService) context).jobCompleted();
      }

      @Override
      public void onFailure(Call<NewsDataWrapper> call, Throwable t) {

      }
    });

  }


  public static void executeNotification(Context context, String action, String url) {
    if (ACTION_GOTO_ARTICLE_NOTIFICATION.equals(action)) {
      if (url != null || url.equals("")) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
      }
    } else if (ACTION_DISMISS_NOTIFICATION.equals(action)) {
      NotificationUtils.clearAllNotifications(context);
    }
  }

  public void addMovieUsingContentValues(Context context, List<Article> articles) {
    SQLDatabase sqlDatabase = SQLDatabase.getInstance(context);
    ContentValues contentValues = new ContentValues();
    for (Article article : articles) {
      contentValues.put("author", article.getAuthor());
      contentValues.put("title", article.getTitle());
      contentValues.put("description", article.getAuthor());
      contentValues.put("url", article.getUrl());
      contentValues.put("urlToImage", article.getUrlToImage());
      contentValues.put("published", article.getPublishedAt());
    }
    sqlDatabase.getWritableDatabase().insert("sports", null, contentValues);

  }

  public void onServiceCancelled() {
    newsCall.cancel();
  }
}
