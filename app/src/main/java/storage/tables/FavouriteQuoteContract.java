package storage.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import model.Quote;

public final class FavouriteQuoteContract {
    public static final String TABLE_NAME = "favouritequotes";
    private final SQLiteOpenHelper mDbHelper;
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INT";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    FavouriteQuoteEntry._ID + " INTEGER PRIMARY KEY," +
                    FavouriteQuoteEntry.COLUMN_NAME_ID + INT_TYPE + COMMA_SEP +
                    FavouriteQuoteEntry.COLUMN_NAME_QUOTE + TEXT_TYPE + COMMA_SEP +
                    FavouriteQuoteEntry.COLUMN_NAME_AUTHOR + TEXT_TYPE + COMMA_SEP +
                    FavouriteQuoteEntry.COLUMN_NAME_GENRE + TEXT_TYPE + " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public FavouriteQuoteContract(SQLiteOpenHelper dbHelper) {
        mDbHelper = dbHelper;
    }

    public abstract class FavouriteQuoteEntry implements BaseColumns {
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_QUOTE = "quote";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_GENRE = "genre";
    }

    public long insert(Quote quote) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FavouriteQuoteEntry.COLUMN_NAME_ID, quote.getId());
        values.put(FavouriteQuoteEntry.COLUMN_NAME_QUOTE, quote.getQuote());
        values.put(FavouriteQuoteEntry.COLUMN_NAME_AUTHOR, quote.getAuthor());
        values.put(FavouriteQuoteEntry.COLUMN_NAME_GENRE, quote.getGenre());

        long newRowId;
        newRowId = db.insert(
                TABLE_NAME,
                null,
                values);

        db.close();
        return newRowId;
    }

    public List<Quote> getQuotes() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                FavouriteQuoteEntry._ID,
                FavouriteQuoteEntry.COLUMN_NAME_ID,
                FavouriteQuoteEntry.COLUMN_NAME_QUOTE,
                FavouriteQuoteEntry.COLUMN_NAME_AUTHOR,
                FavouriteQuoteEntry.COLUMN_NAME_GENRE,
        };


        String sortOrder =
                FavouriteQuoteEntry.COLUMN_NAME_AUTHOR + " DESC";


        Cursor c = db.query(
                TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List<Quote> quotes = new ArrayList<>();

        while (c.moveToNext()) {
            Quote quote = new Quote();
            quote.setQuote(c.getString(c.getColumnIndexOrThrow(FavouriteQuoteEntry.COLUMN_NAME_QUOTE)));
            quote.setAuthor(c.getString(c.getColumnIndexOrThrow(FavouriteQuoteEntry.COLUMN_NAME_GENRE)));
            quote.setGenre(c.getString(c.getColumnIndexOrThrow(FavouriteQuoteEntry.COLUMN_NAME_AUTHOR)));
            quote.setId(c.getInt(c.getColumnIndexOrThrow(FavouriteQuoteEntry.COLUMN_NAME_ID)));
            quotes.add(quote);
        }


        c.close();
        db.close();
        return quotes;
    }

    public void delete(int id) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String selection = FavouriteQuoteEntry.COLUMN_NAME_ID + " = ?";

        String[] selectionArgs = {String.valueOf(id)};

        String[] columns = {String.valueOf(id)};


        db.delete(TABLE_NAME, selection, selectionArgs);
        db.close();
    }

    public Quote find(int id) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String selection = FavouriteQuoteEntry.COLUMN_NAME_ID + " = ?";

        String[] selectionArgs = {String.valueOf(id)};

        String[] projection = {
                FavouriteQuoteEntry._ID,
                FavouriteQuoteEntry.COLUMN_NAME_ID,
                FavouriteQuoteEntry.COLUMN_NAME_QUOTE,
                FavouriteQuoteEntry.COLUMN_NAME_AUTHOR,
                FavouriteQuoteEntry.COLUMN_NAME_GENRE,
        };
        Cursor c = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        Quote quote = null;
        if (c.moveToNext()) {
            quote = new Quote();
            quote.setQuote(c.getString(c.getColumnIndexOrThrow(FavouriteQuoteEntry.COLUMN_NAME_QUOTE)));
            quote.setAuthor(c.getString(c.getColumnIndexOrThrow(FavouriteQuoteEntry.COLUMN_NAME_GENRE)));
            quote.setGenre(c.getString(c.getColumnIndexOrThrow(FavouriteQuoteEntry.COLUMN_NAME_AUTHOR)));
            quote.setId(c.getInt(c.getColumnIndexOrThrow(FavouriteQuoteEntry.COLUMN_NAME_ID)));
        }

        c.close();
        db.close();
        return quote;
    }
}