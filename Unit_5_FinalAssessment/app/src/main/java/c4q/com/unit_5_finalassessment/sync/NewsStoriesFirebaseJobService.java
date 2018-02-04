package c4q.com.unit_5_finalassessment.sync;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by c4q on 2/4/18.
 */

public class NewsStoriesFirebaseJobService extends JobService {

  private static String TAG = NewsStoriesFirebaseJobService.class.getSimpleName();
  JobParameters jobParams;
  NewsRefreshTask newsRefreshTask;

  @Override
  public boolean onStartJob(JobParameters job) {
    Log.d(TAG, "start: on start called");
    jobParams = job;
    newsRefreshTask = new NewsRefreshTask();
    //newsRefreshTask.getMoviesData(job.getTag(), this);
    return true;
  }

  @Override
  public boolean onStopJob(JobParameters job) {
    if (newsRefreshTask != null) {
      // newsRefreshTask.onserviceCancelled();
    }
    return true;
  }

  public void jobCompleted() {
    this.jobFinished(jobParams, false);
  }
}
