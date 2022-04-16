package com.cafenoion.grizzlymovie.fragment.ticket;

/**
 * Created by ravi on 26/09/17.
 */

public class TicketModel {

    int MovieImg;
    String MovieName;
    String MovieDate;
    String MovieIntro;

    public TicketModel(int movieImg, String movieName, String movieDate, String movieIntro) {
        MovieImg = movieImg;
        MovieName = movieName;
        MovieDate = movieDate;
        MovieIntro = movieIntro;
    }

    public int getMovieImg() {
        return MovieImg;
    }

    public void setMovieImg(int movieImg) {
        MovieImg = movieImg;
    }

    public String getMovieName() {
        return MovieName;
    }

    public void setMovieName(String movieName) {
        MovieName = movieName;
    }

    public String getMovieDate() {
        return MovieDate;
    }

    public void setMovieDate(String movieDate) {
        MovieDate = movieDate;
    }

    public String getMovieIntro() {
        return MovieIntro;
    }

    public void setMovieIntro(String movieIntro) {
        MovieIntro = movieIntro;
    }
}
