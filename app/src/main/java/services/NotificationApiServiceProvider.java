package services;

import model.ResponseBase;
import model.mail.EmailNotificationMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.json.DataCallback;
import services.json.provider.http.EmailHttpService;
import services.json.provider.http.ServiceGenerator;

public class NotificationApiServiceProvider implements NotificationService {
    EmailHttpService mEmailHttpService = ServiceGenerator.createService(EmailHttpService.class);

    public void sendEmail(EmailNotificationMessage notificationMessage, String packageName, final DataCallback<ResponseBase> callback) {

        Call<ResponseBase> randomQuoteCall = mEmailHttpService.sendEmail(notificationMessage, packageName);
        randomQuoteCall.enqueue(new Callback<ResponseBase>() {
            @Override
            public void onResponse(Call<ResponseBase> call, Response<ResponseBase> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBase> call, Throwable t) {
                callback.onFailure("error");
            }
        });
    }
}

