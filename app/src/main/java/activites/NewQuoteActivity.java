package activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.imcodebased.quotesmate.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.ResponseBase;
import model.mail.EmailAddress;
import model.mail.EmailNotificationMessage;
import services.NotificationApiServiceProvider;
import services.NotificationService;
import services.json.DataCallback;

public class NewQuoteActivity extends BaseToolbarActivity implements View.OnClickListener {

    @BindView(R.id.quote)
    protected EditText quoteView;

    @BindView(R.id.quoteMetabase)
    protected EditText quoteMetabaseView;

    @BindView(R.id.buttonView)
    protected Button buttonView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quote);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        buttonView.setOnClickListener(this);
        toolbarView.setTitle("New Quote");
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem menuItem = menu.findItem(R.id.action_new_quote);
        menuItem.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        sendEmail();
    }

    private void sendEmail() {
        showProgressDialog("Please wait...");
        NotificationService notificationService = new NotificationApiServiceProvider();
        EmailNotificationMessage emailNotificationMessage = new EmailNotificationMessage();
        emailNotificationMessage.setFromAddress(new EmailAddress("Amit", "codebased@hotmail.com"));
        emailNotificationMessage.setNotificationType("email");
        emailNotificationMessage.setReplyToAddress(new EmailAddress("Amit", "codebased@hotmail.com"));
        emailNotificationMessage.setToAddress(new EmailAddress("Amit", "codebased@hotmail.com"));
        emailNotificationMessage.setSubject("New Quote");
        emailNotificationMessage.setEmailType("submitquote");
        emailNotificationMessage.setBody(quoteMetabaseView.getText().toString() + "\n" + quoteView.getText().toString());
        notificationService.sendEmail(emailNotificationMessage, "com.architectpack.quotesmate", new DataCallback<ResponseBase>() {
            @Override
            public void onSuccess(ResponseBase result) {
                hideProgressDialog();
            }

            @Override
            public void onFailure(String error) {

                hideProgressDialog();
            }
        });

    }
}
