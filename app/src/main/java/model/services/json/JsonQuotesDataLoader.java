package model.services.json;

import android.content.Context;

import java.util.List;

import model.Author;
import model.Genre;
import model.Quote;
import model.services.IQuotesDataLoader;
import model.services.json.parser.IParser;
import model.services.json.parser.QuotesManualParser;
import model.services.json.provider.IJsonProvider;

public class JsonQuotesDataLoader implements IQuotesDataLoader {

    private final IJsonProvider jsonProvider;
    private final Context context;
    private final IParser parser;

    public JsonQuotesDataLoader(Context context, IJsonProvider jsonProvider) {
        this.jsonProvider = jsonProvider;
        this.context = context;
        parser = new QuotesManualParser();
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
            }
        });
    }
}