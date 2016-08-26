package adapters;

import android.view.View;

import model.Quote;

/**
 * Created by codebased on 26/08/16.
 */
public interface FavouriteItemClickedCallback {
    void onClick(View v, Quote position);
}
