package Model.Media;

public abstract class Media {
    private final String title;  //TODO: FINAL?
    private final int year;
    private final int genre;
    private double rating;
    private boolean seen = false;
    private String review;


    public Media(String name, int year, int genre) {
        this.title = name;
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

    public int getGenre() {
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

}
