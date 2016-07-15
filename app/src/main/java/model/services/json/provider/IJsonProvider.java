package model.services.json.provider;

import android.content.Context;

import model.services.json.DataCallback;

public interface IJsonProvider {
    void getJsonAsync(Context context, DataCallback<String> callback);
    String getJson(Context context);
}

