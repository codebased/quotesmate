package fragments;

import android.support.v7.widget.LinearLayoutManager;

import com.imcodebased.quotesmate.R;

import java.util.List;

import adapters.CustomRecyclerAdapter;
import adapters.ItemClickedCallback;
import helpers.StringUtil;
import model.Genre;
import model.services.json.DataCallback;

public class GenreListFragment extends BaseListFragment<Genre> {

    @Override
    public int getLayout() {
        return R.layout.recylerviewlist;
    }

    @Override
    public void initializeData() {
        super.initializeData();
        quotesDataLoader.getAllGenreAsync(this);
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
