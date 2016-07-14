package customviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;

/**
 * Created by codebased on 14/07/16.
 */
public  class CustomRecyclerView extends RecyclerView {

    private View mEmptyStateView;

    private AdapterDataObserver mDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            updateEmptyState();
        }
    };

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (getAdapter() != null) {
            getAdapter().unregisterAdapterDataObserver(mDataObserver);
        }

        if (adapter != null) {
            adapter.registerAdapterDataObserver(mDataObserver);
        }
        super.setAdapter(adapter);
        updateEmptyState();
    }

    public void updateEmptyState() {
        if (mEmptyStateView != null && getAdapter() != null) {
            boolean showEmptyStateView = getAdapter().getItemCount() == 0;
            mEmptyStateView.setVisibility(showEmptyStateView ? VISIBLE : GONE);
            setVisibility(showEmptyStateView ? GONE : VISIBLE);
        }
    }
    public void setEmtpyStateView(View view) {
        this.mEmptyStateView = view;
    }

    public void setEmtpyStateViewStub(ViewStub view) {
        this.mEmptyStateView = view.inflate();
    }

}