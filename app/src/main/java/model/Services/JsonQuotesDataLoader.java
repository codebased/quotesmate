package model.Services;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Quote;

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
    public List<Quote> getAll() {
        String rawJson = this.jsonProvider.getJson(context);

        JSONObject jObject = null;
        try {
            jObject = new JSONObject(rawJson);

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
}