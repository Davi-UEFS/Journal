package Model.Media;
import Model.Genres;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.List;

public class Series extends Media {
    private int yearOfEnding;
    private final List<String> cast;
    private final PriorityQueue<Season> seasons; //TODO: Usar priority queue?
    private final String originalTitle;
    private final List<String> whereToWatch;

    public Series(String name, int year, Genres genre, int yearOfEnding, List<String> cast, String originalTitle, List<String> whereToWatch) {
        super(name, year, genre);
        this.yearOfEnding = yearOfEnding;
        this.cast = cast;
        this.seasons = new PriorityQueue<>();
        this.originalTitle = originalTitle;
        this.whereToWatch = whereToWatch;
    }

    public void addSeason(Season season){
        this.seasons.add(season);
    }

    public void showCast(){
        cast.forEach(actor->System.out.println(actor));
    }

    public String toString() {
        String endingYear = (yearOfEnding == 9999) ? "Em andamento" : Integer.toString(yearOfEnding);
        return "\n" + title + " (" + year + " - "  + endingYear + ")\nTítulo original: " + originalTitle +
                    "\nOnde assistir: " + whereToWatch + "\nElenco: " + cast;
    }

    public int getYearOfEnding() {
        return yearOfEnding;
    }

    public void setYearOfEnding(int yearOfEnding) {
        this.yearOfEnding = yearOfEnding;
    }

    public List<String> getCast() {
        return cast;
    }

    public PriorityQueue<Season> getSeasons() {
        return seasons;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public List<String> getWhereToWatch() {
        return whereToWatch;
    }

    public int getNumberOfSeasons(){
        return seasons.size();
    }

}
