package model.services.json;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(result);

            JSONArray jArray = jObject.getJSONArray("quotes");

            ArrayList<Quote> data = new ArrayList<>();

            for (int i = 0; i < jArray.length(); i++) {
                data.add(new Quote(jArray.getJSONObject(i).getInt("id"),
                        jArray.getJSONObject(i).getString("quote"),
                        jArray.getJSONObject(i).getString("author"),
                        jArray.getJSONObject(i).getString("genre")));

            }
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
}