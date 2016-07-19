package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imcodebased.quotesmate.R;

import java.util.ArrayList;

import model.Quote;

/**
 * Created by codebased on 14/07/16.
 */
public class QuotesRecyclerAdapter extends RecyclerView.Adapter<QuoteViewHolder> implements ItemClickedCallback {

    private final ItemClickedCallback ItemClickedCallback;
    private ArrayList<Quote> items;

    public QuotesRecyclerAdapter(ArrayList<Quote> items, ItemClickedCallback itemClickedCallback) {
        this.items = items;
        this.ItemClickedCallback = itemClickedCallback;
    }

    @Override
    public QuoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_list_item, parent, false);
        return new QuoteViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(QuoteViewHolder holder, int position) {
        holder.getHeaderView().setText(items.get(position).getQuote());
        holder.getSubHeaderView().setText(items.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(int position) {
        this.ItemClickedCallback.onClick(position);
    }
}
