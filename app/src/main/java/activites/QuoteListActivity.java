package activites;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.imcodebased.quotesmate.R;

import fragments.QuoteListFragment;
import helpers.StringUtil;

public class QuoteListActivity extends BaseToolbarActivity {
    public static final String EXTRA_GENRE = "EXTRA_GENRE";
    public static final String EXTRA_AUTHOR = "EXTRA_AUTHOR";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list_fragment);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        String title = getIntent().hasExtra(EXTRA_GENRE)
                ? StringUtil.capitalFirstLetter(getIntent().getStringExtra(EXTRA_GENRE))
                : getIntent().hasExtra(EXTRA_AUTHOR) ? "Author" : "";

        toolbarView.setTitle(title + " Quotes");

        getSupportFragmentManager().beginTransaction().add(R.id.container, new QuoteListFragment()).commit();
    }
}
