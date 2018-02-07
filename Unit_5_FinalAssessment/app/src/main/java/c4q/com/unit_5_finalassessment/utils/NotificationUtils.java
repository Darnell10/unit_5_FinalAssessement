package c4q.com.unit_5_finalassessment.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.ContextCompat;
import c4q.com.unit_5_finalassessment.MainActivity;
import c4q.com.unit_5_finalassessment.R;
import c4q.com.unit_5_finalassessment.model.Articles;
import c4q.com.unit_5_finalassessment.sync.NewsRefreshIntentService;
import java.io.IOException;
import java.net.URL;

/**
 * Created by c4q on 2/5/18.
 */

public class NotificationUtils {

  private static final int NEWSFEED_REFRESH_NOTIFCATION_ID = 9931;

  private static final int NEWSFEED_REFRESH_PENDING_INTENT_ID = 3319;

  private static final String NEWSFEED_REFRESH_NOTIFCATION_CHANNEL_ID = "refresh_notifcation_channel";

  private static final int ACTION_DISMISS_PENDING_INTENT_ID = 21;
  private static final int ACTION_GO_TO_SOURCE_PENDING_INTENT_ID = 13;

  public static void clearAllNotifications(Context context) {
    NotificationManager notificationManager = (NotificationManager) context
        .getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.cancelAll();
  }

  public static void breakingNews(Context context, Articles article) {
    NotificationManager notificationManager = (NotificationManager) context
        .getSystemService(Context.NOTIFICATION_SERVICE);
    NotificationCompat.Builder notificationBuilder = new Builder(context,
        NEWSFEED_REFRESH_NOTIFCATION_CHANNEL_ID)
        .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
        .setSmallIcon(R.drawable.newspaper)
        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.newspaper))
        .setContentTitle("Breaking News")
        .setContentText(article.getTitle())
        .setStyle(new BigTextStyle().bigText(article.getTitle()))
        .setDefaults(Notification.DEFAULT_VIBRATE)
        .setContentIntent(contentIntent(context))
        .addAction(goToArticleSourceAction(context, article.getUrl()))
        .addAction(dismissNotificationAction(context))
        .setAutoCancel(true);

    notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
    notificationManager.notify(NEWSFEED_REFRESH_NOTIFCATION_ID, notificationBuilder.build());

  }

  private static Action goToArticleSourceAction(Context context, String url) {
    Intent gotToArticleIntent = new Intent(context, NewsRefreshIntentService.class);
    gotToArticleIntent.setAction("goto-article");
    gotToArticleIntent.putExtra("url", url);
    PendingIntent gotoArticlePendingIntent = PendingIntent
        .getService(context, ACTION_GO_TO_SOURCE_PENDING_INTENT_ID, gotToArticleIntent,
            PendingIntent.FLAG_UPDATE_CURRENT);
    return new Action(R.drawable.window_close, "goto-article",
        gotoArticlePendingIntent);
  }

  private static Action dismissNotificationAction(Context context) {
    Intent ignoreNotificationIntent = new Intent(context, NewsRefreshIntentService.class);
    ignoreNotificationIntent.setAction("dismiss-notification");
    PendingIntent ignoreNotificationPendingIntent = PendingIntent
        .getService(context, ACTION_DISMISS_PENDING_INTENT_ID, ignoreNotificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT);
    return new Action(R.drawable.window_close, "dismiss-notification",
        ignoreNotificationPendingIntent);
  }

  private static PendingIntent contentIntent(Context context) {
    Intent startActivityIntent = new Intent(context, MainActivity.class);
    return PendingIntent
        .getActivity(context, NEWSFEED_REFRESH_PENDING_INTENT_ID, startActivityIntent,
            PendingIntent.FLAG_UPDATE_CURRENT);
  }
}
