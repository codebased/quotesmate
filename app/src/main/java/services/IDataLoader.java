package services;

import java.util.List;

import model.Author;
import model.Genre;
import model.Quote;
import services.json.DataCallback;

public interface IDataLoader {

    void getAllRandomQuoteAsync(DataCallback<List<Quote>> callback);
    void getAllFavouriteQuoteAsync(DataCallback<List<Quote>> callback);
    void getAllGenreAsync(DataCallback<List<Genre>> callback);
    void getAllAuthorAsync(DataCallback<List<Author>> callback);

    void getAllQuoteByGenreAsync(String genre, DataCallback<List<Quote>> callback);
    void getAllQuoteByAuthorAsync(String author, DataCallback<List<Quote>> callback);

}



