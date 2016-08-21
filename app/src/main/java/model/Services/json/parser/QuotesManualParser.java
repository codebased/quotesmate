package model.services.json.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Quote;


public class QuotesManualParser implements IParser {

    @Override
    public List<Quote> deserializeList(String json) {
        JSONObject jObject;

        try {
            jObject = new JSONObject(json);

            JSONArray jArray = jObject.getJSONArray("quotes");

            List<Quote> data = new ArrayList<>();

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
    public <T> String serializeList(List<T> items) {
        return null;
    }
}
