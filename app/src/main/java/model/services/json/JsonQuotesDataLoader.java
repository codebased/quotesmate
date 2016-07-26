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
    public List<Quote> getAll() {
        return parser.deserializeList(this.jsonProvider.getJson(context));
    }

    @Override
    public void getAllAsync(final DataCallback<List<Quote>> callback) {
        this.jsonProvider.getJsonAsync(context, new DataCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(parser.<Quote>deserializeList(result));
            }

            @Override
            public void onFailure(String reason) {
            }
        });
    }

    @Override
    public void getAllGenreAsync(DataCallback<List<Genre>> callback) {


    }

    @Override
    public void getAllAuthorAsync(DataCallback<List<Author>> callback) {

    }
}