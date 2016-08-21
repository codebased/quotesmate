package activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.imcodebased.quotesmate.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewQuoteActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbarView)
    protected Toolbar toolbarView;

    @BindView(R.id.quote)
    protected EditText quoteView;

    @BindView(R.id.quoteMetabase)
    protected EditText quoteMetabaseView;

    @BindView(R.id.progressBarView)
    protected ProgressBar progressBarView;

    @BindView(R.id.buttonView)
    protected Button buttonView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quote);
        ButterKnife.bind(this);

        buttonView.setOnClickListener(this);
        toolbarView.setTitle("New Quote");
    }

    @Override
    public void onClick(View v) {
        sendEmail();
    }

    private void sendEmail() {
        progressBarView.setVisibility(View.VISIBLE);
    }
}
