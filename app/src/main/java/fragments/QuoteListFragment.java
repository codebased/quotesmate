package fragments;

import android.graphics.PorterDuff;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

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
        store = new SqliteStore(getContext());
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
        Quote quote = items.get(position);
        if (store.hasQuote(quote.getId())) {
            setColor(holder.getImg(), R.color.favouriteItemColor);
        } else {
            setColor(holder.getImg(), R.color.itemColor);
        }
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
            if (store.hasQuote(item.getId())) {
                store.deleteQuote(item.getId());
                setColor(v, R.color.itemColor);
            } else {
                store.saveFavouriteQuote(item);
                setColor(v, R.color.favouriteItemColor);
            }
        } else {
            startActivity(IntentUtil.createShareIntent(item.getQuote()));
        }
    }

    private void setColor(View v, @ColorRes int res) {
        int color = ContextCompat.getColor(v.getContext(), res);
        ((ImageView) v).getDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
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
