package model.services.json.provider;

import android.content.Context;

import model.services.json.DataCallback;
import model.services.json.provider.http.QuotesAsyncTask;

/**
 * Created by codebased on 26/07/16.
 */
public class HttpJsonProvider implements IJsonProvider {

    @Override
    public void getJsonAsync(Context context, DataCallback<String> callback) {

        QuotesAsyncTask quotesAsyncTask = new QuotesAsyncTask(callback);
        quotesAsyncTask.execute();
    }

    @Override
    public String getJson(Context context) {
        return null;
    }
}
