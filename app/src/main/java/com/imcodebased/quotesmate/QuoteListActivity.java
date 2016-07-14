package com.imcodebased.quotesmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import adapters.ItemClickedCallback;
import adapters.QuotesRecyclerAdapter;
import customviews.CustomRecyclerView;
import helpers.IntentUtil;
import model.Quote;
import model.services.IQuotesDataLoader;
import model.services.json.DataCallback;
import model.services.json.JsonQuotesDataLoader;
import model.services.json.provider.RawJsonProvider;

public class QuoteListActivity extends AppCompatActivity {

    private CustomRecyclerView listView;

    private ArrayList<Quote> quotes;

    private QuotesRecyclerAdapter adapter;

    private IQuotesDataLoader quotesDataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        quotesDataLoader = new JsonQuotesDataLoader(getApplicationContext(), new RawJsonProvider());
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {

        quotesDataLoader.getAllAsync(new DataCallback<ArrayList<Quote>>() {
            @Override
            public void onSuccess(ArrayList<Quote> result) {

                quotes = result;
                // ahh the moment you have created a new Array... and override, it has created an anonymous class
                // for you thus the below is same as saying:
                // MyAdapter extends ArrayAdapter...
                // adapter = new QuotesArrayAdapter(QuoteListActivity.this, R
                //      .layout.quote_list_item, quotes);

                adapter = new QuotesRecyclerAdapter(quotes, new ItemClickedCallback() {
                    @Override
                    public void onClick(int position) {
                        startActivity(IntentUtil.createShareIntent(quotes.get(position).toString()));
                    }
                });
//
//                make it empty
// adapter = new QuotesArrayAdapter(QuoteListActivity.this, R
//                        .layout.quote_list_item, new ArrayList<Quote>());

                listView.setEmtpyStateView(findViewById(R.id.empty));
                listView.setLayoutManager(new LinearLayoutManager(QuoteListActivity.this));
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

    private void initUI() {
        listView = (CustomRecyclerView) findViewById(R.id.listView);
//        ViewStub emptyViewStub = (ViewStub) findViewById(R.id.empty);
//        listView.setEmptyView(emptyViewStub);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.quote_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.newquote) {
            Intent newQuoteIntent = new Intent(this, NewQuoteActivity.class);
            startActivity(newQuoteIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
