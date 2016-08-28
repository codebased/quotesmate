package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by codebased on 13/07/16.
 */
public class Genre {

    @SerializedName("genre")
    private String genre;

    @SerializedName("quotes")
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
