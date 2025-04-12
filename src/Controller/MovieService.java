package Controller;

import Model.Exceptions.MediaAlreadyExistsException;
import Model.Genres;
import Model.Library;
import Model.Media.Movie;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieService extends MediaService<Movie>{

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
            journal.isRegistered(movie);
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

    public ArrayList<Movie> allMovies(){
        return journal.getMovieList();
    }

}
