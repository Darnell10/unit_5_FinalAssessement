package c4q.com.unit_5_finalassessment.api;


import c4q.com.unit_5_finalassessment.model.NewsDataWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsDBService {
    @GET("v2/top-headlines?language=en&sources=bbc-sport&sortBy=popularity")
    Call<NewsDataWrapper> getNewsDiscover(
            @Query("apiKey") String apiKey
    );

    @GET("v2/top-headlines?language=en&sources=bbc-sport&sortBy=popularity")
    Call<NewsDataWrapper> getNewsDiscoverWithSearch(
            @Query("apiKey") String apiKey,
            @Query("q") String search
    );
}
