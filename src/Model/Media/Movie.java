package Model.Media;
import java.time.Duration;
import java.util.List;

public class Movie extends Media {
    private List<String> cast;
    private Duration duration;
    private String direction;
    private String script;
    private String originalTitle;
    private List<String> whereToWatch;

    
    public Movie(String name, int year, int genre, List<String> cast, Duration duration, String direction,
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
    public void setCast(List<String> cast) {
        this.cast = cast;
    }
    public Duration getDuration() {
        return duration;
    }
    public void setDuration(Duration duration) {
        this.duration = duration;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getScript() {
        return script;
    }
    public void setScript(String script) {
        this.script = script;
    }
    public String getOriginalTitle() {
        return originalTitle;
    }
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    public List<String> getWhereToWatch() {
        return whereToWatch;
    }
    public void setWhereToWatch(List<String> whereToWatch) {
        this.whereToWatch = whereToWatch;
    }


}
