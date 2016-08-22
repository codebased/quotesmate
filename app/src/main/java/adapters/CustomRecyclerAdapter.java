package adapters;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class CustomRecyclerAdapter<T> extends RecyclerView.Adapter<CustomViewHolder>   {

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
        return new CustomViewHolder(v, this.ItemClickedCallback);
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
