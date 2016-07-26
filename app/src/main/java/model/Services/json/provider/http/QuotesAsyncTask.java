package model.services.json.provider.http;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import model.Quote;
import model.services.json.DataCallback;
import model.services.json.parser.QuotesManualParser;


/**
 * Created by codebased on 26/07/16.
 */
public class QuotesAsyncTask extends AsyncTask<Void, Void, String> {


    private final DataCallback<String> callback;

    public QuotesAsyncTask(DataCallback<String> callback) {
        this.callback = callback;

    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            BufferedReader reader = null;
            URL url = new URL("http://quotesmateapi.imcodebased.com/quotes/randomize");

            final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            return buffer.toString();
        } catch (Exception ex) {
            Log.e("QuotesAsyncTask", ex.toString());
        }

        return null;
    }

    @Override
    protected void onPostExecute(String quotes) {

        this.callback.onSuccess(quotes);
    }
}
