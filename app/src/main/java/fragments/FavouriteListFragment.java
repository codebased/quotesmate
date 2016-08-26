package fragments;

import android.database.Cursor;
import android.net.Uri;
import android.view.View;

import com.imcodebased.quotesmate.R;

import adapters.CustomViewHolder;
import adapters.FavouriteQuotesCursorAdapter;
import adapters.ItemClickedCallback;
import model.Quote;

public class FavouriteListFragment extends BaseListFragment<Quote> implements ItemClickedCallback{

    private static final String PROVIDER_NAME = "com.imcodebased.quotesmate.favouritequotes";
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/quotes");
    @Override
    public int getLayout() {
        return R.layout.recylerviewlist;
    }

    @Override
    public void initializeData() {
        super.initializeData();
        Cursor cursor = getActivity().getContentResolver().query(CONTENT_URI, null, null, null, null);

        mRecyclerView.setAdapter(new FavouriteQuotesCursorAdapter(getActivity(), cursor, this));
        onPostData();
//        mDataLoader.getAllFavouriteQuoteAsync(this);
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
    public void onClick(View v, int position) {
    }
}
