package services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import model.Quote;

public class SqliteStore implements IStore {

    private final FavouriteQuoteContract mf;
    private final QuoteReaderDbHelper mDbHelper;


    public SqliteStore(Context context) {
        mDbHelper = new QuoteReaderDbHelper(context);
        mf = new FavouriteQuoteContract();
    }

    public final class FavouriteQuoteContract {
        public static final String TABLE_NAME = "favouritequotes";
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // To prevent someone from accidentally instantiating the contract class,
        // give it an empty constructor.
        public FavouriteQuoteContract() {
        }

        /*
        * Inner class that defines the table contents
        * Note: By implementing the BaseColumns interface, your inner class can inherit a primary key field called _ID that some
        * Android classes such as cursor adaptors will expect it to have.
        * It's not required, but this can help your database work harmoniously with the Android framework.
        * */
        public abstract class FavouriteQuoteEntry implements BaseColumns {
            public static final String COLUMN_NAME_ID = "id";
            public static final String COLUMN_NAME_QUOTE = "quote";
            public static final String COLUMN_NAME_AUTHOR = "author";
            public static final String COLUMN_NAME_GENRE = "genre";

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
        }

        public long insert(Quote quote) {

// Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_ID, quote.getId());
            values.put(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_QUOTE, quote.getQuote());
            values.put(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_AUTHOR, quote.getAuthor());
            values.put(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_GENRE, quote.getGenre());

// Insert the new row, returning the primary key value of the new row
            long newRowId;
            newRowId = db.insert(
                    FavouriteQuoteContract.TABLE_NAME,
                    null,
                    values);

            return newRowId;
        }

        public List<Quote> getQuotes() {
            SQLiteDatabase db = mDbHelper.getReadableDatabase();

            // Define a projection that specifies which columns from the database
// you will actually use after this query.
            String[] projection = {
                    FavouriteQuoteContract.FavouriteQuoteEntry._ID,
                    FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_QUOTE,
                    FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_AUTHOR,
                    FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_GENRE,
            };


            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_AUTHOR + " DESC";


            Cursor c = db.query(
                    FavouriteQuoteContract.TABLE_NAME,  // The table to query
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
                quote.setQuote(c.getString(c.getColumnIndexOrThrow(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_QUOTE)));
                quote.setAuthor(c.getString(c.getColumnIndexOrThrow(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_GENRE)));
                quote.setGenre(c.getString(c.getColumnIndexOrThrow(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_AUTHOR)));
                quotes.add(quote);
            }

            return quotes;
        }

        public void delete(int id) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            // Define 'where' part of query.
            String selection = FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_ID + " LIKE ?";

            // Specify arguments in placeholder order.
            String[] selectionArgs = {String.valueOf(id)};

            db.delete(FavouriteQuoteContract.TABLE_NAME, selection, selectionArgs);
        }
    }

    @Override
    public void saveFavouriteQuote(Quote quote) {
        mf.insert(quote);
    }

    @Override
    public void saveFavouriteQuotes(List<Quote> quotes) {

        for (Quote quote :
                quotes) {
            mf.insert(quote);
        }
    }

    @Override
    public List<Quote> getFavouriteQuotes() {
        return mf.getQuotes();
    }

    @Override
    public void deleteQuote(int id) {
        mf.delete(id);
    }

    @Override
    public void deleteQuotes(int... ids) {

        for (int id :
                ids) {
            mf.delete(id);
        }
    }
}
