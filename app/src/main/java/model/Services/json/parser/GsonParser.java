package model.services.json.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GSONParser implements IParser {
    @Override
    public <T> List<T> deserializeList(String json) {
        //Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.PRIVATE).create();
        Gson gson = new Gson();
        Type type = (new TypeToken<List<T>>() {}).getType();
        return  gson.fromJson(json, type);
    }
}