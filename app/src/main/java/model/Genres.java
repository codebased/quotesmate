package model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Genres {

    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
