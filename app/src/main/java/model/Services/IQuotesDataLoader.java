package model.services;

import java.util.ArrayList;
import java.util.List;

import model.Author;
import model.Genre;
import model.Quote;
import model.services.json.DataCallback;

public interface IQuotesDataLoader {

    List<Quote> getAll();

    void getAllAsync(DataCallback<List<Quote>> callback);
    void getAllGenreAsync(DataCallback<List<Genre>> callback);
    void getAllAuthorAsync(DataCallback<List<Author>> callback);
}


