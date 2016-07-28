package fragments;

import android.support.v7.widget.LinearLayoutManager;

import com.imcodebased.quotesmate.R;

import java.util.List;

import adapters.CustomRecyclerAdapter;
import adapters.ItemClickedCallback;
import model.Author;
import model.services.json.DataCallback;

public class AuthorListFragment extends BaseFragment<Author> {

    @Override
    public int getLayout() {
        return R.layout.recylerviewlist;
    }

    @Override
    public void initializeData() {

        quotesDataLoader.getAllAuthorAsync(new DataCallback<List<Author>>() {

            @Override
            public void onSuccess(List<Author> result) {

                quotes = result;
                // ahh the moment you have created a new Array... and override, it has created an anonymous class
                // for you thus the below is same as saying:
                // MyAdapter extends ArrayAdapter...
                // adapter = new QuotesArrayAdapter(QuoteListActivity.this, R
                //      .layout.list_item, quotes);

                adapter = new CustomRecyclerAdapter<Author>(quotes, new ItemClickedCallback() {
                    @Override
                    public void onClick(int position) {

                    }
                }) {
                    @Override
                    public String getHeader(Author item) {
                        return null;
                    }

                    @Override
                    public String getSubHeader(Author item) {
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
