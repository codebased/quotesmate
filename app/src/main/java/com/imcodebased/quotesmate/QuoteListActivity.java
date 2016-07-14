package com.imcodebased.quotesmate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adapters.QuotesArrayAdapter;
import helpers.IntentUtil;
import model.Quote;
import model.services.IQuotesDataLoader;
import model.services.json.DataCallback;
import model.services.json.JsonQuotesDataLoader;
import model.services.json.provider.RawJsonProvider;

public class QuoteListActivity extends AppCompatActivity {

    private ListView listView;

    private ArrayList<Quote> quotes;

    private ArrayAdapter<Quote> adapter;

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
                adapter = new QuotesArrayAdapter(QuoteListActivity.this, R
                        .layout.quote_list_item, quotes) ;

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(QuoteListActivity.this, quotes.get(position).toString(), Toast.LENGTH_LONG).show();
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
    }
}
