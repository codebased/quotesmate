package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.imcodebased.quotesmate.R;

public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView headerView;
    private final TextView subHeaderView;
    private final ImageView leftImageView;
    private final ItemClickedCallback itemClickedCallback;
    private final ImageView rightImageView;

    public CustomViewHolder(View itemView, ItemClickedCallback itemClickedCallback) {
        super(itemView);

        this.headerView = (TextView) itemView.findViewById(R.id.headerView);
        this.subHeaderView = (TextView) itemView.findViewById(R.id.subHeaderView);
        this.leftImageView = (ImageView) itemView.findViewById(R.id.leftImageView);
        this.rightImageView = (ImageView) itemView.findViewById(R.id.rightImageView);
        this.itemClickedCallback = itemClickedCallback;
        itemView.setOnClickListener(this);
        this.leftImageView.setOnClickListener(this);
//        this.leftImageView.setOnClickListener(new View.OnClickListener() {
//            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void onClick(View v) {
//                CustomViewHolder.this.leftImageView.getDrawable().setTint(Color.RED);
//            }
//        });

    }

    public TextView getHeaderView() {
        return headerView;
    }

    public TextView getSubHeaderView() {
        return subHeaderView;
    }

    public ImageView getLeftImageView() {
        return leftImageView;
    }

    public ImageView getRightImageView() {
        return rightImageView;
    }

    @Override
    public void onClick(View v) {
        if (itemClickedCallback != null) {
            itemClickedCallback.onClick(v, getAdapterPosition());
        }

//        v.getContext().startActivity(IntentUtil.createShareIntent(headerView.getText().toString()));
    }
}
