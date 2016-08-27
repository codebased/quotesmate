package fragments;

import android.view.View;

import com.imcodebased.quotesmate.R;

import activites.QuoteListActivity;
import adapters.CustomViewHolder;
import helpers.IntentUtil;
import helpers.StringUtil;
import model.Quote;
import storage.IStore;
import storage.SqliteStore;

public class QuoteListFragment extends BaseListFragment<Quote> {

    private IStore store;

    @Override
    public int getLayout() {
        return R.layout.recylerviewlist;
    }

    @Override
    public void initializeData() {
        super.initializeData();
        String genre = getActivity().getIntent().getStringExtra(QuoteListActivity.EXTRA_GENRE);
        String author = getActivity().getIntent().getStringExtra(QuoteListActivity.EXTRA_AUTHOR);

        if (StringUtil.isNotNullAndWhiteSpace(genre)) {
            mDataLoader.getAllQuoteByGenreAsync(genre, this);
        } else if (StringUtil.isNotNullAndWhiteSpace(author)) {
            mDataLoader.getAllQuoteByAuthorAsync(author, this);
        } else {
            mDataLoader.getAllRandomQuoteAsync(this);
        }
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

            if (store.hasQuote(item.getId())) {
            } else {
                store.saveFavouriteQuote(item);
            }
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
