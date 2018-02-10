package c4q.com.unit_5_finalassessment.sync;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import c4q.com.unit_5_finalassessment.api.NewsDBService;
import c4q.com.unit_5_finalassessment.databases.SQL_Database;
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


    public void executeAction(Context context, String string) {

    }


    public void getArticlesData(final Context context) {
        Call<NewsDataWrapper> refreshCall = refreshStoriesCallback
                .getNewsDiscover(API_KEY);
        refreshCall.cancel();
        refreshCall.enqueue(new Callback<NewsDataWrapper>() {
            @Override
            public void onResponse(Call<NewsDataWrapper> call, Response<NewsDataWrapper> response) {
                List<Article> responseList = response.body().getArticles();
                saveData(responseList, context);
                ((NewsStoriesFirebaseJobService) context).jobCompleted();
            }

            @Override
            public void onFailure(Call<NewsDataWrapper> call, Throwable t) {
                SQL_Database sqlDatabase = SQL_Database.getInstance(context);
                sqlDatabase.getArticlesList();
            }
        });

    }

    private void saveData(List<Article> responseList, Context context) {
        SQL_Database sqlDatabase = SQL_Database.getInstance(context);
        for (Article article : responseList) {
            sqlDatabase.addArticle(article);
        }
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
}
