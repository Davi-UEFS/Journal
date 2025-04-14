package Model.Medias;

import Model.Genres;


public abstract class Media {
    protected final String title;
    protected final int year;
    protected final Genres genre;
    protected double rating;
    protected boolean seen = false;
    protected String review;

    public Media(String title, int year, Genres genre) {
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    public double getRating(){
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Genres getGenre() {
        return genre;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getId() {
        return title.hashCode() + 227 *  year;
    }
}
