package model;

import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("quotes")
    private String quotes;

    @SerializedName("author")
    private String author;

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
