package customviews;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;

public class CustomRecyclerView extends RecyclerView {

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
            if (showEmptyStateView) {
                setVisibility(GONE);
                mEmptyStateView.setVisibility(View.VISIBLE);
                mEmptyStateView.setAlpha(0.0f);
                mEmptyStateView.animate().setDuration(500).alpha(1.0f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }

                }).start();
            } else {
                setVisibility(VISIBLE);
            }
        }
    }

    public void setEmptyStateView(View view) {
        this.mEmptyStateView = view;
    }

    public void setEmptyStateViewStub(ViewStub view) {
        this.mEmptyStateView = view.inflate();
    }

}