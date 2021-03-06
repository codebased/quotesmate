package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by codebased on 26/02/16.
 */
public class Message {

    @SerializedName("description")
    private String description;

    @SerializedName("type")
    private String type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}