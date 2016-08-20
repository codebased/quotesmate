package fragments;

import com.imcodebased.quotesmate.R;

import model.Quote;

public class FavouriteListFragment extends BaseListFragment<Quote> {

    @Override
    public int getLayout() {
        return R.layout.recylerviewlist;
    }

    @Override
    public void initializeData() {
        super.initializeData();
        mDataLoader.getAllFavouriteQuoteAsync(this);
    }

    @Override
    public String getHeader(Quote item) {
        return item.getQuote();
    }

    @Override
    public String getSubHeader(Quote item) {
        return item.getAuthor();
    }

    @Override
    public void onItemClicked(Quote item) {

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
