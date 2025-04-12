package Controller;

import Model.Exceptions.MediaAlreadyExistsException;
import Model.Genres;
import Model.Library;
import Model.Media.*;
import Model.Exceptions.MediaNotFoundException;

import java.time.Duration;
import java.util.*;

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
            journal.addYear(year);
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
            journal.addYear(year);
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
            journal.addYear(year);
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

    //TODO: CRESCENTE E DA MENOR NOTA PARA A MAIOR OU DA MAIOR PARA A MENOR?
    public <X extends Media> List<X> sortAscending(List<X> mediaList){
        return mediaList.stream().sorted(Comparator.comparing(Media::getRating)).toList();
    }

    public <X extends Media> List<X> sortDescending(List<X> mediaList){
        return mediaList.stream().sorted(Comparator.comparing(Media::getRating)).toList().reversed();
    }

    /*Gera um LinkedHashMap (para manter a ordem) com as chaves sendo
     os anos cadastrados e os valores sendo as listas de cada ano.
     */
    public Map<Integer, List<Book>> booksByAscendingYearAscendingRate(){
        Map<Integer, List<Book>> mapYearBook = new LinkedHashMap<>();

        for(Integer year: journal.getYearsRegistered()){
            List<Book> books = searchBookByYear(year);
            mapYearBook.put(year, books);
        }
        return mapYearBook;
    }

    public Map<Integer, List<Book>> booksByAscendingYearDescendingRate(){
        Map<Integer, List<Book>> mapYearBook = new LinkedHashMap<>();

        for(Integer year: journal.getYearsRegistered()){
            List<Book> books = sortDescending(searchBookByYear(year));
            mapYearBook.put(year, books);
        }
        return mapYearBook;
    }

    public Map<Integer, List<Book>> booksByDescendingYearAscendingRate(){
        Map<Integer, List<Book>> mapYearBook = new LinkedHashMap<>();

        for(Integer year: journal.getYearsRegistered().reversed()){
            List<Book> books = searchBookByYear(year);
            mapYearBook.put(year, books);
        }
        return mapYearBook;
    }

    public Map<Integer, List<Book>> booksByDescendingYearDescendingRate(){
        Map<Integer, List<Book>> mapYearBook = new LinkedHashMap<>();

        for(Integer year: journal.getYearsRegistered().reversed()){
            List<Book> books = sortDescending(searchBookByYear(year));
            mapYearBook.put(year, books);
        }
        return mapYearBook;
    }

    /*Gera um EnumMap (para manter a ordem) com as chaves sendo
     os generos e os valores sendo as listas de cada genero.
     */
    public Map<Genres, List<Book>> booksByGenreAscendingRate() {
        Map<Genres, List<Book>> mapGenreBook = new EnumMap<>(Genres.class);

        for (Genres genre : Genres.values()) {
            List<Book> books = searchBookByGenre(genre);
            mapGenreBook.put(genre, books);

        }
        return mapGenreBook;
    }

    public Map<Genres, List<Book>> booksByGenreDescendingRate() {
        Map<Genres, List<Book>> mapGenreBook = new EnumMap<>(Genres.class);

        for (Genres genre : Genres.values()) {
            List<Book> books = sortDescending(searchBookByGenre(genre));
            mapGenreBook.put(genre, books);

        }
        return mapGenreBook;
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
