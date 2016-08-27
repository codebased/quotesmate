package storage;

import android.content.Context;

import java.util.List;

import model.Quote;
import storage.tables.FavouriteQuoteContract;

public class SqliteStore implements IStore {

    private final FavouriteQuoteContract mf;

    public SqliteStore(Context context) {
        // TODO: 26/08/16  use dagger instead of new.
        mf = new FavouriteQuoteContract(new QuoteReaderDbHelper(context));
    }

    @Override
    public void saveFavouriteQuote(Quote quote) {
        mf.insert(quote);
    }

    @Override
    public void saveFavouriteQuotes(List<Quote> quotes) {

        for (Quote quote :
                quotes) {
            mf.insert(quote);
        }
    }

    @Override
    public List<Quote> getFavouriteQuotes() {
        return mf.getQuotes();
    }

    @Override
    public boolean hasQuote(int id) {
        return mf.find(id) != null;
    }

    @Override
    public void deleteQuote(int id) {
        mf.delete(id);
    }

    @Override
    public void deleteQuotes(int... ids) {

        for (int id :
                ids) {
            mf.delete(id);
        }
    }
}