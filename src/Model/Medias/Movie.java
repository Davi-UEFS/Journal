package Model.Medias;
import Model.Genres;

import java.time.Duration;
import java.util.List;

public class Movie extends Media {
    private final List<String> cast;
    private final Duration duration; //TODO: TROCAR PRA DOUBLE
    private final String direction;
    private final String script;
    private final String originalTitle;
    private final List<String> whereToWatch;

    
    public Movie(String name, int year, Genres genre, List<String> cast, Duration duration, String direction,
                 String script, String originalTitle, List<String> whereToWatch) {
        super(name, year, genre);
        this.cast = cast;
        this.duration = duration;
        this.direction = direction;
        this.script = script;
        this.originalTitle = originalTitle;
        this.whereToWatch = whereToWatch;
    }

    public void showCast(){
        cast.forEach(actor->System.out.println(actor));
    }

    public List<String> getCast() {
        return cast;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getDirection() {
        return direction;
    }

    public String getScript() {
        return script;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public List<String> getWhereToWatch() {
        return whereToWatch;
    }

    @Override
    public String toString() {
        return "\n" + title + " (" + year + ")\nDuração: " + duration.toString() + " minutos\nDireção: " +
                direction + "\nTítulo original: " + originalTitle + "\nElenco: " + cast;
    }

}
