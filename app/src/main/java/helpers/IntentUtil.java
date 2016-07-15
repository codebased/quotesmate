package helpers;

import android.content.Intent;

/**
 * Created by codebased on 13/07/16.
 */
public class IntentUtil {

    public static Intent createShareIntent(String message) {
        Intent send = new Intent(Intent.ACTION_SEND);
        send.setType("text/plain");
        send.putExtra(Intent.EXTRA_TEXT, message);
        return Intent.createChooser(send, "Share using");
    }
}
