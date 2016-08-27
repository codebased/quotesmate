package services;

import model.ResponseBase;
import model.mail.EmailNotificationMessage;
import services.json.DataCallback;

/**
 * Created by codebased on 27/08/16.
 */
public interface NotificationService {
    void sendEmail(EmailNotificationMessage notificationMessage, String packageName, final DataCallback<ResponseBase> callback);
}
