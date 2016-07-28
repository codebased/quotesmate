package model.services.json.provider.http;


import model.Quotes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

// retrofit2 uses  OkHttp as the networking layer and is built on top of it.
public interface QuoteHttpService {


    @GET("/quotes/randomize")
    Call<Quotes> randomize(@Query("count") int count);

    @GET("/quotes/today")
    Call<Quotes> today();

    @GET("/quotes/genres/{genre}")
    Call<Quotes> genres(@Path("genre") String genre);

    @GET("/quotes/author/{author}")
    Call<Quotes> authors(@Path("author") String author);
}
