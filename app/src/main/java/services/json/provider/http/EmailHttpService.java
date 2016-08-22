package services.json.provider.http;

import model.ResponseBase;
import model.mail.EmailNotificationMessage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmailHttpService {
    @POST("notifications/sendemail/{appName}")
    Call<ResponseBase> sendEmail(@Body EmailNotificationMessage notificationMessage, @Path("appName") String appName);
}
