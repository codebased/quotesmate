package com.imcodebased.quotesmate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import adapters.QuotesArrayAdapter;
import helpers.IntentUtil;
import model.Quote;
import model.services.IDataLoader;
import model.services.json.DataCallback;
import model.services.json.JsonDataLoader;
import model.services.json.provider.HttpJsonProvider;

public class LegacyQuoteListActivity extends AppCompatActivity {

    private ListView listView;

    private List<Quote> quotes;

    private ArrayAdapter<Quote> adapter;

    private IDataLoader quotesDataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legacy_quote_list);
        quotesDataLoader = new JsonDataLoader(getApplicationContext(), new HttpJsonProvider());
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {

        quotesDataLoader.getAllRandomQuoteAsync(new DataCallback<List<Quote>>() {
            @Override
            public void onSuccess(List<Quote> result) {

                quotes = result;
                // ahh the moment you have created a new Array... and override, it has created an anonymous class
                // for you thus the below is same as saying:
                // MyAdapter extends ArrayAdapter...
                adapter = new QuotesArrayAdapter(LegacyQuoteListActivity.this, R
                        .layout.list_item, quotes);
//
//                make it empty
// mTabFragmentPagerAdapter = new QuotesArrayAdapter(OldQuoteListActivity.this, R
//                        .layout.quote_list_item, new ArrayList<Quote>());

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(LegacyQuoteListActivity.this, quotes.get(position).toString(), Toast.LENGTH_LONG).show();
                        startActivity(IntentUtil.createShareIntent(quotes.get(position).toString()));
                    }
                });
            }

            @Override
            public void onFailure(String error) {

            }
        });


    }

    private void initUI() {
        listView = (ListView) findViewById(R.id.listView);
        ViewStub emptyViewStub = (ViewStub) findViewById(R.id.empty);
        listView.setEmptyView(emptyViewStub);
    }
}

