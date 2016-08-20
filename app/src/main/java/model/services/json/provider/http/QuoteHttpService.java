package model.services.json.provider.http;


import model.Authors;
import model.Genres;
import model.Quotes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

// retrofit2 uses  OkHttp as the networking layer and is built on top of it.
public interface QuoteHttpService {

    @GET("/quotes/randomize")
    Call<Quotes> randomize(@Query("count") int count);

    @GET("/quotes/genres/{genre}")
    Call<Quotes> genreQuotes(@Path("genre") String genre);

    @GET("/quotes/author/{author}")
    Call<Quotes> authorQuotes(@Path("author") String author);


    @GET("/quotes/today")
    Call<Quotes> today(@Header("x-custom-message") String message);

    @GET("/quotes/genres/{genre}")
    Call<Genres> genres(@Path("genre") String genre);

    @GET("/quotes/authors/{author}")
    Call<Authors> authors(@Path("author") String author);
}
