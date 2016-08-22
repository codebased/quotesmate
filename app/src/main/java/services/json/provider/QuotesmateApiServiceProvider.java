package services.json.provider;

import android.content.Context;

import java.util.List;

import model.Author;
import model.Authors;
import model.Genre;
import model.Genres;
import model.Quote;
import model.Quotes;
import services.json.DataCallback;
import services.json.provider.http.QuoteHttpService;
import services.json.provider.http.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.IStore;
import services.SharedPreferenceStore;


public class QuotesmateApiServiceProvider implements IJsonProvider {

    QuoteHttpService quoteHttpService = ServiceGenerator.createService(QuoteHttpService.class);

    @Override
    public void getQuotesJsonAsync(Context context, final DataCallback<List<Quote>> callback) {

        Call<Quotes> randomQuoteCall = quoteHttpService.randomize(10);
        randomQuoteCall.enqueue(new Callback<Quotes>() {
            @Override
            public void onResponse(Call<Quotes> call, Response<Quotes> response) {
                callback.onSuccess(response.body().getQuotes());
            }

            @Override
            public void onFailure(Call<Quotes> call, Throwable t) {
            }
        });
    }

    @Override
    public void getFavouriteQuotesJsonAsync(Context context, DataCallback<List<Quote>> callback) {
        IStore store = new SharedPreferenceStore(context);
        callback.onSuccess(store.getFavouriteQuotes());
    }

    @Override
    public void getAuthorsJsonAsync(Context context, final DataCallback<List<Author>> callback) {

        Call<Authors> randomQuoteCall = quoteHttpService.authors("");
        randomQuoteCall.enqueue(new Callback<Authors>() {
            @Override
            public void onResponse(Call<Authors> call, Response<Authors> response) {
                callback.onSuccess(response.body().getAuthors());
            }

            @Override
            public void onFailure(Call<Authors> call, Throwable t) {
            }
        });
    }

    @Override
    public void getGenresJsonAsync(Context context, final DataCallback<List<Genre>> callback) {

        Call<Genres> randomQuoteCall = quoteHttpService.genres("");
        randomQuoteCall.enqueue(new Callback<Genres>() {
            @Override
            public void onResponse(Call<Genres> call, Response<Genres> response) {
                callback.onSuccess(response.body().getGenres());
            }

            @Override
            public void onFailure(Call<Genres> call, Throwable t) {
            }
        });
    }

    @Override
    public void getAuthorQuotesJsonAsync(Context context, String author, final DataCallback<List<Quote>> callback) {
        Call<Quotes> randomQuoteCall = quoteHttpService.authorQuotes(author);
        randomQuoteCall.enqueue(new Callback<Quotes>() {
            @Override
            public void onResponse(Call<Quotes> call, Response<Quotes> response) {
                callback.onSuccess(response.body().getQuotes());
            }

            @Override
            public void onFailure(Call<Quotes> call, Throwable t) {
            }
        });
    }

    @Override
    public void getGenreQuotesJsonAsync(Context context, String genre, final DataCallback<List<Quote>> callback) {
        Call<Quotes> randomQuoteCall = quoteHttpService.genreQuotes(genre);
        randomQuoteCall.enqueue(new Callback<Quotes>() {
            @Override
            public void onResponse(Call<Quotes> call, Response<Quotes> response) {
                callback.onSuccess(response.body().getQuotes());
            }

            @Override
            public void onFailure(Call<Quotes> call, Throwable t) {
            }
        });
    }
}
