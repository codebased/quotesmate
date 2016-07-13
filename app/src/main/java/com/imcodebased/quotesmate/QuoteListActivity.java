package com.imcodebased.quotesmate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import helpers.IntentUtil;
import model.Quote;
import model.Services.IQuotesDataLoader;
import model.Services.JsonQuotesDataLoader;
import model.Services.RawJsonProvider;

public class QuoteListActivity extends AppCompatActivity {

    private ListView listView;

    private List<Quote> quotes;

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

        quotes = quotesDataLoader.getAll();

        // ahh the moment you have created a new Array... and override, it has created an anonymous class
        // for you thus the below is same as saying:
        // MyAdapter extends ArrayAdapter...
        adapter = new ArrayAdapter<Quote>(this, android.R.layout.simple_list_item_2, android.R.id.text1
                , quotes) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(quotes.get(position).getQuote());
                text2.setText(quotes.get(position).getAuthor());
                return view;
            }
        };

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
