package adapters;

import android.view.View;

// TODO: 22/08/16 replace with event bus
public interface ItemClickedCallback {
    void onClick(View v, int position);
}
