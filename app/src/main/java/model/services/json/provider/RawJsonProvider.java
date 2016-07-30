package model.services.json.provider;

import android.content.Context;

import com.imcodebased.quotesmate.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import model.Author;
import model.Genre;
import model.Quote;
import model.services.json.DataCallback;


public class RawJsonProvider implements IJsonProvider {

    private String readJson(Context context) {
        InputStreamReader inputStream = new InputStreamReader(context.getResources().openRawResource(R.raw.quotes));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }

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
