package fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.imcodebased.quotesmate.R;

import java.util.List;

import activites.QuoteListActivity;
import adapters.CustomRecyclerAdapter;
import adapters.ItemClickedCallback;
import helpers.StringUtil;
import model.Author;
import model.services.json.DataCallback;

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
    public String getHeader(Author item) {
        return StringUtil.capitalFirstLetter(item.getAuthor());
    }

    @Override
    public String getSubHeader(Author item) {
        return String.format("%s Quotes", item.getQuotes());
    }

    @Override
    public void onItemClicked(Author item) {
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
}
