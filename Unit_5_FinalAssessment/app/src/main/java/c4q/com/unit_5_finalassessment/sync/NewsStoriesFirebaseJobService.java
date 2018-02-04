package c4q.com.unit_5_finalassessment.sync;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by c4q on 2/4/18.
 */

class NewsStoriesFirebaseJobService extends JobService {

  @Override
  public boolean onStartJob(JobParameters job) {
    return false;
  }

  @Override
  public boolean onStopJob(JobParameters job) {
    return false;
  }
}
