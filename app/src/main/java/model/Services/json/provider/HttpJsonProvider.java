package model.services.json.provider;

import android.content.Context;

import java.util.List;

import model.Author;
import model.Genre;
import model.Quote;
import model.services.json.DataCallback;
import model.services.json.provider.http.QuotesAsyncTask;

/**
 * Created by codebased on 26/07/16.
 */
public class HttpJsonProvider implements IJsonProvider {

    @Override
    public void getQuotesJsonAsync(Context context, DataCallback<List<Quote>> callback) {

    }

    @Override
    public void getAuthorsJsonAsync(Context context, DataCallback<List<Author>> callback) {

    }

    @Override
    public void getGenresJsonAsync(Context context, DataCallback<List<Genre>> callback) {

    }
}
