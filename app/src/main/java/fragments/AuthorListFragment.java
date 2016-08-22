package fragments;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.imcodebased.quotesmate.R;

import activites.QuoteListActivity;
import adapters.CustomViewHolder;
import adapters.ItemOffsetDecoration;
import helpers.StringUtil;
import model.Author;

public class AuthorListFragment extends BaseListFragment<Author> {

    @Override
    public int getLayout() {
        return R.layout.recylerviewlist;
    }

    @Override
    public void initializeData() {
        super.initializeData();
        mDataLoader.getAllAuthorAsync(this);
    }

    @Override
    protected void onPostBindView(CustomViewHolder holder, int position) {
        holder.getImg().setVisibility(View.GONE);
    }

    @Override
    protected int getListItemLayout() {
        return R.layout.author_quote_item;
    }

    @Override
    public String getHeader(Author item) {
        return StringUtil.capitalFirstLetter(item.getAuthor());
    }

    @Override
    public String getSubHeader(Author item) {
        return String.format("%s Quotes", item.getQuotes());
    }

    @Override
    public void onItemClicked(View v, Author item) {
        Intent intent = new Intent(getContext(), QuoteListActivity.class);
        intent.putExtra(QuoteListActivity.EXTRA_AUTHOR, item.getAuthor());
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
       return new GridLayoutManager(getContext(), 2) ;
    }

    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.item_offset);
        return itemDecoration;
    }
}
