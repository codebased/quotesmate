package com.imcodebased.quotesmate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuoteListActivity extends AppCompatActivity {

    private ListView listView;

    private ArrayList<String> collection;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        populateValue();
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void populateValue() {
        collection = new ArrayList<>();
        collection.add("Item1");
        collection.add("Item2");
        collection.add("Item3");
        collection.add("Item4");
        collection.add("Item5");
    }

    private void initUI() {
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, collection);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(QuoteListActivity.this, collection.get(position), Toast.LENGTH_LONG).show();
            }
        });

    }
}
