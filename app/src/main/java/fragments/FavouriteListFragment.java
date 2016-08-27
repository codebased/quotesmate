package fragments;

import android.view.View;

import com.imcodebased.quotesmate.R;

import adapters.CustomViewHolder;
import helpers.IntentUtil;
import model.Quote;
import storage.IStore;
import storage.SqliteStore;

public class FavouriteListFragment extends BaseListFragment<Quote> {

    private IStore store;

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
    protected void onPostBindView(CustomViewHolder holder, int position) {
    }

    @Override
    protected int getListItemLayout() {
        return R.layout.list_quote_item;
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
    public void onItemClicked(View v, Quote item) {
        if (v.getId() == R.id.leftImageView) {
            if (store == null) {
                // TODO: 26/08/16 dagger
                store = new SqliteStore(v.getContext());
            }

            store.deleteQuote(item.getId());
            onRefresh();
        } else {
            startActivity(IntentUtil.createShareIntent(item.getQuote()));
        }
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
