package c4q.com.unit_5_finalassessment.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by c4q on 2/6/18.
 */

public class NewsRefreshIntentService extends IntentService {

  /**
   * Creates an IntentService.  Invoked by your subclass's constructor.
   *
   * @param-name Used to name the worker thread, important only for debugging.
   */
  public NewsRefreshIntentService() {
    super("FeedRefreshIntentService");
  }

  @Override
  protected void onHandleIntent(@Nullable Intent intent) {
    String action = intent.getAction();
    String url = intent.getStringExtra("url");
    NewsRefreshTask.executeNotification(this, action, url);
  }
}