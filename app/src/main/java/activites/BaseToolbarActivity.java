package activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.imcodebased.quotesmate.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseToolbarActivity extends AppCompatActivity {

    @BindView(R.id.toolbarView)
    protected Toolbar toolbarView;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(toolbarView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_base_toolbar_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_new_quote:
                openNewQuoteActivity();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openNewQuoteActivity() {
        startActivity(new Intent(this, NewQuoteActivity.class));
    }

}
