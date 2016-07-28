package fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imcodebased.quotesmate.R;

import java.util.List;

import adapters.CustomRecyclerAdapter;
import customviews.CustomRecyclerView;
import model.services.IQuotesDataLoader;
import model.services.json.JsonQuotesDataLoader;
import model.services.json.provider.RetroServiceJsonProvider;

public abstract class BaseFragment<T> extends Fragment implements SwipeRefreshLayout.OnRefreshListener{


    protected CustomRecyclerView listView;

    protected List<T> quotes;

    protected CustomRecyclerAdapter<T> adapter;

    protected IQuotesDataLoader quotesDataLoader;

    private SwipeRefreshLayout swipeRefreshView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), null);
    }

    public abstract int getLayout();

    public abstract void initializeData();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        quotesDataLoader = new JsonQuotesDataLoader(view.getContext(), new RetroServiceJsonProvider());
        listView = (CustomRecyclerView) view.findViewById(R.id.listView);
        swipeRefreshView = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshView);
        listView.setEmtpyStateView(view.findViewById(R.id.empty));
        swipeRefreshView.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initializeData();
    }

    @Override
    public void onRefresh() {
        swipeRefreshView.setRefreshing(false);
    }
}
