package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imcodebased.quotesmate.R;

import java.util.List;

/**
 * Created by codebased on 14/07/16.
 */
public abstract class CustomRecyclerAdapter<T> extends RecyclerView.Adapter<CustomViewHolder> implements ItemClickedCallback {

    private final ItemClickedCallback ItemClickedCallback;
    private List<T> items;

    public CustomRecyclerAdapter(List<T> items, ItemClickedCallback itemClickedCallback) {
        this.items = items;
        this.ItemClickedCallback = itemClickedCallback;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CustomViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.getHeaderView().setText(getHeader(items.get(position)));
        holder.getSubHeaderView().setText(getSubHeader(items.get(position)));
    }

    public abstract String getHeader(T item);
    public abstract String getSubHeader(T item);

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(int position) {
        this.ItemClickedCallback.onClick(position);
    }
}
