package services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import helpers.StringUtil;
import model.Quote;
import services.json.parser.GsonParser;

public class SharedPreferenceStore implements IStore {

    private static final String QUOTE_LIST = "QUOTE_LIST";
    private final Context context;

    public SharedPreferenceStore(Context context) {
        this.context = context;
    }

    @Override
    public void saveFavouriteQuote(Quote quote) {
        List<Quote> quotes = new ArrayList<>(1);
        Collections.fill(quotes, quote);

        saveFavouriteQuotes(quotes);
    }

    @Override
    public void saveFavouriteQuotes(List<Quote> newQuotes) {

        if (newQuotes == null) newQuotes = new ArrayList<>();

        List<Quote> quotes = getFavouriteQuotes();
        Collections.copy(quotes, newQuotes);

        GsonParser gsonParser = new GsonParser();
        String quoteString = gsonParser.serializeList(quotes);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(QUOTE_LIST, quoteString).commit();
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

    @Override
    public void deleteQuote(int id) {

        List<Quote> quotes = getFavouriteQuotes();
        for (Quote quote :
                quotes) {
            if (quote.getId() == id) {
                quotes.remove(quote);
            }
        }

        saveFavouriteQuotes(quotes);
    }

    @Override
    public void deleteQuotes(int... ids) {
        for (int id :
                ids) {
            deleteQuote(id);
        }
    }
}
