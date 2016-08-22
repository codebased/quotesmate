package fragments;

import android.content.Intent;

import com.imcodebased.quotesmate.R;

import activites.QuoteListActivity;
import helpers.StringUtil;
import model.Genre;

public class GenreListFragment extends BaseListFragment<Genre> {

    @Override
    public int getLayout() {
        return R.layout.recylerviewlist;
    }

    @Override
    public void initializeData() {
        super.initializeData();
        mDataLoader.getAllGenreAsync(this);
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
    public void onItemClicked(Genre item) {
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
}
