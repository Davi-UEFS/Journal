package Controller;

import Model.Exceptions.MediaAlreadyExistsException;
import Model.Genres;
import Model.Library;

import Model.Media.Movie;

import javax.print.ServiceUI;
import java.time.Duration;
import java.util.*;

public class MovieService extends CommonService<Movie> {

    public MovieService(Library journal){
        super(journal);
    }

    public String register(String name, int year, Genres genre, String[] castBuffer,
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
            return "Filme registrado com sucesso!";

        }catch (MediaAlreadyExistsException e){
            return e.getMessage();
        }

    }

    public List<Movie> searchByDirector(String director){
        String directorLower = director.toLowerCase().trim();
        List<Movie> filteredMovies = journal.getMovieList().stream().filter
                (movie -> movie.getDirection().toLowerCase().contains(directorLower)).toList();

        return sortAscending(filteredMovies);
    }

    public ArrayList<Movie> getAllMovies(){
        return journal.getMovieList();
    }

}
