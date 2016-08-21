package services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import helpers.StringUtil;
import model.Quote;
import model.services.json.parser.GsonParser;

public class SharedPreferenceStore implements IStore {

    private static final String QUOTE_LIST = "QUOTE_LIST";
    private final Context context;

    public SharedPreferenceStore(Context context) {
        this.context = context;
    }

    @Override
    public void saveFavouriteQuotes(List<Quote> quotes) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        if (quotes == null) quotes = new ArrayList<>();

        GsonParser gsonParser = new GsonParser();
        sharedPreferences.edit().putString(QUOTE_LIST, gsonParser.serializeList(quotes)).commit();
    }

    @Override
    public List<Quote> getFavouriteQuotes() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String quoteString = sharedPreferences.getString(QUOTE_LIST, null);
        List<Quote> quotes;

        if (StringUtil.isNullOrWhiteSpace(quoteString)) {
            quotes = new ArrayList<>();
        } else {
            GsonParser gsonParser = new GsonParser();
            quotes = gsonParser.deserializeList(quoteString);
        }

        return quotes;
    }
}
