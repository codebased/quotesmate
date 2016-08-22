package adapters;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.imcodebased.quotesmate.R;

import helpers.IntentUtil;
import storage.IStore;
import storage.SharedPreferenceStore;

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
        this.img.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                // @// TODO: 22/08/16 cange to default.
                CustomViewHolder.this.img.getDrawable().setTint(Color.RED);
            }
        });

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
            itemClickedCallback.onClick(v, getAdapterPosition());
        }

//        v.getContext().startActivity(IntentUtil.createShareIntent(headerView.getText().toString()));
    }
}
