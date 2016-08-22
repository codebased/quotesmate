package storage;

import java.util.List;
import model.Quote;

public interface IStore {
    void saveFavouriteQuote(Quote quotes);
    void saveFavouriteQuotes(List<Quote> quotes);
    List<Quote> getFavouriteQuotes();
    boolean hasQuote(int id);
    void deleteQuote(int id);
    void deleteQuotes(int... ids);
}
