package fragments;

import android.support.v7.widget.LinearLayoutManager;

import com.imcodebased.quotesmate.R;

import java.util.List;

import adapters.CustomRecyclerAdapter;
import adapters.ItemClickedCallback;
import model.Genre;
import model.services.json.DataCallback;

/**
 * Created by codebased on 22/07/16.
 */
public class GenreListFragment extends BaseFragment<Genre> {

    @Override
    public int getLayout() {
        return R.layout.recylerviewlist;
    }

    @Override
    public void initializeData() {
        quotesDataLoader.getAllGenreAsync(new DataCallback<List<Genre>>() {

            @Override
            public void onSuccess(List<Genre> result) {

                quotes = result;
                // ahh the moment you have created a new Array... and override, it has created an anonymous class
                // for you thus the below is same as saying:
                // MyAdapter extends ArrayAdapter...
                // adapter = new QuotesArrayAdapter(QuoteListActivity.this, R
                //      .layout.list_item, quotes);

                adapter = new CustomRecyclerAdapter<Genre>(quotes, new ItemClickedCallback() {
                    @Override
                    public void onClick(int position) {

                    }
                }) {
                    @Override
                    public String getHeader(Genre item) {
                        return null;
                    }

                    @Override
                    public String getSubHeader(Genre item) {
                        return null;
                    }
                };


//                make it empty
// adapter = new QuotesArrayAdapter(QuoteListActivity.this, R
//                        .layout.list_item, new ArrayList<Quote>());

                listView.setLayoutManager(new LinearLayoutManager(getContext()));
                listView.setHasFixedSize(false);
                listView.setAdapter(adapter);


//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Toast.makeText(QuoteListActivity.this, quotes.get(position).toString(), Toast.LENGTH_LONG).show();
//                        startActivity(IntentUtil.createShareIntent(quotes.get(position).toString()));
//                    }
//                });
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @Override
    public void onRefresh() {
        super.onRefresh();

        initializeData();
    }
}
