package com.imcodebased.quotesmate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import helpers.IntentUtil;
import model.Quote;
import model.Services.IQuotesDataLoader;
import model.Services.StaticQuotesDataLoader;

public class QuoteListActivity extends AppCompatActivity {

    private ListView listView;

    private List<Quote> quotes;

    private ArrayAdapter<Quote> adapter;

    private IQuotesDataLoader quotesDataLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        quotesDataLoader = new StaticQuotesDataLoader();
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {

        quotes = quotesDataLoader.getAll();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, quotes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(QuoteListActivity.this, quotes.get(position).toString(), Toast.LENGTH_LONG).show();
                startActivity(IntentUtil.createShareIntent(quotes.get(position).toString()));
            }
        });
    }

    private void initUI() {
        listView = (ListView) findViewById(R.id.listView);
    }
}
