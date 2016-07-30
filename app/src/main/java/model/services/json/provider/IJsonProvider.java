package model.services.json.provider;

import android.content.Context;

import java.util.List;

import model.Author;
import model.Genre;
import model.Quote;
import model.services.json.DataCallback;

public interface IJsonProvider {
    void getQuotesJsonAsync(Context context, final DataCallback<List<Quote>> callback);

    void getAuthorsJsonAsync(Context context, final DataCallback<List<Author>> callback);

    void getGenresJsonAsync(Context context, final DataCallback<List<Genre>> callback);
}

