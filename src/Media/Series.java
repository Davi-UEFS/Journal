package Media;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Series extends Media{
    private int yearOfEnding;
    private List<String> cast;
    private List<Season> seasons; //TODO: Usar priority queue?
    private String originalTitle;
    private List<String> whereToWatch;

    public Series(String name, int year, int genre, int yearOfEnding, List<String> cast, String originalTitle, List<String> whereToWatch) {
        super(name, year, genre);
        this.yearOfEnding = yearOfEnding;
        this.cast = cast;
        this.seasons = new ArrayList<Season>();
        this.originalTitle = originalTitle;
        this.whereToWatch = whereToWatch;
    }

    public void addSeason(Season season){
        this.seasons.add(season);
    }

    public void showCast(){
        cast.forEach(actor->System.out.println(actor));
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

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
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
