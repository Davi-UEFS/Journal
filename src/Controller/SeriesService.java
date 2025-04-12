package Controller;

import Model.Exceptions.MediaAlreadyExistsException;
import Model.Exceptions.MediaNotFoundException;
import Model.Exceptions.SeasonNotFoundException;
import Model.Genres;
import Model.Library;
import Model.Media.Media;
import Model.Media.Season;
import Model.Media.Series;

import java.util.ArrayList;
import java.util.Arrays;

public class SeriesService extends MediaService<Series> {

    public SeriesService(Library journal){
        super(journal);
    }


    public String register(String title, int year, Genres genre, int yearOfEnding,
                           String[] castBuffer, String originalTitle, String[] whereToWatchBuffer,
                           int seasonNumber, int episodeCount){

        ArrayList<String> cast = new ArrayList<>(Arrays.asList(castBuffer));
        ArrayList<String> whereToWatch = new ArrayList<>(Arrays.asList(whereToWatchBuffer));

        Season season = new Season(seasonNumber, episodeCount);

        Series series = new Series(title, year, genre, yearOfEnding, cast,
                originalTitle, whereToWatch);

        try {
            journal.isRegistered(series);
            series.addSeason(season);
            journal.addSeries(series);
            journal.addYear(year);
            return "Série registrada com sucesso!";
        }catch (MediaAlreadyExistsException e){
            return e.getMessage();
        }
    }

    public String rate(String title, int seasonNumber, double rating) {
        try {
            Series series = journal.findSeries(title);

            Season season = series.findSeason(seasonNumber);
            season.setRating(rating);
            series.updateRate();
            return "Avaliação salva com sucesso";

        }catch (SeasonNotFoundException | MediaNotFoundException e){
            return e.getMessage();
        }
    }

    public String writeReview(String title, int seasonNumber, String review) {
        try {
            Series serie = journal.findSeries(title);
            Season season = serie.findSeason(seasonNumber);
            season.setReview(review);
            return "Review salva com sucesso";

        } catch (SeasonNotFoundException | MediaNotFoundException e) {
            return e.getMessage();
        }

    }

    public String readReview(String title, int seasonNumber) {

        try {
            Series series = journal.findSeries(title);
            Season season = series.findSeason(seasonNumber);
            return "Review: " + ((season.getReview() == null) ?
                    "Você ainda não escreveu uma review desta temporada" : season.getReview());
        } catch (MediaNotFoundException e) {
            return e.getMessage();

        }
    }
    @Override
    public String showRating(String title) {

        try {
            Media media = journal.findMedia(title);
            return "Nota geral: " + ((media.getRating() == 0.0) ?
                    "Sem temporadas avaliadas" : media.getRating());
        } catch (MediaNotFoundException e) {
            return e.getMessage();

        }
    }

    public String showRating(String title, int seasonNumber) {

        try {
            Series series = journal.findSeries(title);
            Season season = series.findSeason(seasonNumber);
            return "Nota: " + ((season.getRating() == 0.0) ?
                    "Você ainda não avaliou esta temporada" : season.getRating());
        } catch (MediaNotFoundException e) {
            return e.getMessage();

        }
    }

    public ArrayList<Series> allSeries(){
        return journal.getSeriesList();
    }
}
