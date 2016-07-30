package model.services;

import java.util.ArrayList;
import java.util.List;

import model.Author;
import model.Genre;
import model.Quote;
import model.services.json.DataCallback;

public class StaticQuotesDataLoader implements IQuotesDataLoader {

    @Override
    public void getAllGenreAsync(DataCallback<List<Genre>> callback) {

    }

    @Override
    public void getAllAuthorAsync(DataCallback<List<Author>> callback) {

    }

    @Override
    public void getAllRandomQuoteAsync(DataCallback<List<Quote>> callback) {

        ArrayList<Quote> data = getData ();

        callback.onSuccess(data);
    }

    private ArrayList<Quote> getData() {
        ArrayList<Quote> quotes = new ArrayList<>();
        quotes.add(new Quote(1, "My First Quote", "Amit", "Love"));
        quotes.add(new Quote(2, "My Second Quote", "Amit", "Hate"));
        quotes.add(new Quote(3, "My Third Quote", "Amit", "Honest"));
        quotes.add(new Quote(4, "My Forth Quote", "Amit", "Death"));
        quotes.add(new Quote(5, "My Fifth Quote", "Amit", "Birth"));
        quotes.add(new Quote(6, "My Sixth Quote", "Amit", "Teacher"));
        quotes.add(new Quote(7, "My Seventh Quote", "Amit", "CBA"));

        return quotes;
    }
}
