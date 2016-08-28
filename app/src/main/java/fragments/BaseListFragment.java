package fragments;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.imcodebased.quotesmate.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import activites.BaseToolbarActivity;
import adapters.CustomRecyclerAdapter;
import adapters.CustomViewHolder;
import adapters.ItemClickedCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import customviews.CustomRecyclerView;
import services.IDataLoader;
import services.json.DataCallback;
import services.json.JsonDataLoader;
import services.json.provider.IJsonProvider;

public abstract class BaseListFragment<T> extends Fragment implements SwipeRefreshLayout.OnRefreshListener, DataCallback<List<T>> {

    @BindView(R.id.listView)
    protected CustomRecyclerView listView;

    @BindView(R.id.swipeRefreshView)
    protected SwipeRefreshLayout swipeRefreshView;

    @BindView(R.id.empty)
    protected ViewStub emptyListView;

    @Inject
    protected IJsonProvider dataProvider;

    protected List<T> items;
    protected CustomRecyclerAdapter<T> adapter;
    protected IDataLoader mDataLoader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emptyListView.setLayoutResource(getEmptyStateLayout());
        listView.setEmptyStateView(emptyListView);
        listView.setLayoutManager(getLayoutManager());
        listView.addItemDecoration(getItemDecoration());

        listView.setHasFixedSize(false);
        mDataLoader = new JsonDataLoader(view.getContext(), dataProvider);
        swipeRefreshView.setOnRefreshListener(this);

        swipeRefreshView.setColorSchemeResources(
                R.color.refresh_progress);
    }

    @Override
    public void onResume() {
        super.onResume();
        initializeData();
    }

    @Override
    @CallSuper
    public void onRefresh() {
        swipeRefreshView.setRefreshing(false);
    }

    public abstract int getLayout();

    @CallSuper
    public void initializeData() {
        onPreInit();
    }

    @Override
    public void onSuccess(List<T> result) {

        items = result;
        adapter = new CustomRecyclerAdapter<T>(items, getListItemLayout(), new ItemClickedCallback() {
            @Override
            public void onClick(View v, int position) {
                onItemClicked(v, items.get(position));
//                startActivity(IntentUtil.createShareIntent(items.get(position).toString()));
            }
        }) {
            @Override
            protected void onPostBindViewHolder(CustomViewHolder holder, int position) {
                BaseListFragment.this.onPostBindView(holder, position);
            }

            @Override
            public String getHeader(T item) {
                return BaseListFragment.this.getHeader(item);
            }

            @Override
            public String getSubHeader(T item) {
                return BaseListFragment.this.getSubHeader(item);
            }

        };

        listView.setAdapter(adapter);

        onPostData();
    }

    protected abstract void onPostBindView(CustomViewHolder holder, int position);

    protected abstract int getListItemLayout();

    @Override
    public void onFailure(String error) {
        adapter = new CustomRecyclerAdapter<T>(new ArrayList<T>(), getListItemLayout(), new ItemClickedCallback() {
            @Override
            public void onClick(View v, int position) {

            }
        }) {
            @Override
            protected void onPostBindViewHolder(CustomViewHolder holder, int position) {

            }

            @Override
            public String getHeader(T item) {
                return null;
            }

            @Override
            public String getSubHeader(T item) {
                return null;
            }
        };

        onPostData();
    }

    public abstract String getHeader(T item);

    public abstract String getSubHeader(T item);

    public abstract void onItemClicked(View v, T item);

    public void onPreInit() {

    }

    public void onPostData() {
    }

    protected void showProgressDialog(String message) {
        if (isAdded()) {
            ((BaseToolbarActivity) getActivity()).showProgressDialog(message);
        }
    }

    protected void hideProgressDialog() {
        if (isAdded()) {
            ((BaseToolbarActivity) getActivity()).hideProgressDialog();
        }
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {
        };
    }

    public int getEmptyStateLayout() {
        return R.layout.empty_list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}