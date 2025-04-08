package Controller;

import Model.Genres;
import Model.Library;
import Model.Media.*;
import Model.Exceptions.MediaNotFoundException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class JournalController {
    private final Library journal;

    public JournalController(Library jornal){
        this.journal = jornal;
    }

    public void register(String name, int year, int genre, String isbn,
                             String author, String publisher, boolean owned){

        Book book = new Book(name, year, genre, isbn, author, publisher, owned);
        journal.addBook(book);
    }

    public void register(String name, int year, int genre, String[] castBuffer,
                         Duration duration, String direction, String script,
                         String originalTitle, String[] whereToWatchBuffer){

        ArrayList<String> cast = new ArrayList<>(Arrays.asList(castBuffer));//add todos os nomes na lista
        ArrayList<String> whereToWatch = new ArrayList<>(Arrays.asList(whereToWatchBuffer));

        Movie movie = new Movie(name, year, genre, cast, duration, direction,
                script, originalTitle, whereToWatch);

        journal.addMovie(movie);
    }

    public void register(String name, int year, int genre, int yearOfEnding,
                         String[] castBuffer, String originalTitle, String[] whereToWatchBuffer,
                         int seasonNumber){

        ArrayList<String> cast = new ArrayList<>(Arrays.asList(castBuffer));
        ArrayList<String> whereToWatch = new ArrayList<>(Arrays.asList(whereToWatchBuffer));

        Season season = new Season(seasonNumber);

        Series series = new Series(name, year, genre, yearOfEnding, cast,
                originalTitle, whereToWatch);

        series.addSeason(season);
        journal.addSeries(series);
    }

    public String rate(String name, double rating){
        try{
            Media media = journal.findMedia(name);
            media.setSeen(true);
            media.setRating(rating);
            return "Avaliação salva com sucesso";

        } catch (MediaNotFoundException e){
            return e.getMessage();
        }

    }

    public String rate(String name, int seasonNumber, double rating){
        try{
            Series series = journal.findSeries(name);

            for(Season season: series.getSeasons()){   //TODO: METODO FINDSEASON? *
                if(seasonNumber == season.getSeasonNumber()){
                    season.setRating(rating);
                    return "Avaliação salva com sucesso";
                }
            }
            return "Erro. Temporada não cadastrada"; //TODO: EXCECAO PARA TEMPORADA? **

        }catch (MediaNotFoundException e) {
            return e.getMessage();
        }
    }

    public String writeReview(String name, String review){
        try{
            Media media = journal.findMedia(name);
            media.setSeen(true);
            media.setReview(review);
            return "Review salva com sucesso";

        }catch (MediaNotFoundException e){
            return e.getMessage();
        }

    }

    public String writeReview(String name, int seasonNumber, String review){
        try {
            Series serie = journal.findSeries(name);

            for (Season season : serie.getSeasons()) { //TODO: *
                if (seasonNumber == season.getSeasonNumber()) {
                    season.setReview(review);
                    return "Review salva com sucesso";
                }
            }
            return "Erro. Temporada não cadastrada";

        } catch (MediaNotFoundException e) { //TODO: **
            return e.getMessage();
        }

    }

    public List<Book> searchBook(String title){
        String titleLower = title.toLowerCase().trim();
        List<Book> filteredBooks = journal.getBookList().stream().filter
                (book -> book.getTitle().toLowerCase().contains(titleLower)).toList();

        return filteredBooks.stream().sorted(Comparator.comparing(Book::getRating)).toList();
    }

    public List<Movie> searchMovie(String title) {
        String titleLower = title.toLowerCase().trim();
        List<Movie> filteredMovies = journal.getMovieList().stream().filter
                (movie -> movie.getTitle().toLowerCase().contains(titleLower)).toList();

        return filteredMovies.stream().sorted(Comparator.comparing(Movie::getRating)).toList();
    }

    public List<Series> searchSeries(String title){
        String titleLower = title.toLowerCase().trim();
        List<Series> filteredSeries = journal.getSeriesList().stream().filter
                (series -> series.getTitle().toLowerCase().contains(titleLower)).toList();

        return filteredSeries.stream().sorted(Comparator.comparing(Series::getRating)).toList();

    }

    public String readReview(String name){

        try{
            Media media = journal.findMedia(name);
            return "Review: " + ((media.getReview() == null)?
                    "Você ainda não escreveu uma review" : media.getReview());
        } catch (MediaNotFoundException e){
            return e.getMessage();

        }
    }

    public String showRating(String name) {

        try {
            Media media = journal.findMedia(name);
            return "Nota: " + ((media.getRating() == 0.0) ?
                    "Você ainda não avaliou a obra" : media.getRating());
        } catch (MediaNotFoundException e) {
            return e.getMessage();

        }
    }

    public static void viewGenres() {
        Genres.showGenres();
    }

    public ArrayList<Book> allBooks(){
        return journal.getBookList();
    }

    public ArrayList<Movie> allMovies(){
        return journal.getMovieList();
    }

    public ArrayList<Series> allSeries(){
        return journal.getSeriesList();
    }


}
