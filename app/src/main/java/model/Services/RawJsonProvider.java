package model.Services;

import android.content.Context;

import com.imcodebased.quotesmate.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RawJsonProvider implements IJsonProvider {

    @Override
    public String getJson(Context context) {

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
