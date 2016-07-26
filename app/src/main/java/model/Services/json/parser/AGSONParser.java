package model.services.json.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class AGSONParser  {
    public <T> List<T> deserializeList(String json, Type type) {
        //Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.PRIVATE).create();
        Gson gson = new Gson();
        return  gson.fromJson(json, type);
    }
}