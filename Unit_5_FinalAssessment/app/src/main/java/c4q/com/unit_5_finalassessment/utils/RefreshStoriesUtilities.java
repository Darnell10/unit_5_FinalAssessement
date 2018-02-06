package c4q.com.unit_5_finalassessment.utils;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import c4q.com.unit_5_finalassessment.sync.NewsStoriesFirebaseJobService;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import java.util.concurrent.TimeUnit;

/**
 * Created by c4q on 2/4/18.
 */

public class RefreshStoriesUtilities {

  private static final int REFRESH_INTERVAL_MINUTES = 15;
  private static final int REFRESH_INTERVAL_SECONDS = (int) (TimeUnit.MINUTES
      .toSeconds(REFRESH_INTERVAL_MINUTES));
  private static final int SYNC_FLEXTIME_SECONDS = REFRESH_INTERVAL_SECONDS;

  private static final String REFRESH_JOB_TAG = "refresh_stories_job_tag";

  private static boolean isInitialized;

  synchronized public static void scheduleStoriesRefresh(@NonNull final Context context) {
    if (isInitialized) {
      return;
    }

    Log.d(REFRESH_JOB_TAG, "start: entered refresh job");
    Driver gdriver = new GooglePlayDriver(context);
    FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(gdriver);

    Job refreshStoriesJob = dispatcher.newJobBuilder()
        .setService(NewsStoriesFirebaseJobService.class)
        .setTag(REFRESH_JOB_TAG).setConstraints(Constraint.ON_ANY_NETWORK)
        .setLifetime(Lifetime.FOREVER).setRecurring(true)
        .setTrigger(Trigger.executionWindow(REFRESH_INTERVAL_SECONDS,
            REFRESH_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
        .setReplaceCurrent(true)
        .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
        .build();

    dispatcher.mustSchedule(refreshStoriesJob);
    isInitialized = true;
  }

}
