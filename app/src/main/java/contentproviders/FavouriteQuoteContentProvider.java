package contentproviders;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.security.NoSuchProviderException;
import java.util.HashMap;

import storage.QuoteReaderDbHelper;
import storage.tables.FavouriteQuoteContract;

public class FavouriteQuoteContentProvider extends ContentProvider {

    private static final String PROVIDER_NAME = "com.imcodebased.quotesmate.favouritequotes";

    private QuoteReaderDbHelper dbHelper;

    private static final int ALLFAVOURITEQUOTES = 0;
    private static final int FAVOURITEQUOTEBYID = 1;
    private static HashMap<String, String> FAVOURITE_QUOTES_PROJECTION_MAP;

    private static UriMatcher URL_MATCHER;

    static {
        URL_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URL_MATCHER.addURI(PROVIDER_NAME,
                "quotes", ALLFAVOURITEQUOTES);
        URL_MATCHER.addURI(PROVIDER_NAME,
                "quotes/#", FAVOURITEQUOTEBYID);

        FAVOURITE_QUOTES_PROJECTION_MAP = new HashMap<>();
        FAVOURITE_QUOTES_PROJECTION_MAP.put(FavouriteQuoteContract.FavouriteQuoteEntry._ID, "_id");
        FAVOURITE_QUOTES_PROJECTION_MAP.put(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_ID, "id");
        FAVOURITE_QUOTES_PROJECTION_MAP.put(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_QUOTE, "quote");
        FAVOURITE_QUOTES_PROJECTION_MAP.put(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_AUTHOR, "author");
        FAVOURITE_QUOTES_PROJECTION_MAP.put(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_GENRE, "genre");
    }

    @Override
    public boolean onCreate() {
        dbHelper = new QuoteReaderDbHelper(getContext());
        return true;
    }

    // convention is vnd.android.cursor.dir/vnd.<package>
    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (URL_MATCHER.match(uri)) {
            case ALLFAVOURITEQUOTES:
                return
                        "vnd.android.cursor.dir/vnd.com.imcodebased.quotesmate.provider.favouritequotes";
            case FAVOURITEQUOTEBYID:
                return
                        "vnd.android.cursor.item/vnd.com.imcodebased.quotesmate.provider.favouritequotes";
            default:
                throw new IllegalArgumentException("Unknown URL " + uri);
        }
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        switch (URL_MATCHER.match(uri)) {
            case ALLFAVOURITEQUOTES:
                qb.setTables(FavouriteQuoteContract.TABLE_NAME);
                qb.setProjectionMap(FAVOURITE_QUOTES_PROJECTION_MAP);
                break;
            case FAVOURITEQUOTEBYID:
                qb.setTables(FavouriteQuoteContract.TABLE_NAME);
                qb.appendWhere(FavouriteQuoteContract.FavouriteQuoteEntry._ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URL " + uri);
        }

        String orderBy;

        if (TextUtils.isEmpty(sortOrder)) {
            orderBy = FavouriteQuoteContract.FavouriteQuoteEntry.DEFAULT_SORT_ORDER;
        } else {
            orderBy = sortOrder;
        }

        Cursor c = qb.query(dbHelper.getReadableDatabase(), projection, selection, selectionArgs, null,
                null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        long rowID;
        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {

            throw new IllegalArgumentException("content values are not specified.");

//            values = new ContentValues();
        }
        if (URL_MATCHER.match(uri) != ALLFAVOURITEQUOTES) {
            throw new IllegalArgumentException("Unknown URL " + uri);
        }

        final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/quotes");


        rowID = dbHelper.getWritableDatabase().insert(FavouriteQuoteContract.TABLE_NAME, null, values);
        if (rowID > 0) {
            Uri url = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int count;
        long rowId = 0;
        switch (URL_MATCHER.match(uri)) {
            case ALLFAVOURITEQUOTES:
                count = dbHelper.getWritableDatabase().delete("favouritequotes", selection, selectionArgs);
                break;
            case FAVOURITEQUOTEBYID:
                String segment = uri.getPathSegments().get(1);
                rowId = Long.parseLong(segment);
                count = dbHelper.getWritableDatabase()
                        .delete(FavouriteQuoteContract.TABLE_NAME, FavouriteQuoteContract.FavouriteQuoteEntry._ID + "="
                                + rowId
                                + (!TextUtils.isEmpty(selection) ? " AND (" + selection
                                + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URL " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        try {
            throw new NoSuchProviderException("There is no implementation for update.");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
