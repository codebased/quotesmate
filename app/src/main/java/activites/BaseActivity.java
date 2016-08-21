package activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.imcodebased.quotesmate.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_base_activity, menu);
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
