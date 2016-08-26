package adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imcodebased.quotesmate.R;

import fragments.FavouriteListFragment;
import model.Quote;
import storage.tables.FavouriteQuoteContract;

public class FavouriteQuotesCursorAdapter
        extends CursorRecyclerViewAdapter<CustomViewHolder> {

    private static final String PROVIDER_NAME = "com.imcodebased.quotesmate.favouritequotes";

    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/quotes");

    private final ItemClickedCallback itemClickedCallback;

    public FavouriteQuotesCursorAdapter(Context context, Cursor cursor, ItemClickedCallback itemClickedCallback) {
        super(context, cursor);
        this.itemClickedCallback = itemClickedCallback;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_quote_item, parent, false);
        CustomViewHolder vh = new CustomViewHolder(itemView, itemClickedCallback, null);
        return vh;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, Cursor cursor) {
        Quote quote = Quote.fromCursor(cursor);
        viewHolder.getHeaderView().setText(quote.getQuote());
        viewHolder.setQuote(quote);
    }

}