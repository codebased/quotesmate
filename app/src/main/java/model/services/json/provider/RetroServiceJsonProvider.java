package model.services.json.provider;

import android.content.Context;

import java.util.List;

import model.Quote;
import model.Quotes;
import model.services.json.DataCallback;
import model.services.json.provider.http.QuoteHttpService;
import model.services.json.provider.http.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by codebased on 28/07/16.
 */
public class RetroServiceJsonProvider implements IJsonProvider<List<Quote>> {

    QuoteHttpService quoteHttpService = ServiceGenerator.createService(QuoteHttpService.class);

    @Override
    public void getJsonAsync(Context context, final DataCallback<List<Quote>> callback) {

        Call<Quotes> randomQuoteCall = quoteHttpService.randomize(10);
        randomQuoteCall.enqueue(new Callback<Quotes>() {
            @Override
            public void onResponse(Call<Quotes> call, Response<Quotes> response) {
                callback.onSuccess(response.body().getQuotes());

            }

            @Override
            public void onFailure(Call<Quotes> call, Throwable t) {

            }
        });
    }

    @Override
    public String getJson(Context context) {
        return null;
    }
}
