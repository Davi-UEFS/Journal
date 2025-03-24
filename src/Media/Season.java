package Media;

public class Season {
    private double rating;
    private int seasonNumber;
    private String review;
    private boolean seen;

    public Season(int seasonNumber){
        this.seasonNumber = seasonNumber;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getSeasonNumber(){
        return seasonNumber;
    }
}
