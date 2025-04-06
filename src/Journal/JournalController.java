package Journal;

import Media.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JournalController {
    private final JournalModel journal;

    public JournalController(JournalModel jornal){
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

    public boolean rateMedia(String name, double rating){
        Media media = findMedia(name);

        if(media == null)
            return false;

        media.setSeen(true);
        media.setRating(rating);
        return true;
    }

    public boolean rateSeason(String name, int seasonNumber, double rating){
        Series serie = findSeries(name);

        if(serie == null)
            return false;

        for(Season season: serie.getSeasons()){
            if(seasonNumber == season.getSeasonNumber()){
                season.setRating(rating);
                return true;
            }

        }
        return false;

    }

    public List<Book> searchBook(String title){
        String titleLower = title.toLowerCase().trim();
        List<Book> bookList = journal.getBookList();
        return bookList.stream().filter(book -> book.getTitle().toLowerCase().contains(titleLower)).toList(); //TODO GUARDAR NOME DO LIVRO EM LOWERCASE
    }

    public List<Movie> searchMovie(String title){
        String titleLower = title.toLowerCase().trim();
        List<Movie> movieList = journal.getMovieList();
        return movieList.stream().filter(movie -> movie.getTitle().toLowerCase().contains(titleLower)).toList();
    }

    public List<Series> searchSeries(String title){
        String titleLower = title.toLowerCase().trim();
        List<Series> seriesList = journal.getSeriesList();
        return seriesList.stream().filter(series -> series.getTitle().toLowerCase().contains(titleLower)).toList();
    }

    public boolean writeReview(String name, String review){
        Media media = findMedia(name);

        if(media==null)
            return false;

        media.setSeen(true);  //TODO: VERIFICAO DEPOIS
        media.setReview(review);
        return true;

    }

    private Media findMedia(String name){

        Media media = findBook(name);

        if(media == null)
            media = findMovie(name);

        if(media == null)
            media = findSeries(name);

        return media;
    }

    private Book findBook(String name){
        String lowerName = name.toLowerCase();

        for(Book book:journal.getBookList()) {
            if (lowerName.equals(book.getTitle().toLowerCase()))
                return book;
        }
        return null;
    }

    private Movie findMovie(String name){
        String lowerName = name.toLowerCase();

        for(Movie movie:journal.getMovieList()) {
            if (lowerName.equals(movie.getTitle().toLowerCase()))
                return movie;
        }
        return null;
    }

    private Series findSeries(String name){
        String lowerName = name.toLowerCase();

        for(Series series:journal.getSeriesList()) {
            if (lowerName.equals(series.getTitle().toLowerCase()))
                return series;
        }
        return null;
    }

    public String readReview(String name){

        Media media = findBook(name);

        if(media == null)
            media = findMovie(name);

        if(media == null)
            media = findSeries(name);

        if(media == null)
            return "Midia nao encontrada";  //TODO: GRAMATICA

        return "Review: " + ((media.getReview() == null)?
                "Voce ainda nao escreveu uma review" : media.getReview());

    }

    public String showRating(String name){

        Media media = findBook(name);

        if(media == null)    //se nao existe um livro com este nome
            media = findMovie(name);

        if(media == null)    //se nao existe livro nem filme
            media = findSeries(name);

        if(media == null)
            return "Midia nao encontrada"; //TODO: GRAMATICA

        return "Nota: " + media.getRating();

    }

    public static void showGenres(){
        for(int i = 0; i < Genres.values().length; ++i){
            System.out.println(i+1 + " - " + Journal.Genres.values()[i].name());
        }
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

    public boolean reviewSeason(String name, int seasonNumber, String review){
        Series serie = findSeries(name);

        if(serie == null)
            return false;

        for(Season season: serie.getSeasons()){
            if(seasonNumber == season.getSeasonNumber()){
                season.setReview(review);
                return true;
            }

        }
        return false;
    }
}
