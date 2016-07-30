package model;

import com.google.gson.annotations.Expose;

/**
 * Created by codebased on 13/07/16.
 */
public class Genre {

    private String genre;
    private String quotes;


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }
}
