package activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.imcodebased.quotesmate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import fragments.QuoteListFragment;
import helpers.StringUtil;

public class QuoteListActivity extends AppCompatActivity {
    public static final String EXTRA_GENRE = "EXTRA_GENRE";
    public static final String EXTRA_AUTHOR = "EXTRA_AUTHOR";

    @BindView(R.id.toolbarView)
    protected Toolbar toolbarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list_fragment);

        ButterKnife.bind(this);

        String title = getIntent().hasExtra(EXTRA_GENRE)
                ? StringUtil.capitalFirstLetter(getIntent().getStringExtra(EXTRA_GENRE))
                : getIntent().hasExtra(EXTRA_AUTHOR) ? "Author" : "";

        toolbarView.setTitle(title + " Quotes");

        getSupportFragmentManager().beginTransaction().add(R.id.container, new QuoteListFragment()).commit();
    }
}
