package fragments;

import android.support.v7.widget.LinearLayoutManager;

import com.imcodebased.quotesmate.R;

import model.Quote;

public class QuoteListFragment extends BaseListFragment<Quote> {

    @Override
    public int getLayout() {
        return R.layout.recylerviewlist;
    }

    @Override
    public void initializeData() {
        super.initializeData();
        quotesDataLoader.getAllRandomQuoteAsync(this);
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
