package fragments;

import android.graphics.PorterDuff;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.imcodebased.quotesmate.R;

import adapters.CustomViewHolder;
import helpers.IntentUtil;
import helpers.StringUtil;
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
        holder.getImg().setImageResource(R.drawable.ic_delete);
        setColor(holder.getImg(), R.color.itemColor);
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
        return StringUtil.capitalFirstLetter(item.getAuthor());
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

    private void setColor(View v, @ColorRes int res) {
        int color = ContextCompat.getColor(v.getContext(), res);
        ((ImageView) v).getDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }
}
