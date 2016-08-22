package services.json;

import android.content.Context;

import java.util.List;

import model.Author;
import model.Genre;
import model.Quote;
import services.IDataLoader;
import services.json.parser.GsonParser;
import services.json.parser.IParser;
import services.json.provider.IJsonProvider;

public class JsonDataLoader implements IDataLoader {

    private final IJsonProvider jsonProvider;
    private final Context context;
    private final IParser parser;

    public JsonDataLoader(Context context, IJsonProvider jsonProvider) {
        this.jsonProvider = jsonProvider;
        this.context = context;
        parser = new GsonParser();
    }

    @Override
    public void getAllRandomQuoteAsync(final DataCallback<List<Quote>> callback) {
        this.jsonProvider.getQuotesJsonAsync(context, new DataCallback<List<Quote>>() {
            @Override
            public void onSuccess(List<Quote> result) {
//                callback.onSuccess(parser.<Quote>deserializeList(result));
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(String reason) {
                callback.onFailure(reason);
            }
        });
    }

    @Override
    public void getAllFavouriteQuoteAsync(final DataCallback<List<Quote>> callback) {
        this.jsonProvider.getFavouriteQuotesJsonAsync(context, new DataCallback<List<Quote>>() {
            @Override
            public void onSuccess(List<Quote> result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(String reason) {
                callback.onFailure(reason);
            }
        });
    }

    @Override
    public void getAllGenreAsync(final DataCallback<List<Genre>> callback) {

        this.jsonProvider.getGenresJsonAsync(context, new DataCallback<List<Genre>>() {
            @Override
            public void onSuccess(List<Genre> result) {
//                callback.onSuccess(parser.<Quote>deserializeList(result));
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(String reason) {
                callback.onFailure(reason);
            }
        });

    }

    @Override
    public void getAllAuthorAsync(final DataCallback<List<Author>> callback) {

        this.jsonProvider.getAuthorsJsonAsync(context, new DataCallback<List<Author>>() {
            @Override
            public void onSuccess(List<Author> result) {
//                callback.onSuccess(parser.<Quote>deserializeList(result));
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(String reason) {
                callback.onFailure(reason);
            }
        });
    }

    @Override
    public void getAllQuoteByGenreAsync(String genre, final DataCallback<List<Quote>> callback) {
        this.jsonProvider.getGenreQuotesJsonAsync(context, genre, new DataCallback<List<Quote>>() {
            @Override
            public void onSuccess(List<Quote> result) {
//                callback.onSuccess(parser.<Quote>deserializeList(result));
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(String reason) {
                callback.onFailure(reason);
            }
        });
    }

    @Override
    public void getAllQuoteByAuthorAsync(String author, final DataCallback<List<Quote>> callback) {
        this.jsonProvider.getAuthorQuotesJsonAsync(context, author, new DataCallback<List<Quote>>() {
            @Override
            public void onSuccess(List<Quote> result) {
//                callback.onSuccess(parser.<Quote>deserializeList(result));
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(String reason) {
                callback.onFailure(reason);
            }
        });
    }
}