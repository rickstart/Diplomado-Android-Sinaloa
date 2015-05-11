package com.mobintum.movieexample.Models;

/**
 * Created by Rick on 11/05/15.
 */
public class Movie {

    private String title;
    private String year;
    private int criticScore;
    private int audienceScpre;
    private String synopsis;
    private String urlThumb;

    public Movie(String title, String year, int criticScore, int audienceScpre, String synopsis, String urlThumb) {
        this.title = title;
        this.year = year;
        this.criticScore = criticScore;
        this.audienceScpre = audienceScpre;
        this.synopsis = synopsis;
        this.urlThumb = urlThumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCriticScore() {
        return criticScore;
    }

    public void setCriticScore(int criticScore) {
        this.criticScore = criticScore;
    }

    public int getAudienceScpre() {
        return audienceScpre;
    }

    public void setAudienceScpre(int audienceScpre) {
        this.audienceScpre = audienceScpre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getUrlThumb() {
        return urlThumb;
    }

    public void setUrlThumb(String urlThumb) {
        this.urlThumb = urlThumb;
    }
}
