package services;

import java.util.List;
import model.Quote;

public interface IStore {
    void saveFavouriteQuotes(List<Quote> quotes);
    List<Quote> getFavouriteQuotes();
}
