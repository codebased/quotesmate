package model.services.json.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import helpers.StringUtil;
import model.Author;
import model.Authors;
import model.Genre;
import model.Genres;
import model.Quote;
import model.Quotes;
import model.services.json.DataCallback;
import model.services.json.parser.GsonParser;
import model.services.json.provider.http.QuoteHttpService;
import model.services.json.provider.http.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetroServiceJsonProvider implements IJsonProvider {

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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String quoteString = sharedPreferences.getString("", null);
        List<Quote> quotes;

        if (StringUtil.isNullOrWhiteSpace(quoteString)) {
            quotes = new ArrayList<>();
        } else {
            GsonParser gsonParser = new GsonParser();
            quotes = gsonParser.deserializeList(quoteString);
        }

        callback.onSuccess(quotes);
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
