package model.services.json;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import model.Quote;
import model.services.IQuotesDataLoader;
import model.services.json.provider.IJsonProvider;

/**
 * Created by codebased on 13/07/16.
 */
public class JsonQuotesDataLoader implements IQuotesDataLoader {


    private final IJsonProvider jsonProvider;
    private final Context context;

    public JsonQuotesDataLoader(Context context, IJsonProvider jsonProvider) {
        this.jsonProvider = jsonProvider;
        this.context = context;
    }

    @Override
    public ArrayList<Quote> getAll() {
        return processJson(this.jsonProvider.getJson(context));
    }

    private ArrayList<Quote> processJson(String result) {
        JSONArray jArray = null;

        long currentTime = System.currentTimeMillis();

        try {
            jArray = new JSONArray(result);


            ArrayList<Quote> data = new ArrayList<>();

            for (int i = 0; i < jArray.length(); i++) {
                data.add(new Quote(jArray.getJSONObject(i).getInt("id"),
                        jArray.getJSONObject(i).getString("quote"),
                        jArray.getJSONObject(i).getString("author"),
                        jArray.getJSONObject(i).getString("genre")));

            }

            Log.d("MYTAG", String.valueOf(System.currentTimeMillis() - currentTime));

            return data;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public void getAllAsync(final DataCallback<ArrayList<Quote>> callback) {
        this.jsonProvider.getJsonAsync(context, new DataCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(processJson(result));

            }

            @Override
            public void onFailure(String reason) {

            }
        });
    }

    private ArrayList<Quote> processJsonThroughGSON(String json) {

        long currentTime = System.currentTimeMillis();
        //Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.PRIVATE).create();
        Gson gson = new Gson();
        Type quotesType = new TypeToken<ArrayList<Quote>>() {
        }.getType();

        ArrayList<Quote> quotes = gson.fromJson(json, quotesType);

        Log.d("MYTAG", String.valueOf(System.currentTimeMillis() - currentTime));


        return quotes;
    }
}