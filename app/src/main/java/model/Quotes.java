package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quotes {

    @SerializedName("quotes")
    private List<Quote> quotes;

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}
