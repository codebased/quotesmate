package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Authors {

    @SerializedName("authors")
    private List<Author> authors;

    public List<Author> getAuthors() {
        return authors;
    }

    public void getAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
