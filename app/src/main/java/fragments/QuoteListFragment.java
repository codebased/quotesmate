package fragments;

import android.support.v7.widget.LinearLayoutManager;

import com.imcodebased.quotesmate.R;

import java.util.List;

import adapters.CustomRecyclerAdapter;
import adapters.ItemClickedCallback;
import helpers.IntentUtil;
import model.Quote;
import model.services.json.DataCallback;

public class QuoteListFragment extends BaseFragment<Quote> {


    @Override
    public int getLayout() {
        return R.layout.recylerviewlist;
    }

    @Override
    public void initializeData() {

        quotesDataLoader.getAllAsync(new DataCallback<List<Quote>>() {

            @Override
            public void onSuccess(List<Quote> result) {

                quotes = result;
                // ahh the moment you have created a new Array... and override, it has created an anonymous class
                // for you thus the below is same as saying:
                // MyAdapter extends ArrayAdapter...
                // adapter = new QuotesArrayAdapter(QuoteListActivity.this, R
                //      .layout.list_item, quotes);

                adapter = new CustomRecyclerAdapter<Quote>(quotes, new ItemClickedCallback() {
                    @Override
                    public void onClick(int position) {
                        startActivity(IntentUtil.createShareIntent(quotes.get(position).toString()));
                    }
                }) {
                    @Override
                    public String getHeader(Quote item) {
                        return item.getQuote();
                    }

                    @Override
                    public String getSubHeader(Quote item) {
                        return item.getAuthor();
                    }
                };
//
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
}
