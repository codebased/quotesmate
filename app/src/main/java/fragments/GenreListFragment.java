package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imcodebased.quotesmate.R;

import activites.QuoteListActivity;
import adapters.CustomViewHolder;
import adapters.ItemOffsetDecoration;
import applications.MainApplication;
import helpers.StringUtil;
import model.Genre;

public class GenreListFragment extends BaseListFragment<Genre> {

    @Override
    public int getLayout() {
        return R.layout.recylerviewlist;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ((MainApplication) getActivity().getApplication()).getAppComponent().inject(this);

        return view;
    }

    @Override
    public void initializeData() {
        super.initializeData();
        mDataLoader.getAllGenreAsync(this);
    }

    @Override
    protected void onPostBindView(CustomViewHolder holder, int position) {
        holder.getImg().setVisibility(View.GONE);

    }

    @Override
    protected int getListItemLayout() {
        return R.layout.genre_quote_item;
    }

    @Override
    public String getHeader(Genre item) {
        return StringUtil.capitalFirstLetter(item.getGenre());
    }

    @Override
    public String getSubHeader(Genre item) {
        return String.format("%s Quotes", item.getQuotes());
    }

    @Override
    public void onItemClicked(View v, Genre item) {
        Intent intent = new Intent(getContext(), QuoteListActivity.class);
        intent.putExtra(QuoteListActivity.EXTRA_GENRE, item.getGenre());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();

        initializeData();
    }

    @Override
    public void onPreInit() {
        super.onPreInit();
        this.showProgressDialog("Loading...");
    }

    @Override
    public void onPostData() {
        super.onPostData();
        this.hideProgressDialog();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.item_offset);
        return itemDecoration;
    }
}
