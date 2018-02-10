package c4q.com.unit_5_finalassessment.sync;

import android.util.Log;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by c4q on 2/4/18.
 */

public class NewsStoriesFirebaseJobService extends JobService {

  private static String TAG = NewsStoriesFirebaseJobService.class.getSimpleName();
  private JobParameters jobParams;
  private NewsRefreshTask newsRefreshTask;

  @Override
  public boolean onStartJob(JobParameters job) {
    Log.d(TAG, "start: on startJob called");
    jobParams = job;
    newsRefreshTask = new NewsRefreshTask();
    newsRefreshTask.getArticlesData(this);
    return true;
  }

  @Override
  public boolean onStopJob(JobParameters job) {
    Log.d(TAG,"stop: service cancel called");
    if (newsRefreshTask != null) {
      newsRefreshTask.onServiceCancelled();
    }
    return true;
  }

  public void jobCompleted() {
    Log.d(TAG,"job completed");
    this.jobFinished(jobParams, false);
  }
}
