package c4q.com.unit_5_finalassessment.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.ContextCompat;
import c4q.com.unit_5_finalassessment.MainActivity;
import c4q.com.unit_5_finalassessment.R;
import c4q.com.unit_5_finalassessment.model.Articles;
import java.io.IOException;
import java.net.URL;

/**
 * Created by c4q on 2/5/18.
 */

public class NotificationUtils {

  private static final int NEWSFEED_REFRESH_NOTIFCATION_ID = 9931;

  private static final int NEWSFEED_REFRESH_PENDING_INTENT_ID = 3319;

  private static final String NEWSFEED_REFRESH_NOTIFCATION_CHANNEL_ID = "refresh_notifcation_channel";

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
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setLargeIcon(Bitmap.createBitmap(getBitmap(article.getUrlToImage())))
        .setContentTitle("Breaking News")
        .setContentText(article.getTitle())
        .setStyle(new BigTextStyle().bigText(article.getTitle()))
        .setDefaults(Notification.DEFAULT_VIBRATE)
        .setContentIntent(contentIntent(context))
        .addAction(dismissNotificationAction(context))
        .setAutoCancel(true);

    notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
    notificationManager.notify(NEWSFEED_REFRESH_NOTIFCATION_ID, notificationBuilder.build());

  }

  private static PendingIntent contentIntent(Context context) {
    Intent startActivityIntent = new Intent(context, MainActivity.class);
    return PendingIntent
        .getActivity(context, NEWSFEED_REFRESH_PENDING_INTENT_ID, startActivityIntent,
            PendingIntent.FLAG_UPDATE_CURRENT);
  }

  private static Action dismissNotificationAction(Context context) {
    return null;
  }

  private static Bitmap getBitmap(String imageUrl) {
    try {
      URL url = new URL(imageUrl);
      Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
      return image;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
