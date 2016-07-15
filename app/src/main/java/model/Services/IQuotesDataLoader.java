package model.services;

import java.util.ArrayList;

import model.Quote;
import model.services.json.DataCallback;

public interface IQuotesDataLoader {

    ArrayList<Quote> getAll();

    void getAllAsync(DataCallback<ArrayList<Quote>> callback);
}


