package Controller;

import Model.Exceptions.MediaAlreadyExistsException;
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

    public String register(String name, int year, Genres genre, String isbn,
                             String author, String publisher, boolean owned) {

        Book book = new Book(name, year, genre, isbn, author, publisher, owned);

        try {
            journal.isRegistered(book);
            journal.addBook(book);
            return "Livro registrado com sucesso!";
        } catch (MediaAlreadyExistsException e){
            return e.getMessage();
        }
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
            return "Filme registrado com sucesso!";

        }catch (MediaAlreadyExistsException e){
            return e.getMessage();
        }

    }

    public String register(String name, int year, Genres genre, int yearOfEnding,
                         String[] castBuffer, String originalTitle, String[] whereToWatchBuffer,
                         int seasonNumber, int episodeCount){

        ArrayList<String> cast = new ArrayList<>(Arrays.asList(castBuffer));
        ArrayList<String> whereToWatch = new ArrayList<>(Arrays.asList(whereToWatchBuffer));

        Season season = new Season(seasonNumber, episodeCount);

        Series series = new Series(name, year, genre, yearOfEnding, cast,
                originalTitle, whereToWatch);

        try {
            journal.isRegistered(series);
            series.addSeason(season);
            journal.addSeries(series);
            return "Série registrada com sucesso!";
        }catch (MediaAlreadyExistsException e){
            return e.getMessage();
        }
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


    // SEARCH BOOKS:
    public List<Book> searchBookByTitle(String title){
        String titleLower = title.toLowerCase().trim();
        List<Book> filteredBooks = journal.getBookList().stream().filter
                (book -> book.getTitle().toLowerCase().contains(titleLower)).toList();

        return sortAscending(filteredBooks);
    }

    public List<Book> searchBookByYear(int year){
        List<Book> filteredBooks = journal.getBookList().stream().filter
                (book -> book.getYear() == year).toList();

        return sortAscending(filteredBooks);
    }

    public List<Book> searchBookByGenre(Genres genre){
        List<Book> filteredBooks = journal.getBookList().stream().filter
                (book -> book.getGenre() == genre).toList();

        return sortAscending(filteredBooks);
    }

    public List<Book> searchBookByIsbn(String isbn){
        String isbnLower = isbn.toLowerCase().trim();
        List<Book> filteredBooks = journal.getBookList().stream().filter
                (book -> book.getTitle().toLowerCase().contains(isbnLower)).toList();

        return sortAscending(filteredBooks);
    }

    public List<Book> searchBookByAuthor(String author){
        String authorLower = author.toLowerCase().trim();
        List<Book> filteredBooks = journal.getBookList().stream().filter
                (book -> book.getTitle().toLowerCase().contains(authorLower)).toList();

        return sortAscending(filteredBooks);
    }


    //SEARCH MOVIES:
    public List<Movie> searchMovieByTitle(String title){
        String titleLower = title.toLowerCase().trim();
        List<Movie> filteredMovies = journal.getMovieList().stream().filter
                (movie -> movie.getTitle().toLowerCase().contains(titleLower)).toList();

        return sortAscending(filteredMovies);
    }

    public List<Movie> searchMovieByDirector(String director){
        String directorLower = director.toLowerCase().trim();
        List<Movie> filteredMovies = journal.getMovieList().stream().filter
                (movie -> movie.getDirection().toLowerCase().contains(directorLower)).toList();

        return sortAscending(filteredMovies);
    }

    public List<Movie> searchMovieByYear(int year){
        List<Movie> filteredMovies = journal.getMovieList().stream().filter
                (movie -> movie.getYear() == year).toList();

        return sortAscending(filteredMovies);
    }

    public List<Movie> searchMovieByGenre(Genres genre){
        List<Movie> filteredMovies = journal.getMovieList().stream().filter
                (movie -> movie.getGenre() == genre).toList();

        return sortAscending(filteredMovies);
    }


    //SEARCH SERIES:
    public List<Series> searchSeriesByTitle(String title){
        String titleLower = title.toLowerCase().trim();
        List<Series> filteredSeries = journal.getSeriesList().stream().filter
                (series -> series.getTitle().toLowerCase().contains(titleLower)).toList();

        return sortAscending(filteredSeries);
    }

    public List<Series> searchSeriesByYear(int year){
        List<Series> filteredSeries = journal.getSeriesList().stream().filter
                (series -> series.getYear() == year).toList();

        return sortAscending(filteredSeries);
    }

    public List<Series> searchSeriesByGenre(Genres genre){
        List<Series> filteredSeries = journal.getSeriesList().stream().filter
                (series -> series.getGenre() == genre).toList();

        return sortAscending(filteredSeries);
    }

    public List<Series> searchSeries(String title){
        String titleLower = title.toLowerCase().trim();
        List<Series> filteredSeries = journal.getSeriesList().stream().filter
                (series -> series.getTitle().toLowerCase().contains(titleLower)).toList();

        return sortAscending(filteredSeries);

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

    public <X extends Media> List<X> sortAscending(List<X> mediaList){
        return mediaList.stream().sorted(Comparator.comparing(Media::getRating)).toList();
    }

    public <X extends Media> List<X> sortDescending(List<X> mediaList){
        return mediaList.stream().sorted(Comparator.comparing(Media::getRating)).toList().reversed();
    }

    public StringBuffer booksByGenreTextAscending() {
        //TODO: Logica nao e adequada para GUI
        StringBuffer booksByGenreText = new StringBuffer();

        for (Genres genre: Genres.values()) {

            booksByGenreText.append(genre);
            booksByGenreText.append("\n");
            List<Book> booksByGenre = searchBookByGenre(genre);

            if (booksByGenre.isEmpty())
                booksByGenreText.append("Nenhum livro cadastrado para esse gênero.\n\n");

            else {
                for (Book book : booksByGenre) {
                    booksByGenreText.append(book.toString());
                    booksByGenreText.append("\n\n");
                }
            }


        }
        return booksByGenreText;
    }

    public StringBuffer booksByGenreTextDescending() {
        //TODO: Logica nao e adequada para GUI
        StringBuffer booksByGenreText = new StringBuffer();

        for (Genres genre: Genres.values()) {

            booksByGenreText.append(genre);
            booksByGenreText.append("\n");
            List<Book> booksByGenre = sortDescending(searchBookByGenre(genre));

            if (booksByGenre.isEmpty())
                booksByGenreText.append("Nenhum livro cadastrado para esse gênero.\n\n");

            else {
                for (Book book : booksByGenre) {
                    booksByGenreText.append(book.toString());
                    booksByGenreText.append("\n\n");
                }
            }


        }
        return booksByGenreText;
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
