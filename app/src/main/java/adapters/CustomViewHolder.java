package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.imcodebased.quotesmate.R;

public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView headerView;
    private final TextView subHeaderView;
    private final ImageView img;
    private final ItemClickedCallback itemClickedCallback;

    public CustomViewHolder(View itemView, ItemClickedCallback itemClickedCallback) {
        super(itemView);

        this.headerView = (TextView) itemView.findViewById(R.id.headerView);
        this.subHeaderView = (TextView) itemView.findViewById(R.id.subHeaderView);
        this.img = (ImageView) itemView.findViewById(R.id.leftImageView);
        this.itemClickedCallback = itemClickedCallback;
        itemView.setOnClickListener(this);

    }

    public TextView getHeaderView() {
        return headerView;
    }

    public TextView getSubHeaderView() {
        return subHeaderView;
    }

    public ImageView getImg() {
        return img;
    }

    @Override
    public void onClick(View v) {
        if (itemClickedCallback != null) {
            itemClickedCallback.onClick(getAdapterPosition());
        }
//        v.getContext().startActivity(IntentUtil.createShareIntent(headerView.getText().toString()));
    }
}
