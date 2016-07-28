package model.mail;

import java.util.List;

/**
 * Created by codebased on 14/01/16.
 */
public class EmailNotificationMessage extends NotificationMessage {

    private EmailAddress fromAddress;
    private EmailAddress replyToAddress;
    private List<EmailAddress> toAddress;
    private String emailType;

    public EmailNotificationMessage() {
        this.setNotificationType("email");
    }

    public List<EmailAddress> getToAddress() {
        return toAddress;
    }

    public void setToAddress(List<EmailAddress> toAddress) {
        this.toAddress = toAddress;
    }

    public EmailAddress getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(EmailAddress fromAddress) {
        this.fromAddress = fromAddress;
    }

    public EmailAddress getReplyToAddress() {
        return replyToAddress;
    }

    public void setReplyToAddress(EmailAddress replyToAddress) {
        this.replyToAddress = replyToAddress;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

}
