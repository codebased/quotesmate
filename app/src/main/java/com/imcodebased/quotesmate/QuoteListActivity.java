package com.imcodebased.quotesmate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import adapters.ItemClickedCallback;
import adapters.QuotesRecyclerAdapter;
import customviews.CustomRecyclerView;
import helpers.FileLoaderTask;
import helpers.IntentUtil;
import model.Quote;
import model.services.IQuotesDataLoader;
import model.services.json.DataCallback;
import model.services.json.JsonQuotesDataLoader;
import model.services.json.provider.RetroServiceJsonProvider;

public class QuoteListActivity extends AppCompatActivity {

    private CustomRecyclerView listView;

    private List<Quote> quotes;

    private QuotesRecyclerAdapter adapter;

    private IQuotesDataLoader quotesDataLoader;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        quotesDataLoader = new JsonQuotesDataLoader(getApplicationContext(), new RetroServiceJsonProvider());
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();

    }

    private void initData() {

        quotesDataLoader.getAllAsync(new DataCallback<List<Quote>>() {
            @Override
            public void onSuccess(List<Quote> result) {

                quotes = result;
                // ahh the moment you have created a new Array... and override, it has created an anonymous class
                // for you thus the below is same as saying:
                // MyAdapter extends ArrayAdapter...
                // adapter = new QuotesArrayAdapter(QuoteListActivity.this, R
                //      .layout.list_item, quotes);

                adapter = new QuotesRecyclerAdapter(quotes, new ItemClickedCallback() {
                    @Override
                    public void onClick(int position) {
                        startActivity(IntentUtil.createShareIntent(quotes.get(position).toString()));
                    }
                });
//
//                make it empty
// adapter = new QuotesArrayAdapter(QuoteListActivity.this, R
//                        .layout.list_item, new ArrayList<Quote>());

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
        mProgressDialog = new ProgressDialog(this);
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
        } else if (item.getItemId() == R.id.loader) {

            FileLoaderTask fileLoaderTask = new FileLoaderTask(this, new FileLoaderTask.FileLoaderTaskCallback() {

                @Override
                public void onException(Exception exception) {
                    // will run as background operation.
                    Log.i(FileLoaderTask.TAG, String.format("onException is called by Thread ID %d", Thread.currentThread().getId()));
                    Log.i(FileLoaderTask.TAG, String.format("onException %s", exception.toString()));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(FileLoaderTask.TAG, String.format("onException.runOnUiThread is called by Thread ID %d", Thread.currentThread().getId()));
                            mProgressDialog.hide();
                        }
                    });
                }

                @Override
                public void onCancel(String reason) {
                    mProgressDialog.hide();

                }

                @Override
                public void onPreExecute() {
                    Log.i(FileLoaderTask.TAG, String.format("onPreExecute is called by Thread ID %d", Thread.currentThread().getId()));

                    mProgressDialog.setMessage("Accessing Quotes database ...");
//                    mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                    mProgressDialog.setMax(28862);
                    mProgressDialog.show();

                }

                @Override
                public void onProgress(int on, int total) {
//                    Log.i(FileLoaderTask.TAG, String.format("onProgress is called by Thread ID %d", Thread.currentThread().getId()));
                    mProgressDialog.setProgress(on);
                }

                @Override
                public void onSuccess(String result) {
                    Log.i(FileLoaderTask.TAG, String.format("onSuccess is called by Thread ID %d", Thread.currentThread().getId()));

                    mProgressDialog.hide();
                }
            });

            fileLoaderTask.execute("");
        }
        return super.onOptionsItemSelected(item);
    }
}
