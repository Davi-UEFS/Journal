package Model.Media;

public class Season implements Comparable<Season>{
    private double rating;
    private final int seasonNumber;
    private String review;
    private boolean seen;
    private int episodeCount;

    public Season(int seasonNumber, int episodeCount) {
        this.seasonNumber = seasonNumber;
        this.episodeCount = episodeCount;
    }

    @Override
    public int compareTo(Season other) {
        return Integer.compare(this.seasonNumber, other.seasonNumber);
    }

    public String toString() {
        return "Temporada: " + seasonNumber + " - " + episodeCount + " Episódios";
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
