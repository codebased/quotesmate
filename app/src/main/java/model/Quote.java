package model;

import android.database.Cursor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import storage.tables.FavouriteQuoteContract;

/**
 * Created by codebased on 13/07/16.
 */
public class Quote {

    //    @SerializedName("IDD")
    protected int id;
    @Expose(serialize = false, deserialize = true)
    protected String quote;
    @Expose(serialize = false, deserialize = true)
    protected String author;
    protected String genre;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s by %s on %s", quote, author, genre);
    }

    public Quote() {
    }

    public Quote(int id, String quote, String author, String genre) {
        this.id = id;
        this.quote = quote;
        this.author = author;
        this.genre = genre;
    }

    public static Quote fromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(FavouriteQuoteContract.FavouriteQuoteEntry._ID));
        String quote = cursor.getString(cursor.getColumnIndex(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_QUOTE));
        String author = cursor.getString(cursor.getColumnIndex(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_AUTHOR));
        String genre = cursor.getString(cursor.getColumnIndex(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_GENRE));
        return new Quote(id, quote, author, genre);
    }
}
