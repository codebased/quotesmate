package model.services.json.provider;

import android.content.Context;

import com.imcodebased.quotesmate.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import model.services.json.DataCallback;


public class RawJsonProvider implements IJsonProvider<String> {

    @Override
    public void getJsonAsync(Context context, DataCallback<String> callback) {

        if (callback != null) {
            callback.onSuccess(readJson(context));
        }
    }

    @Override
    public String getJson(Context context) {
        return readJson(context);
    }

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
}
