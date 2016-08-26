package adapters;

import android.content.ContentValues;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import model.Quote;
import storage.tables.FavouriteQuoteContract;

public abstract class CustomRecyclerAdapter<T> extends RecyclerView.Adapter<CustomViewHolder>   {

    private static final String PROVIDER_NAME = "com.imcodebased.quotesmate.favouritequotes";

    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/quotes");

    private final ItemClickedCallback ItemClickedCallback;
    private final int resource;
    private List<T> items;

    public CustomRecyclerAdapter(List<T> items, @LayoutRes int resource, ItemClickedCallback itemClickedCallback) {
        this.items = items;
        this.ItemClickedCallback = itemClickedCallback;
        this.resource = resource;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.resource, parent, false);
        return new CustomViewHolder(v, this.ItemClickedCallback, new FavouriteItemClickedCallback() {
            @Override
            public void onClick(View v, Quote q) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_AUTHOR, q.getAuthor());
                contentValues.put(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_GENRE, q.getGenre());
                contentValues.put(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_QUOTE, q.getQuote());
                contentValues.put(FavouriteQuoteContract.FavouriteQuoteEntry.COLUMN_NAME_ID, q.getId());
                v.getContext().getContentResolver().insert(CONTENT_URI, contentValues);
            }
        });
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.getHeaderView().setText(getHeader(items.get(position)));
        holder.getSubHeaderView().setText(getSubHeader(items.get(position)));
        this.onPostBindViewHolder(holder, position);
    }

    protected abstract void onPostBindViewHolder(CustomViewHolder holder, int position);

    public abstract String getHeader(T item);

    public abstract String getSubHeader(T item);

    @Override
    public int getItemCount() {
        return items.size();
    }

}
