package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by codebased on 26/02/16.
 */
public class ResponseBase {

    @SerializedName("messages")
    private List<Message> messages;

    public boolean isSuccess() {
        if (this.messages == null) return true;

        for (Message message : this.messages) {
            if (message.getType().equalsIgnoreCase("error")) return false;

        }

        return true;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String concatMessage() {
        StringBuilder builder = new StringBuilder();

        for (Message message :
                getMessages()) {
            builder.append(message.getDescription());
        }

        return builder.toString();
    }
}
