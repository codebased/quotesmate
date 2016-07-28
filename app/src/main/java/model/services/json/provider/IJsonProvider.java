package model.services.json.provider;

import android.content.Context;

import model.services.json.DataCallback;

public interface IJsonProvider<T> {
    void getJsonAsync(Context context, DataCallback<T> callback);
    String getJson(Context context);
}

