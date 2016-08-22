package services;

import java.util.List;
import model.Quote;

public interface IStore {
    void saveFavouriteQuote(Quote quotes);
    void saveFavouriteQuotes(List<Quote> quotes);
    List<Quote> getFavouriteQuotes();
    void deleteQuote(int id);
    void deleteQuotes(int... ids);
}
