package com.cafenoion.grizzlymovie.fragment.moviehome;


public class MovieHomeModel {

    String movie_id;
    int movie_poster;
    String movie_name;
    int rating;

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public int getMovie_poster() {
        return movie_poster;
    }

    public void setMovie_poster(int movie_poster) {
        this.movie_poster = movie_poster;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public MovieHomeModel(String movie_id, int movie_poster, String movie_name, int rating) {
        this.movie_id = movie_id;
        this.movie_poster = movie_poster;
        this.movie_name = movie_name;
        this.rating = rating;
    }
}
