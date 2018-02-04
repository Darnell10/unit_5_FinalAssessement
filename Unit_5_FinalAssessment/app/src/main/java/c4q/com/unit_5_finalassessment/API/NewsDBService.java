package c4q.com.unit_5_finalassessment.API;


import c4q.com.unit_5_finalassessment.Model.NewsDataWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsDBService {
    @GET("v2/top-headlines?language=en&sources=fox-sports&sortBy=relevancy")
    Call<NewsDataWrapper> getNewsDiscover(
            @Query("apiKey") String apiKey
    );

    @GET("v2/top-headlines?language=en&sources=fox-sports&sortBy=relevancy")
    Call<NewsDataWrapper> getNewsDiscoverWithSearch(
            @Query("apiKey") String apiKey,
            @Query("q") String search
    );
}
