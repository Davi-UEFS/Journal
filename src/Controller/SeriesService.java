package Controller;

import Model.Exceptions.MediaAlreadyExistsException;
import Model.Exceptions.SeasonNotFoundException;
import Model.Genres;
import Model.Library;

import Model.Medias.Season;
import Model.Medias.Series;
import Model.Result.*;

import java.util.*;

public class SeriesService extends CommonService<Series> {

    public SeriesService(Library journal){
        super(journal);
    }

    public IResult register(String title, int year, Genres genre, int yearOfEnding,
                           String[] castBuffer, String originalTitle, String[] whereToWatchBuffer,
                           int seasonNumber, int episodeCount){

        ArrayList<String> cast = new ArrayList<>(Arrays.asList(castBuffer));
        ArrayList<String> whereToWatch = new ArrayList<>(Arrays.asList(whereToWatchBuffer));

        Season season = new Season(seasonNumber, episodeCount);

        Series series = new Series(title, year, genre, yearOfEnding, cast,
                originalTitle, whereToWatch);

        try {
            journal.exists(series);
            series.addSeason(season);
            journal.addSeries(series);
            journal.addYear(year);
            return new Success("Série","Registrada com sucesso.");
        }catch (MediaAlreadyExistsException e){
            return new Failure("Série",e.getMessage());
        }
    }

    public IResult registerSeason(Series series, int seasonNumber, int episodeCount){
        Season season = new Season(seasonNumber, episodeCount);
        try{
            series.findSeason(seasonNumber);
            return new Failure("Temporada", "Já existe");
        } catch (SeasonNotFoundException e){
            series.addSeason(season);
            return new Success("Temporada", "Registrada com sucesso.");
        } //TODO: MUDAR ANO DA SERIE?
    }

    public IResult markAsSeenSeason(Series series, int seasonNumber) {
        try{
            Season season = series.findSeason(seasonNumber);
            if(season.isSeen()) {
                return new Failure("Temporada", "Já marcado como visto");
            }
            season.setSeen(true);
            return new Success("Temporada", "Marcado como visto");

    }catch (SeasonNotFoundException e){
        return new Failure("Temporada",e.getMessage());
    }
    }

    //TODO: OVERRIDE EM RATE E REVIEW DO PAI. EXCECAO
           /////////////////////////////////////////////
    public IResult rateSeason(Series series, int seasonNumber, double rating) {
        try {

            Season season = series.findSeason(seasonNumber);
            if(season.isSeen()) {
                season.setRating(rating);
                series.updateRate();
                return new Success("Temporada", "Avaliação salva com sucesso");
            }
            return new Failure("Temporada", "Marque como visto antes de avaliar");

        }catch (SeasonNotFoundException e){
            return new Failure("Temporada",e.getMessage());
        }
    }

    public IResult writeReviewSeason(Series series, int seasonNumber, String review) {
        try {
            Season season = series.findSeason(seasonNumber);
            if(season.isSeen()) {
                season.setReview(review);
                return new Success("Temporada", "Review salva com sucesso");
            }
            return new Failure("Temporada", "Marque como visto antes de escrever uma review");
        } catch (SeasonNotFoundException e) {
            return new Failure("Temporada",e.getMessage());
        }
    }

    public String readReviewSeason(Series series, int seasonNumber) {

        try {

            Season season = series.findSeason(seasonNumber);
            return "Review: " + ((season.getReview() == null) ?
                    "Você ainda não escreveu uma review desta temporada" : season.getReview());
        } catch (SeasonNotFoundException e) {
            return e.getMessage();

        }
    }
    @Override
    public String showRating(Series series) {

            return "Nota geral: " + ((series.getRating() == 0.0) ?
                    "Sem temporadas avaliadas" : series.getRating());

    }

    public String showRatingSeason(Series series, int seasonNumber) {

        try {
            Season season = series.findSeason(seasonNumber);
            return "Nota: " + ((season.getRating() == 0.0) ?
                    "Você ainda não avaliou esta temporada" : season.getRating());
        } catch (SeasonNotFoundException e) {
            return e.getMessage();

        }
    }

    public List<Series> searchByActor(String name){
        String actorLower = name.toLowerCase().trim();
        List<Series> filteredSeries = journal.getSeriesList().stream().filter
                (series -> series.getCast().stream().anyMatch(
                        actor-> actor.toLowerCase().contains(actorLower))).toList();

        return sortAscending(filteredSeries);
    }

    public ArrayList<Series> getAllSeries(){
        return journal.getSeriesList();
    }
}
