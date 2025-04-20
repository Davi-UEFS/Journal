package Controller;

import Model.Exceptions.MediaAlreadyExistsException;
import Model.Enums.Genres;
import Model.Library;

import Model.Medias.Movie;
import Model.Enums.Months;
import Model.Result.*;

import java.time.Duration;
import java.util.*;

public class MovieService extends CommonService<Movie> {

    public MovieService(Library journal){
        super(journal);
    }

    public IResult register(String name, int year, Genres genre, String[] castBuffer,
                            Duration duration, String direction, String script,
                            String originalTitle, String[] whereToWatchBuffer){

        ArrayList<String> cast = new ArrayList<>(Arrays.asList(castBuffer));//add todos os nomes na lista
        ArrayList<String> whereToWatch = new ArrayList<>(Arrays.asList(whereToWatchBuffer));

        Movie movie = new Movie(name, year, genre, cast, duration, direction,
                script, originalTitle, whereToWatch);

        try {
            journal.exists(movie);
            journal.addMovie(movie);
            journal.addYear(year);
            return new Success("Filme","Registrado com sucesso!");

        }catch (MediaAlreadyExistsException e){
            return new Failure("Filme",e.getMessage());
        }
    }

    public List<Movie> searchByDirector(String director){
        String directorLower = director.toLowerCase().trim();
        List<Movie> filteredMovies = journal.getMovieList().stream().filter
                (movie -> movie.getDirection().toLowerCase().contains(directorLower)).toList();

        return sortAscending(filteredMovies);
    }

    public List<Movie> searchByActor(String name){
        String actorLower = name.toLowerCase().trim();
        List<Movie> filteredMovies = journal.getMovieList().stream().filter
                (movie -> movie.getCast().stream().anyMatch(
                actor-> actor.toLowerCase().contains(actorLower))).toList();

        return sortAscending(filteredMovies);
    }

    public IResult markAsSeen(Movie movie, int year, Months month){

        if(movie.isSeen())
            return new Failure("Filme", "Já marcado como visto");

        if(year < movie.getYear() || year > 2025)
            return new Failure("Filme", "Ano inválido!");

        String date = month.toString() + " de " + year;
        movie.setSeen(true);
        movie.setSeenDate(date);
        return new Success("Filme", "Marcado como visto e data registrada.");
    }

    public ArrayList<Movie> getAllMovies(){
        return journal.getMovieList();
    }

}
