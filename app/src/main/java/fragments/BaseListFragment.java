package fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imcodebased.quotesmate.R;

import java.util.ArrayList;
import java.util.List;

import adapters.CustomRecyclerAdapter;
import adapters.ItemClickedCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import customviews.CustomRecyclerView;
import model.services.IDataLoader;
import model.services.json.DataCallback;
import model.services.json.JsonDataLoader;
import model.services.json.provider.RetroServiceJsonProvider;

public abstract class BaseListFragment<T> extends Fragment implements SwipeRefreshLayout.OnRefreshListener, DataCallback<List<T>> {

    @BindView(R.id.listView)
    protected CustomRecyclerView listView;

    @BindView(R.id.swipeRefreshView)
    protected SwipeRefreshLayout swipeRefreshView;

    @BindView(R.id.empty)
    protected View emptyListView;

    private ProgressDialog mProgressDialog;

    protected List<T> items;
    protected CustomRecyclerAdapter<T> adapter;
    protected IDataLoader mDataLoader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), null);
        ButterKnife.bind(this, view);
        mProgressDialog = new ProgressDialog(getContext());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        listView.setEmtpyStateView(emptyListView);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.setHasFixedSize(false);
        mDataLoader = new JsonDataLoader(view.getContext(), new RetroServiceJsonProvider());
        swipeRefreshView.setOnRefreshListener(this);
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
        adapter = new CustomRecyclerAdapter<T>(items, new ItemClickedCallback() {
            @Override
            public void onClick(int position) {
                onItemClicked(items.get(position));
//                startActivity(IntentUtil.createShareIntent(items.get(position).toString()));
            }
        }) {
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

    @Override
    public void onFailure(String error) {
        adapter = new CustomRecyclerAdapter<T>(new ArrayList<T>(), new ItemClickedCallback() {
            @Override
            public void onClick(int position) {

            }
        }) {
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

    public abstract void onItemClicked(T item);

    public void onPreInit() {

    }

    public void onPostData() {
    }

    protected void showProgressDialog(String message) {
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    protected void hideProgressDialog() {
        mProgressDialog.hide();
    }
}